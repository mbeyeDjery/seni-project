package fr.app.seni.core.service;


import fr.app.seni.core.dto.UserHopitalDto;
import fr.app.seni.core.mapper.UserHopitalMapper;
import fr.app.seni.core.repository.UserHopitalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserHopitalService {

    private final UserHopitalMapper userHopitalMapper;
    private final UserHopitalRepository userHopitalRepository;

    public UserHopitalDto create(UserHopitalDto userHopitalDto) {
        return userHopitalMapper.toDto(userHopitalRepository.save(userHopitalMapper.toEntity(userHopitalDto)));
    }

    public UserHopitalDto update(UserHopitalDto userHopitalDto) {
        return userHopitalMapper.toDto(userHopitalRepository.save(userHopitalMapper.toEntity(userHopitalDto)));
    }

    public void delete(UserHopitalDto userHopitalDto) {
        userHopitalRepository.delete(userHopitalMapper.toEntity(userHopitalDto));
    }
}
