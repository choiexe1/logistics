package blog.devjay.logistics.service;

import blog.devjay.logistics.domain.item.Item;
import blog.devjay.logistics.dto.item.SearchItemDTO;
import blog.devjay.logistics.repository.item.ItemRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    public Long create(Item item) {
        return itemRepository.save(item);
    }

    public Optional<Item> findById(Long id) {
        return itemRepository.findById(id);
    }

    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    public List<Item> findAll(SearchItemDTO searchItemDTO) {
        return itemRepository.findAll(searchItemDTO);
    }

    public List<Item> findItemsByWarehouseId(Long warehouseId) {
        return itemRepository.findItemsByWarehouseId(warehouseId);
    }

    public int findAllCount(SearchItemDTO searchItemDTO) {
        return itemRepository.findAllCount(searchItemDTO);
    }
}
