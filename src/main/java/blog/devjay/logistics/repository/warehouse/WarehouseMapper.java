package blog.devjay.logistics.repository.warehouse;

import blog.devjay.logistics.domain.warehouse.Warehouse;
import blog.devjay.logistics.dto.warehouse.SearchWarehouseDTO;
import blog.devjay.logistics.dto.warehouse.UpdateWarehouseDTO;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WarehouseMapper extends WarehouseRepository {
    @Override
    Long save(Warehouse warehouse);

    @Override
    Optional<Warehouse> findById(Long id);

    @Override
    List<Warehouse> findAll();

    @Override
    List<Warehouse> findAll(SearchWarehouseDTO searchWarehouseDTO);

    @Override
    int findAllCount(SearchWarehouseDTO searchWarehouseDTO);

    @Override
    void update(Long id, UpdateWarehouseDTO updateWarehouseDTO);

    @Override
    void delete(Long id);
}
