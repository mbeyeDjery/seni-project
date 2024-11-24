package fr.app.seni.core.service;

import fr.app.seni.core.dto.NotationHopitalDto;
import fr.app.seni.core.mapper.NotationHopitalMapper;
import fr.app.seni.core.repository.NotationHopitalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class NotationHopitalService {

    private final NotationHopitalMapper notationHopitalMapper;
    private final NotationHopitalRepository notationHopitalRepository;

    public NotationHopitalDto create(NotationHopitalDto entity) {
        return notationHopitalMapper.toDto(notationHopitalRepository.save(notationHopitalMapper.toEntity(entity)));
    }

    public NotationHopitalDto update(NotationHopitalDto entity) {
        return notationHopitalMapper.toDto(notationHopitalRepository.save(notationHopitalMapper.toEntity(entity)));
    }

    public void delete(NotationHopitalDto entity) {
        notationHopitalRepository.delete(notationHopitalMapper.toEntity(entity));
    }

    public List<NotationHopitalDto> findAll(){
        return notationHopitalRepository.findAll().stream().map(notationHopitalMapper::toDto).collect(Collectors.toList());
    }

    public NotationHopitalDto findOne(String id){
        try {
            return notationHopitalMapper.toDto(notationHopitalRepository.findById(id).get());
        }catch (Exception e) {
            return null;
        }
    }

    public List<NotationHopitalDto> findByHopital(String idHopital){
        return notationHopitalRepository.findByHopital(idHopital).stream().map(notationHopitalMapper::toDto).collect(Collectors.toList());
    }
}
