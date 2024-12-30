package blog.devjay.logistics.repository.log;

import blog.devjay.logistics.domain.log.Log;
import blog.devjay.logistics.dto.UpdateDTO;
import blog.devjay.logistics.dto.log.SearchLogDTO;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper extends LogRepository {
    @Override
    Long save(Log log);

    @Override
    Optional<Log> findById(Long id);

    @Override
    List<Log> findAll();

    @Override
    List<Log> findAll(SearchLogDTO searchLogDTO);

    @Override
    int findAllCount(SearchLogDTO searchLogDTO);

    @Override
    void update(Long id, UpdateDTO updateDTO);

    @Override
    void delete(Long id);
}
