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

    public HopitalDto create(HopitalDto hopitalDto) {
        return hopitalMapper.toDto(hopitalRepository.save(hopitalMapper.toEntity(hopitalDto)));
    }

    public HopitalDto update(HopitalDto hopitalDto) {
        return hopitalMapper.toDto(hopitalRepository.save(hopitalMapper.toEntity(hopitalDto)));
    }

    public void delete(HopitalDto hopitalDto) {
        hopitalRepository.delete(hopitalMapper.toEntity(hopitalDto));
    }

    public List<HopitalDto> findAll(){
        return hopitalRepository.findAll().stream().map(hopitalMapper::toDto).collect(Collectors.toList());
    }
}
