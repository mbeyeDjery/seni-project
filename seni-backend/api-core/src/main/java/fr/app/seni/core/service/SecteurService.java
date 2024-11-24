package fr.app.seni.core.service;

import fr.app.seni.core.dto.SecteurDto;
import fr.app.seni.core.mapper.SecteurMapper;

import fr.app.seni.core.repository.SecteurRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SecteurService {

    private final SecteurMapper secteurMapper;
    private final SecteurRepository secteurRepository;

    public SecteurDto create(SecteurDto entity) {
        return secteurMapper.toDto(secteurRepository.save(secteurMapper.toEntity(entity)));
    }

    public SecteurDto update(SecteurDto entity) {
        return secteurMapper.toDto(secteurRepository.save(secteurMapper.toEntity(entity)));
    }

    public void delete(SecteurDto entity) {
        secteurRepository.delete(secteurMapper.toEntity(entity));
    }

    public List<SecteurDto> findAll(){
        return secteurRepository.findAll().stream().map(secteurMapper::toDto).collect(Collectors.toList());
    }

    public SecteurDto findOne(String id){
        try {
            return secteurMapper.toDto(secteurRepository.findById(id).get());
        }catch (Exception e) {
            return null;
        }
    }

    public List<SecteurDto> findByVille(String idVille){
        return secteurRepository.findByVille(idVille).stream().map(secteurMapper::toDto).collect(Collectors.toList());
    }
}
