package blog.devjay.logistics.repository.item;

import blog.devjay.logistics.domain.item.Item;
import blog.devjay.logistics.dto.item.SearchItemDTO;
import blog.devjay.logistics.dto.item.UpdateItemDTO;
import java.util.List;
import java.util.Optional;

public interface ItemRepository {
    Long save(Item item);

    Optional<Item> findById(Long id);

    List<Item> findAll();

    List<Item> findAll(SearchItemDTO searchItemDTO);

    int findAllCount();

    int findAllCount(SearchItemDTO searchItemDTO);

    void update(UpdateItemDTO updateItemDTO);
}
