package blog.devjay.logistics.repository.item;

import blog.devjay.logistics.domain.item.Item;
import blog.devjay.logistics.dto.item.SearchItemDTO;
import blog.devjay.logistics.dto.item.UpdateItemDTO;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ItemMapper extends ItemRepository {
    @Override
    Long save(Item item);

    @Override
    Optional<Item> findById(Long id);

    @Override
    List<Item> findAll();

    @Override
    List<Item> findAll(SearchItemDTO searchItemDTO);

    @Override
    int findAllCount();

    @Override
    int findAllCount(SearchItemDTO searchItemDTO);

    @Override
    void update(UpdateItemDTO updateItemDTO);
}
