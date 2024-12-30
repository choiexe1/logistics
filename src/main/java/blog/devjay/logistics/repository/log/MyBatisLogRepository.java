package blog.devjay.logistics.repository.log;

import blog.devjay.logistics.domain.log.Log;
import blog.devjay.logistics.dto.UpdateDTO;
import blog.devjay.logistics.dto.log.SearchLogDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
@RequiredArgsConstructor
public class MyBatisLogRepository implements LogRepository {
    private final LogMapper logMapper;

    @Override
    public Long save(Log log) {
        logMapper.save(log);
        return log.getId();
    }

    @Override
    public Optional<Log> findById(Long id) {
        return logMapper.findById(id);
    }

    @Override
    public List<Log> findAll() {
        return new ArrayList<>(logMapper.findAll());
    }

    @Override
    public List<Log> findAll(SearchLogDTO searchLogDTO) {
        return new ArrayList<>(logMapper.findAll(searchLogDTO));
    }

    @Override
    public int findAllCount(SearchLogDTO searchLogDTO) {
        return logMapper.findAllCount(searchLogDTO);
    }

    @Override
    public void delete(Long id) {
        logMapper.delete(id);
    }

    @Override
    public void update(Long id, UpdateDTO updateDTO) {
    }
}
