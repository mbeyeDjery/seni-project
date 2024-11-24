package fr.app.seni.core.service;


import fr.app.seni.core.dto.ContratHopitalDto;
import fr.app.seni.core.enums.ContratHopitalStatus;
import fr.app.seni.core.mapper.ContratHopitalMapper;
import fr.app.seni.core.repository.ContratHopitalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContratHopitalService {

    private final ContratHopitalMapper contratHopitalMapper;
    private final ContratHopitalRepository contratHopitalRepository;

    public ContratHopitalDto create(ContratHopitalDto entity) {
        return contratHopitalMapper.toDto(contratHopitalRepository.save(contratHopitalMapper.toEntity(entity)));
    }

    public ContratHopitalDto update(ContratHopitalDto entity) {
        return contratHopitalMapper.toDto(contratHopitalRepository.save(contratHopitalMapper.toEntity(entity)));
    }

    public void delete(ContratHopitalDto entity) {
        contratHopitalRepository.delete(contratHopitalMapper.toEntity(entity));
    }

    public List<ContratHopitalDto> findAll(){
        return contratHopitalRepository.findAll().stream().map(contratHopitalMapper::toDto).collect(Collectors.toList());
    }

    public ContratHopitalDto findOne(String id){
        try {
            return contratHopitalMapper.toDto(contratHopitalRepository.findById(id).get());
        }catch (Exception e) {
            return null;
        }
    }

    public List<ContratHopitalDto> findByHopital(String idHopital){
        return contratHopitalRepository.findByHopital(idHopital).stream().map(contratHopitalMapper::toDto).collect(Collectors.toList());
    }

    public List<ContratHopitalDto> findByHopitalAndStatut(String idHopital, ContratHopitalStatus statut){
        return contratHopitalRepository.findByHopitalAndStatut(idHopital, statut).stream().map(contratHopitalMapper::toDto).collect(Collectors.toList());
    }
}
