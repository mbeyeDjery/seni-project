package fr.app.seni.parametre.query.province;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GetProvinceByRegionQuery {
    private final String idRegion;
}
