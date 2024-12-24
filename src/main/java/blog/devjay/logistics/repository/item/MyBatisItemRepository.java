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
    private final ItemMapper mapper;

    @Override
    public Long save(Item item) {
        mapper.save(item);

        return item.getId();
    }

    @Override
    public Optional<Item> findById(Long id) {
        return mapper.findById(id);
    }

    @Override
    public List<Item> findAll() {
        return new ArrayList<>(mapper.findAll());
    }

    @Override
    public List<Item> findAll(SearchItemDTO searchItemDTO) {
        return new ArrayList<>(mapper.findAll(searchItemDTO));
    }

    @Override
    public int findAllCount() {
        return 0;
    }

    @Override
    public int findAllCount(SearchItemDTO searchItemDTO) {
        return 0;
    }

    @Override
    public void update(UpdateItemDTO updateItemDTO) {
        mapper.update(updateItemDTO);
    }
}
