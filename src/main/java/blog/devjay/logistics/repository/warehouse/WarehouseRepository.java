package blog.devjay.logistics.repository.warehouse;

import blog.devjay.logistics.domain.warehouse.Warehouse;
import blog.devjay.logistics.dto.warehouse.SearchWarehouseDTO;
import blog.devjay.logistics.dto.warehouse.UpdateWarehouseDTO;
import java.util.List;
import java.util.Optional;

public interface WarehouseRepository {
    Long save(Warehouse warehouse);

    Optional<Warehouse> findById(Long id);

    Optional<Warehouse> findByName(String name);

    Optional<Warehouse> findByLocation(String location);

    List<Warehouse> findAll(SearchWarehouseDTO searchWarehouseDTO);

    int findAllCount(SearchWarehouseDTO searchWarehouseDTO);

    void update(Long id, UpdateWarehouseDTO updateWarehouseDTO);
}
