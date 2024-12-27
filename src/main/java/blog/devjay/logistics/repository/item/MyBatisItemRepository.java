package blog.devjay.logistics.repository.item;

import blog.devjay.logistics.domain.item.Item;
import blog.devjay.logistics.dto.item.SearchItemDTO;
import blog.devjay.logistics.dto.item.UpdateItemDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Primary
@Repository
@RequiredArgsConstructor
public class MyBatisItemRepository implements ItemRepository {
    private final ItemMapper itemMapper;

    @Override
    public Long save(Item item) {
        itemMapper.save(item);

        return item.getId();
    }

    @Override
    public List<Item> findItemsByWarehouseId(Long warehouseId) {
        return itemMapper.findItemsByWarehouseId(warehouseId);
    }

    @Override
    public Optional<Item> findById(Long id) {
        return itemMapper.findById(id);
    }

    @Override
    public List<Item> findAll() {
        return new ArrayList<>(itemMapper.findAll());
    }

    @Override
    public List<Item> findAll(SearchItemDTO searchItemDTO) {
        return new ArrayList<>(itemMapper.findAll(searchItemDTO));
    }

    @Override
    public int findAllCount() {
        return itemMapper.findAllCount();
    }

    @Override
    public int findAllCount(SearchItemDTO searchItemDTO) {
        return itemMapper.findAllCount(searchItemDTO);
    }

    @Override
    public void update(UpdateItemDTO updateItemDTO) {
        itemMapper.update(updateItemDTO);
    }
}
