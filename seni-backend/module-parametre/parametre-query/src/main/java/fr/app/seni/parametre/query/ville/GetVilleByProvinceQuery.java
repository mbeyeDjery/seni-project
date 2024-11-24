package fr.app.seni.parametre.query.ville;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GetVilleByProvinceQuery {
    private final String idProvince;
}
