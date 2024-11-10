package fr.app.seni.core.service;

import fr.app.seni.core.dto.EtablissementDto;
import fr.app.seni.core.dto.TypeEtablissementDto;
import fr.app.seni.core.mapper.EtablissementMapper;
import fr.app.seni.core.mapper.TypeEtablissementMapper;
import fr.app.seni.core.repository.EtablissementRepository;
import fr.app.seni.core.repository.TypeEtablissementRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TypeEtablissementService {

    private final TypeEtablissementMapper typeEtablissementMapper;
    private final TypeEtablissementRepository typeEtablissementRepository;

    public TypeEtablissementDto create(TypeEtablissementDto typeEtablissementDto){
        return typeEtablissementMapper.toDto(typeEtablissementRepository.save(typeEtablissementMapper.toEntity(typeEtablissementDto)));
    }

    public TypeEtablissementDto update(TypeEtablissementDto typeEtablissementDto){
        return typeEtablissementMapper.toDto(typeEtablissementRepository.save(typeEtablissementMapper.toEntity(typeEtablissementDto)));
    }

    public void delete(TypeEtablissementDto typeEtablissementDto){
        typeEtablissementRepository.delete(typeEtablissementMapper.toEntity(typeEtablissementDto));
    }

    public List<TypeEtablissementDto> findAll(){
        return typeEtablissementRepository.findAll().stream().map(typeEtablissementMapper::toDto).collect(Collectors.toList());
    }

    public TypeEtablissementDto findOne(String id){
        return typeEtablissementMapper.toDto(typeEtablissementRepository.findById(id).get());
    }
}
