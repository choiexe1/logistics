package blog.devjay.logistics.repository;

import java.util.List;
import java.util.Optional;

public interface IRepository<T, ID, SearchDTO, UpdateDTO> {
    ID save(T t);

    Optional<T> findById(ID id);

    List<T> findAll();

    List<T> findAll(SearchDTO searchDTO);

    int findAllCount(SearchDTO searchDTO);

    void update(ID id, UpdateDTO updateDTO);

    void delete(ID id);
}
