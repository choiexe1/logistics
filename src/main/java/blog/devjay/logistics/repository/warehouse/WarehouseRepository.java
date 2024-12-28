package blog.devjay.logistics.repository.warehouse;

import blog.devjay.logistics.domain.warehouse.Warehouse;
import blog.devjay.logistics.dto.warehouse.SearchWarehouseDTO;
import blog.devjay.logistics.dto.warehouse.UpdateWarehouseDTO;
import blog.devjay.logistics.repository.IRepository;

public interface WarehouseRepository extends IRepository<Warehouse, Long, SearchWarehouseDTO, UpdateWarehouseDTO> {
}
