package blog.devjay.logistics.service;

import blog.devjay.logistics.domain.exception.NotFoundException;
import blog.devjay.logistics.domain.item.Item;
import blog.devjay.logistics.dto.item.SearchItemDTO;
import blog.devjay.logistics.dto.item.UpdateItemDTO;
import blog.devjay.logistics.repository.item.ItemRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService implements IService<Item, Long, SearchItemDTO, UpdateItemDTO> {
    private final ItemRepository itemRepository;

    public List<Item> findItemsByWarehouseId(Long warehouseId, SearchItemDTO searchItemDTO) {
        return itemRepository.findItemsByWarehouseId(warehouseId, searchItemDTO);
    }

    @Override
    public void delete(Long id) {
        itemRepository.delete(id);
    }

    @Override
    public void update(Long id, UpdateItemDTO dto) {
        itemRepository.update(id, dto);
    }

    @Override
    public Long create(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item findById(Long id) {
        Optional<Item> item = itemRepository.findById(id);

        if (item.isPresent()) {
            return item.get();
        }

        throw new NotFoundException("아이템을 찾을 수 없습니다.");
    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public List<Item> findAll(SearchItemDTO searchItemDTO) {
        return itemRepository.findAll(searchItemDTO);
    }

    @Override
    public int findAllCount(SearchItemDTO searchItemDTO) {
        return itemRepository.findAllCount(searchItemDTO);
    }
}
