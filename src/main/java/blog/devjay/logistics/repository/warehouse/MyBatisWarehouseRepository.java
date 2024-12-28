package blog.devjay.logistics.repository.warehouse;

import blog.devjay.logistics.domain.warehouse.Warehouse;
import blog.devjay.logistics.dto.warehouse.SearchWarehouseDTO;
import blog.devjay.logistics.dto.warehouse.UpdateWarehouseDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
@RequiredArgsConstructor
public class MyBatisWarehouseRepository implements WarehouseRepository {
    private final WarehouseMapper warehouseMapper;

    @Override
    public Long save(Warehouse warehouse) {
        warehouseMapper.save(warehouse);
        return warehouse.getId();
    }

    @Override
    public Optional<Warehouse> findById(Long id) {
        return warehouseMapper.findById(id);
    }

    @Override
    public int findAllCount(SearchWarehouseDTO searchWarehouseDTO) {
        return warehouseMapper.findAllCount(searchWarehouseDTO);
    }

    @Override
    public List<Warehouse> findAll() {
        return warehouseMapper.findAll();
    }

    @Override
    public List<Warehouse> findAll(SearchWarehouseDTO searchWarehouseDTO) {
        return new ArrayList<>(warehouseMapper.findAll(searchWarehouseDTO));
    }

    @Override
    public void update(Long id, UpdateWarehouseDTO updateWarehouseDTO) {
        warehouseMapper.update(id, updateWarehouseDTO);
    }

    @Override
    public void delete(Long id) {
        warehouseMapper.delete(id);
    }
}
