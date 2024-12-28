package blog.devjay.logistics.repository;

import blog.devjay.logistics.dto.SearchDTO;
import blog.devjay.logistics.dto.UpdateDTO;
import java.util.List;
import java.util.Optional;

public interface IRepository<T, IDType, SearchType extends SearchDTO, UpdateType extends UpdateDTO> {
    IDType save(T t);

    Optional<T> findById(IDType id);

    List<T> findAll();

    List<T> findAll(SearchType searchType);

    int findAllCount(SearchType searchType);

    void update(IDType id, UpdateType updateType);

    void delete(IDType id);
}
