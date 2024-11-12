package fr.app.seni.type.etbsmt.controllers;


import fr.app.seni.core.cqrs.type_etablissement.command.CreateTypeEtablissementCommand;
import fr.app.seni.core.cqrs.type_etablissement.command.DeleteTypeEtablissementCommand;
import fr.app.seni.core.cqrs.type_etablissement.command.UpdateTypeEtablissementCommand;
import fr.app.seni.core.dto.TypeEtablissementDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/manager/command/type-etablissement")
public class TypeEtablissementCommandController {

    private final CommandGateway commandGateway;

    @PostMapping
    public CompletableFuture<String> create(@RequestBody TypeEtablissementDto typeEtablissementDto){
        log.info("REQUEST to create TypeEtablissement : {}", typeEtablissementDto);
        return commandGateway.send(new CreateTypeEtablissementCommand(
                UUID.randomUUID().toString(),
                typeEtablissementDto
        ));
    }

    @PutMapping
    public CompletableFuture<String> update(@RequestBody TypeEtablissementDto typeEtablissementDto){
        log.info("REQUEST to update TypeEtablissement : {}", typeEtablissementDto);
        return commandGateway.send(new UpdateTypeEtablissementCommand(
                typeEtablissementDto.getIdTypeEtab(),
                typeEtablissementDto
        ));
    }

    @DeleteMapping("/{id}")
    public CompletableFuture<String> delete(@PathVariable String id){
        log.info("REQUEST to date TypeEtablissement : {}",id);
        return commandGateway.send(new DeleteTypeEtablissementCommand(id, id));
    }
}
