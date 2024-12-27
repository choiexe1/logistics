package blog.devjay.logistics.service;

import blog.devjay.logistics.domain.exception.NotFoundException;
import blog.devjay.logistics.domain.warehouse.Warehouse;
import blog.devjay.logistics.dto.warehouse.SearchWarehouseDTO;
import blog.devjay.logistics.dto.warehouse.UpdateWarehouseDTO;
import blog.devjay.logistics.repository.warehouse.WarehouseRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;

    public Long create(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    public Warehouse findById(Long id) {
        Optional<Warehouse> optional = warehouseRepository.findById(id);

        if (optional.isEmpty()) {
            throw new NotFoundException("창고를 찾을 수 없습니다.");
        }

        return optional.get();
    }

    public List<Warehouse> findAll(SearchWarehouseDTO searchWarehouseDTO) {
        return warehouseRepository.findAll(searchWarehouseDTO);
    }

    public List<Warehouse> findAll() {
        return warehouseRepository.findAll();
    }

    public int findAllCount(SearchWarehouseDTO searchWarehouseDTO) {
        return warehouseRepository.findAllCount(searchWarehouseDTO);
    }

    public void update(Long id, UpdateWarehouseDTO updateWarehouseDTO) {
        warehouseRepository.update(id, updateWarehouseDTO);
    }

    public void delete(Long id) {
        warehouseRepository.delete(id);
    }
}
