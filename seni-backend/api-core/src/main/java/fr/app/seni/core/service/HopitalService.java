package fr.app.seni.core.service;


import fr.app.seni.core.dto.HopitalDto;
import fr.app.seni.core.mapper.HopitalMapper;
import fr.app.seni.core.repository.HopitalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class HopitalService {

    private final HopitalMapper hopitalMapper;
    private  final HopitalRepository hopitalRepository;

    public HopitalDto create(HopitalDto entity) {
        return hopitalMapper.toDto(hopitalRepository.save(hopitalMapper.toEntity(entity)));
    }

    public HopitalDto update(HopitalDto entity) {
        return hopitalMapper.toDto(hopitalRepository.save(hopitalMapper.toEntity(entity)));
    }

    public void delete(HopitalDto entity) {
        hopitalRepository.delete(hopitalMapper.toEntity(entity));
    }

    public List<HopitalDto> findAll(){
        return hopitalRepository.findAll().stream().map(hopitalMapper::toDto).collect(Collectors.toList());
    }

    public HopitalDto findOne(String id){
        try {
            return hopitalMapper.toDto(hopitalRepository.findById(id).get());
        }catch (Exception e) {
            return null;
        }
    }
}
