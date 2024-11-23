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

    public TypeHopitalDto create(TypeHopitalDto typeHopitalDto) {
        return typeHopitalMapper.toDto(typeHopitalRepository.save(typeHopitalMapper.toEntity(typeHopitalDto)));
    }

    public TypeHopitalDto update(TypeHopitalDto typeHopitalDto) {
        return typeHopitalMapper.toDto(typeHopitalRepository.save(typeHopitalMapper.toEntity(typeHopitalDto)));
    }

    public void delete(TypeHopitalDto typeHopitalDto) {
        typeHopitalRepository.delete(typeHopitalMapper.toEntity(typeHopitalDto));
    }

    public List<TypeHopitalDto> findAll(){
        return typeHopitalRepository.findAll().stream().map(typeHopitalMapper::toDto).collect(Collectors.toList());
    }

    public TypeHopitalDto findOne(String idTypeHopital){
        try {
            return typeHopitalMapper.toDto(typeHopitalRepository.findById(idTypeHopital).get());
        }catch (Exception e) {
            return null;
        }
    }

    public List<TypeHopitalDto> findByEnabled(Boolean enabled){
        return typeHopitalRepository.findByEnabled(enabled).stream().map(typeHopitalMapper::toDto).collect(Collectors.toList());
    }
}
