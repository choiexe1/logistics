package blog.devjay.logistics.repository.item;

import blog.devjay.logistics.domain.item.Item;
import blog.devjay.logistics.dto.item.SearchItemDTO;
import blog.devjay.logistics.dto.item.UpdateItemDTO;
import blog.devjay.logistics.repository.IRepository;
import java.util.List;
import java.util.Optional;

public interface ItemRepository extends IRepository<Item, Long, SearchItemDTO, UpdateItemDTO> {
    List<Item> findItemsByWarehouseId(Long warehouseId, SearchItemDTO searchItemDTO);

    Optional<Item> findByIdForUpdate(Long id);
}
