package blog.devjay.logistics.service;

import blog.devjay.logistics.domain.exception.NotFoundException;
import blog.devjay.logistics.domain.log.Log;
import blog.devjay.logistics.dto.UpdateDTO;
import blog.devjay.logistics.dto.log.SearchLogDTO;
import blog.devjay.logistics.repository.log.LogRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LogService implements IService<Log, Long, SearchLogDTO, UpdateDTO> {
    private final LogRepository logRepository;

    @Override
    public Long create(Log log) {
        return logRepository.save(log);
    }

    @Override
    public Log findById(Long id) throws NotFoundException {
        Optional<Log> optional = logRepository.findById(id);

        if (optional.isPresent()) {
            return optional.get();
        }

        throw new NotFoundException("로그가 존재하지 않습니다.");
    }

    @Override
    public List<Log> findAll() {
        return logRepository.findAll();
    }

    @Override
    public List<Log> findAll(SearchLogDTO dto) {
        return logRepository.findAll(dto);
    }

    @Override
    public int findAllCount(SearchLogDTO dto) {
        return logRepository.findAllCount(dto);
    }

    @Override
    public void delete(Long id) {
        Log log = findById(id);
        logRepository.delete(log.getId());
    }

    @Override
    public void update(Long id, UpdateDTO dto) {
        throw new UnsupportedOperationException("업데이트 메서드는 지원하지 않습니다.");
    }
}
