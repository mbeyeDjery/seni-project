package fr.app.seni.auth.gestion.service;

import fr.app.seni.core.dto.AuthRequest;
import fr.app.seni.core.dto.AuthResponse;
import fr.app.seni.core.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginService {

    private final RestTemplate restTemplate;

    @Value("${seni-app.keycloak.clientId}")
    private String clientId;

    @Value("${seni-app.keycloak.clientSecret}")
    private String clientSecret;

    @Value("${seni-app.keycloak.host}")
    private String host;

    @Value("${seni-app.keycloak.realm}")
    private String realm;

    @Value("${seni-app.keycloak.authBaseUrl}")
    private String authBaseUrl;

    public AuthResponse login(AuthRequest authRequest) {
        try {
            ResponseEntity<AuthResponse> responseEntity = restTemplate.postForEntity(authBaseUrl+"/token", getMultiValueMapHttpEntity(authRequest, true) , AuthResponse.class);
            if (responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.getBody() != null) {
                return responseEntity.getBody();
            } else {
                throw new CustomException("Nom ou mot de passe incorrect", HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            log.error("Internal error : {} ", e.getMessage());
            if (e.getMessage().contains("Connexion refusée")){
                throw new CustomException("Erreur serveur", HttpStatus.UNAUTHORIZED);
            }
            throw new CustomException("Nom ou mot de passe incorrect", HttpStatus.UNAUTHORIZED);
        }
    }

    public AuthResponse refreshToken(AuthRequest authRequest){
        try {
            ResponseEntity<AuthResponse> responseEntity = restTemplate.postForEntity(authBaseUrl+"/token", getMultiValueMapHttpEntity(authRequest, false) , AuthResponse.class);
            if (responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.getBody() != null) {
                return responseEntity.getBody();
            } else {
                throw new CustomException("Nom ou mot de passe incorrect", HttpStatus.UNAUTHORIZED);
            }
        }catch (Exception e){
            log.error("Internal error : {} ", e.getMessage());
            if (e.getMessage().contains("Connexion refusée")){
                throw new CustomException("Erreur serveur", HttpStatus.UNAUTHORIZED);
            }
            throw new CustomException("Session expirée", HttpStatus.UNAUTHORIZED);
        }
    }

    public void logout(AuthRequest authRequest){
        try {
            ResponseEntity<Object> responseEntity = restTemplate.postForEntity(authBaseUrl+"/logout", getMultiValueMapHttpEntity(authRequest, false) , Object.class);
        }catch (Exception e){
            log.error("Internal error : {} ", e.getMessage());
            throw new CustomException("Erreur serveur", HttpStatus.UNAUTHORIZED);
        }
    }

    private HttpEntity<MultiValueMap<String, String>> getMultiValueMapHttpEntity(AuthRequest authRequest, Boolean login) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add(OAuth2Constants.CLIENT_ID, clientId);
        requestBody.add(OAuth2Constants.CLIENT_SECRET, clientSecret);

        if (login){
            requestBody.add(OAuth2Constants.USERNAME, authRequest.username());
            requestBody.add(OAuth2Constants.PASSWORD, authRequest.password());
            requestBody.add(OAuth2Constants.GRANT_TYPE, OAuth2Constants.PASSWORD);
        }else {
            requestBody.add(OAuth2Constants.REFRESH_TOKEN, authRequest.token());
            requestBody.add(OAuth2Constants.GRANT_TYPE, OAuth2Constants.REFRESH_TOKEN);
        }

        return new HttpEntity<>(requestBody, headers);
    }
}