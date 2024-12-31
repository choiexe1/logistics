package blog.devjay.logistics.repository.log;

import blog.devjay.logistics.domain.log.Log;
import blog.devjay.logistics.domain.log.ResponseStatus;
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
        Log log = createLog("test", "/test", "GET");

        // WHEN
        Long id = logRepository.save(log);
        Log findLog = logRepository.findById(id).get();

        // THEN
        Assertions.assertThat(findLog.getUrl()).isEqualTo("/test");
    }

    @Test
    @DisplayName("ID를 통해 정상적으로 조회되어야 한다.")
    void findById() {
        // GIVEN
        Log log = createLog("test", "/test", "GET");
        Long id = logRepository.save(log);

        // WHEN
        Log findLog = logRepository.findById(id).get();

        // THEN
        Assertions.assertThat(findLog.getUrl()).isEqualTo("/test");
    }

    @Test
    @DisplayName("검색 조건을 전달하지 않으면 전체 데이터가 리스트로 반횐되어야 한다.")
    void findAll() {
        // GIVEN
        Log log1 = createLog("test", "/test1", "GET");
        Log log2 = createLog("test", "/test2", "POST");
        logRepository.save(log1);
        logRepository.save(log2);

        // WHEN
        List<Log> logs = logRepository.findAll();

        // THEN
        Assertions.assertThat(logs.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("특정 검색 조건으로 검색 시 대소문자 구분 없이 조건에 맞는 데이터가 리스트로 반횐되어야 한다.")
    void findAllWithCondition() {
        // GIVEN
        Log log1 = createLog("test", "/test1", "GET");
        Log log2 = createLog("java", "/java", "POST");

        logRepository.save(log1);
        logRepository.save(log2);

        SearchLogDTO searchPost = new SearchLogDTO();
        searchPost.setMethod("post");

        SearchLogDTO searchURL = new SearchLogDTO();
        searchURL.setUrl("/JaVa");

        SearchLogDTO searchUsername = new SearchLogDTO();
        searchUsername.setUsername("TEST");

        // WHEN
        List<Log> findMethodPOST = logRepository.findAll(searchPost);
        List<Log> findURL = logRepository.findAll(searchURL);
        List<Log> findUsername = logRepository.findAll(searchUsername);

        // THEN
        Assertions.assertThat(findMethodPOST.size()).isEqualTo(1);
        Assertions.assertThat(findURL.get(0).getUrl()).isEqualTo("/java");
        Assertions.assertThat(findUsername.get(0).getUsername()).isEqualTo("test");
    }

    @Test
    @DisplayName("특정 검색 조건으로 검색 시 조건에 맞는 카운트가 정수로 반환되어야 한다.")
    void findAllCount() {
        // GIVEN
        Log log1 = createLog("test", "/test1", "GET");
        Log log2 = createLog("test", "/test2", "POST");
        logRepository.save(log1);
        logRepository.save(log2);

        SearchLogDTO dto = new SearchLogDTO();
        dto.setUrl("1");

        // WHEN
        int count = logRepository.findAllCount(dto);

        // THEN
        Assertions.assertThat(count).isEqualTo(1);
    }

    private Log createLog(String username, String url, String method) {
        return new Log(username, url, method, null, ResponseStatus.SUCCESS);
    }
}