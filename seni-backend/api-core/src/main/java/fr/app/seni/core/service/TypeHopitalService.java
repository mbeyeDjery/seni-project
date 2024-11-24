package fr.app.seni.core.service;


import fr.app.seni.core.dto.TypeHopitalDto;
import fr.app.seni.core.mapper.TypeHopitalMapper;
import fr.app.seni.core.repository.TypeHopitalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TypeHopitalService {

    private final TypeHopitalMapper typeHopitalMapper;
    private final TypeHopitalRepository typeHopitalRepository;

    public TypeHopitalDto create(TypeHopitalDto entity) {
        return typeHopitalMapper.toDto(typeHopitalRepository.save(typeHopitalMapper.toEntity(entity)));
    }

    public TypeHopitalDto update(TypeHopitalDto entity) {
        return typeHopitalMapper.toDto(typeHopitalRepository.save(typeHopitalMapper.toEntity(entity)));
    }

    public void delete(TypeHopitalDto entity) {
        typeHopitalRepository.delete(typeHopitalMapper.toEntity(entity));
    }

    public List<TypeHopitalDto> findAll(){
        return typeHopitalRepository.findAll().stream().map(typeHopitalMapper::toDto).collect(Collectors.toList());
    }

    public TypeHopitalDto findOne(String id){
        try {
            return typeHopitalMapper.toDto(typeHopitalRepository.findById(id).get());
        }catch (Exception e) {
            return null;
        }
    }

    public List<TypeHopitalDto> findByEnabled(Boolean enabled){
        return typeHopitalRepository.findByEnabled(enabled).stream().map(typeHopitalMapper::toDto).collect(Collectors.toList());
    }
}
