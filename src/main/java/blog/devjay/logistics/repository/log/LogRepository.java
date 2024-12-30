package blog.devjay.logistics.repository.log;

import blog.devjay.logistics.domain.log.Log;
import blog.devjay.logistics.dto.UpdateDTO;
import blog.devjay.logistics.dto.log.SearchLogDTO;
import blog.devjay.logistics.repository.IRepository;

public interface LogRepository extends IRepository<Log, Long, SearchLogDTO, UpdateDTO> {
}
