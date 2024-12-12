package fr.app.seni.core.service;


import fr.app.seni.core.dto.UserHopitalDto;
import fr.app.seni.core.mapper.UserHopitalMapper;
import fr.app.seni.core.repository.UserHopitalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserHopitalService {

    private final UserHopitalMapper userHopitalMapper;
    private final UserHopitalRepository userHopitalRepository;

    public UserHopitalDto create(UserHopitalDto entity) {
        return userHopitalMapper.toDto(userHopitalRepository.save(userHopitalMapper.toEntity(entity)));
    }

    public UserHopitalDto update(UserHopitalDto entity) {
        return userHopitalMapper.toDto(userHopitalRepository.save(userHopitalMapper.toEntity(entity)));
    }

    public void delete(UserHopitalDto entity) {
        userHopitalRepository.delete(userHopitalMapper.toEntity(entity));
    }

    public UserHopitalDto findOneByUser(String idUser) {
        return userHopitalMapper.toDto(userHopitalRepository.findByIdUser(idUser));
    }

    public List<UserHopitalDto> findByHopital(String idHopital) {
        return userHopitalRepository.findByHopital(idHopital).stream().map(userHopitalMapper::toDto).toList();
    }
}
