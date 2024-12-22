package blog.devjay.logistics.service;

import blog.devjay.logistics.domain.warehouse.Warehouse;
import blog.devjay.logistics.dto.warehouse.SearchWarehouseDTO;
import blog.devjay.logistics.repository.warehouse.WarehouseMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class WarehouseService {
    private final WarehouseMapper mapper;

    public Long create(Warehouse warehouse) {
        return mapper.save(warehouse);
    }

    public List<Warehouse> findAll(SearchWarehouseDTO searchWarehouseDTO) {
        return mapper.findAll(searchWarehouseDTO);
    }

    public int findAllCount(SearchWarehouseDTO searchWarehouseDTO) {
        return mapper.findAllCount(searchWarehouseDTO);
    }
}
