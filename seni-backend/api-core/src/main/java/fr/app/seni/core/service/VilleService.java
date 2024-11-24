package fr.app.seni.core.service;

import fr.app.seni.core.dto.VilleDto;
import fr.app.seni.core.mapper.VilleMapper;
import fr.app.seni.core.repository.VilleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class VilleService {

    private final VilleMapper villeMapper;
    private final VilleRepository villeRepository;

    public VilleDto create(VilleDto entity) {
        return villeMapper.toDto(villeRepository.save(villeMapper.toEntity(entity)));
    }

    public VilleDto update(VilleDto entity) {
        return villeMapper.toDto(villeRepository.save(villeMapper.toEntity(entity)));
    }

    public void delete(VilleDto entity) {
        villeRepository.delete(villeMapper.toEntity(entity));
    }

    public List<VilleDto> findAll(){
        return villeRepository.findAll().stream().map(villeMapper::toDto).collect(Collectors.toList());
    }

    public VilleDto findOne(String id){
        try {
            return villeMapper.toDto(villeRepository.findById(id).get());
        }catch (Exception e) {
            return null;
        }
    }

    public List<VilleDto> findByProvince(String idProvince){
        return villeRepository.findByProvince(idProvince).stream().map(villeMapper::toDto).collect(Collectors.toList());
    }
}
