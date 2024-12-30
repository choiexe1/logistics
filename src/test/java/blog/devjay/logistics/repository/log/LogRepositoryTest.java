package blog.devjay.logistics.repository.log;

import blog.devjay.logistics.domain.log.Log;
import blog.devjay.logistics.dto.log.SearchLogDTO;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class LogRepositoryTest {
    @Autowired
    LogMapper logMapper;
    @Autowired
    LogRepository logRepository;

    @Test
    @DisplayName("정상적으로 저장되어야 한다.")
    void save() {
        // GIVEN
        Log log = new Log("test", "/abc", "GET", "abc", 200, null);

        // WHEN
        Long id = logRepository.save(log);
        Log findLog = logRepository.findById(id).get();

        // THEN
        Assertions.assertThat(findLog.getUrl()).isEqualTo("/abc");
    }

    @Test
    @DisplayName("ID를 통해 정상적으로 조회되어야 한다.")
    void findById() {
        // GIVEN
        Log log = new Log("test", "/abc", "GET", "abc", 200, null);
        Long id = logRepository.save(log);

        // WHEN
        Log findLog = logRepository.findById(id).get();

        // THEN
        Assertions.assertThat(findLog.getUrl()).isEqualTo("/abc");
    }

    @Test
    @DisplayName("검색 조건을 전달하지 않으면 전체 데이터가 리스트로 반횐되어야 한다.")
    void findAll() {
        // GIVEN
        Log log1 = new Log("test", "/abc", "GET", "abc", 200, null);
        Log log2 = new Log("test", "/abc", "GET", "abc", 200, null);
        logRepository.save(log1);
        logRepository.save(log2);

        // WHEN
        List<Log> logs = logRepository.findAll();

        // THEN
        Assertions.assertThat(logs.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("특정 검색 조건으로 검색 시 조건에 맞는 데이터가 리스트로 반횐되어야 한다.")
    void findAllWithCondition() {
        // GIVEN
        Log log1 = new Log("test", "/abc1", "GET", "abc", 200, null);
        Log log2 = new Log("condition", "/abc2", "POST", "abc", 200, null);
        logRepository.save(log1);
        logRepository.save(log2);

        SearchLogDTO dto = new SearchLogDTO();
        dto.setMethod("post");

        // WHEN
        List<Log> logs = logRepository.findAll(dto);

        // THEN
        Assertions.assertThat(logs.size()).isEqualTo(1);
        Assertions.assertThat(logs.get(0).getUsername()).isEqualTo("condition");
    }

    @Test
    @DisplayName("특정 검색 조건으로 검색 시 조건에 맞는 카운트가 반환되어야 한다.")
    void findAllCount() {
        // GIVEN
        Log log1 = new Log("test", "/abc", "GET", "abc", 200, null);
        Log log2 = new Log("test", "/def", "GET", "abc", 200, null);
        logRepository.save(log1);
        logRepository.save(log2);

        SearchLogDTO dto = new SearchLogDTO();
        dto.setUrl("/abc");

        // WHEN
        int count = logRepository.findAllCount(dto);

        // THEN
        Assertions.assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("정상적으로 삭제 되어야 한다.")
    void delete() {
        // GIVEN
        Log log = new Log("test", "/abc", "GET", "abc", 200, null);
        Long id = logRepository.save(log);

        // WHEN
        logRepository.delete(id);
        int size = logRepository.findAll().size();

        // THEN
        Assertions.assertThat(size).isEqualTo(0);
    }
}