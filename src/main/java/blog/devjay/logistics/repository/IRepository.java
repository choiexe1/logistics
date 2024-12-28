package blog.devjay.logistics.repository;

import java.util.List;
import java.util.Optional;

public interface IRepository<T, IDType, SearchDTO, UpdateDTO> {
    IDType save(T t);

    Optional<T> findById(IDType id);

    List<T> findAll();

    List<T> findAll(SearchDTO searchDTO);

    int findAllCount(SearchDTO searchDTO);

    void update(IDType id, UpdateDTO updateDTO);

    void delete(IDType id);
}
