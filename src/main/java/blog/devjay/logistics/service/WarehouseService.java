package blog.devjay.logistics.service;

import blog.devjay.logistics.domain.exception.NotFoundException;
import blog.devjay.logistics.domain.warehouse.Warehouse;
import blog.devjay.logistics.dto.warehouse.SearchWarehouseDTO;
import blog.devjay.logistics.dto.warehouse.UpdateWarehouseDTO;
import blog.devjay.logistics.repository.warehouse.WarehouseMapper;
import java.util.List;
import java.util.Optional;
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

    public Warehouse findById(Long id) {
        Optional<Warehouse> optional = mapper.findById(id);

        if (optional.isEmpty()) {
            throw new NotFoundException("창고를 찾을 수 없습니다.");
        }

        return optional.get();
    }


    public List<Warehouse> findAll(SearchWarehouseDTO searchWarehouseDTO) {
        return mapper.findAll(searchWarehouseDTO);
    }

    public int findAllCount(SearchWarehouseDTO searchWarehouseDTO) {
        return mapper.findAllCount(searchWarehouseDTO);
    }

    public void update(Long id, UpdateWarehouseDTO updateWarehouseDTO) {
        mapper.update(id, updateWarehouseDTO);
    }
}
