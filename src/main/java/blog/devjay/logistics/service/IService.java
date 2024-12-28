package blog.devjay.logistics.service;

import blog.devjay.logistics.domain.exception.NotFoundException;
import blog.devjay.logistics.dto.SearchDTO;
import blog.devjay.logistics.dto.UpdateDTO;
import java.util.List;

public interface IService<T, IDType, SearchType extends SearchDTO, UpdateType extends UpdateDTO> {
    IDType create(T t);

    T findById(IDType id) throws NotFoundException;

    List<T> findAll();

    List<T> findAll(SearchType dto);

    int findAllCount(SearchType dto);

    void delete(IDType id);

    void update(IDType id, UpdateType dto);
}
