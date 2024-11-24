package fr.app.seni.core.service;

import fr.app.seni.core.dto.ProvinceDto;
import fr.app.seni.core.mapper.ProvinceMapper;
import fr.app.seni.core.repository.ProvinceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProvinceService {

    private final ProvinceMapper provinceMapper;
    private final ProvinceRepository provinceRepository;

    public ProvinceDto create(ProvinceDto entity) {
        return provinceMapper.toDto(provinceRepository.save(provinceMapper.toEntity(entity)));
    }

    public ProvinceDto update(ProvinceDto entity) {
        return provinceMapper.toDto(provinceRepository.save(provinceMapper.toEntity(entity)));
    }

    public void delete(ProvinceDto entity) {
        provinceRepository.delete(provinceMapper.toEntity(entity));
    }

    public List<ProvinceDto> findAll(){
        return provinceRepository.findAll().stream().map(provinceMapper::toDto).collect(Collectors.toList());
    }

    public ProvinceDto findOne(String id){
        try {
            return provinceMapper.toDto(provinceRepository.findById(id).get());
        }catch (Exception e) {
            return null;
        }
    }

    public List<ProvinceDto> findByRegion(String idRegion){
        return provinceRepository.findByRegion(idRegion).stream().map(provinceMapper::toDto).collect(Collectors.toList());
    }
}
