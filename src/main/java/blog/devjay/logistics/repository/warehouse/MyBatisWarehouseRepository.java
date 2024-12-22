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
    private final WarehouseMapper mapper;

    @Override
    public Long save(Warehouse warehouse) {
        mapper.save(warehouse);
        return warehouse.getId();
    }

    @Override
    public Optional<Warehouse> findById(Long id) {
        return mapper.findById(id);
    }

    @Override
    public int findAllCount(SearchWarehouseDTO searchWarehouseDTO) {
        return mapper.findAllCount(searchWarehouseDTO);
    }

    @Override
    public Optional<Warehouse> findByName(String name) {
        return mapper.findByName(name);
    }

    @Override
    public Optional<Warehouse> findByLocation(String location) {
        return mapper.findByLocation(location);
    }

    @Override
    public List<Warehouse> findAll(SearchWarehouseDTO searchWarehouseDTO) {
        return new ArrayList<>(mapper.findAll(searchWarehouseDTO));
    }

    @Override
    public void update(UpdateWarehouseDTO updateWarehouseDTO) {
        mapper.update(updateWarehouseDTO);
    }
}
