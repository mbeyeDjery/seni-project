package fr.app.seni.parametre.queryhandler;


import fr.app.seni.core.dto.SecteurDto;
import fr.app.seni.core.service.SecteurService;
import fr.app.seni.parametre.query.secteur.GetAllSecteurQuery;
import fr.app.seni.parametre.query.secteur.GetSecteurByIdQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SecteurHandlerService {

    private final SecteurService secteurService;

    @QueryHandler
    public SecteurDto onGetByID(GetSecteurByIdQuery query) {
        return secteurService.findOne(query.getId());
    }

    @QueryHandler
    public List<SecteurDto> onGetAll(GetAllSecteurQuery query) {
        return secteurService.findAll();
    }

}
