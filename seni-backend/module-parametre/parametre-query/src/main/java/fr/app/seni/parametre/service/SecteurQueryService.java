package fr.app.seni.parametre.service;

import fr.app.seni.core.dto.SecteurDto;
import fr.app.seni.core.exception.CustomException;
import fr.app.seni.parametre.query.secteur.GetAllSecteurQuery;
import fr.app.seni.parametre.query.secteur.GetSecteurByIdQuery;
import fr.app.seni.parametre.query.secteur.GetSecteurByVilleQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SecteurQueryService {

    private final QueryGateway queryGateway;

    public SecteurDto findOne(String id) {
        SecteurDto secteurDto = queryGateway.query(
                new GetSecteurByIdQuery(id),
                ResponseTypes.instanceOf(SecteurDto.class)
        ).join();

        if (secteurDto == null){
            throw new CustomException("Aucun élément correspondant", HttpStatus.NOT_FOUND);
        }

        return secteurDto;
    }

    public List<SecteurDto> getAll() {
        return queryGateway.query(
                new GetAllSecteurQuery(),
                ResponseTypes.multipleInstancesOf(SecteurDto.class)
        ).join();
    }

    public List<SecteurDto> getSecteurByVille(String idVille) {
        return queryGateway.query(
                new GetSecteurByVilleQuery(idVille),
                ResponseTypes.multipleInstancesOf(SecteurDto.class)
        ).join();
    }
}
