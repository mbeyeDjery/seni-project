package fr.app.seni.core.service;

import fr.app.seni.core.dto.RegionDto;
import fr.app.seni.core.mapper.RegionMapper;
import fr.app.seni.core.repository.RegionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegionService {

    private final RegionMapper regionMapper;
    private final RegionRepository regionRepository;

    public RegionDto create(RegionDto entity) {
        return regionMapper.toDto(regionRepository.save(regionMapper.toEntity(entity)));
    }

    public RegionDto update(RegionDto entity) {
        return regionMapper.toDto(regionRepository.save(regionMapper.toEntity(entity)));
    }

    public void delete(RegionDto entity) {
        regionRepository.delete(regionMapper.toEntity(entity));
    }

    public List<RegionDto> findAll(){
        return regionRepository.findAll().stream().map(regionMapper::toDto).collect(Collectors.toList());
    }

    public RegionDto findOne(String id){
        try {
            return regionMapper.toDto(regionRepository.findById(id).get());
        }catch (Exception e) {
            return null;
        }
    }
}
