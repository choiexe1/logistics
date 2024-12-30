package blog.devjay.logistics.repository.item;

import blog.devjay.logistics.domain.item.Item;
import blog.devjay.logistics.domain.warehouse.Warehouse;
import blog.devjay.logistics.dto.item.SearchItemDTO;
import blog.devjay.logistics.dto.item.UpdateItemDTO;
import blog.devjay.logistics.repository.warehouse.WarehouseRepository;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class ItemRepositoryTest {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;

    @Test
    @DisplayName("아이템이 정상적으로 저장되어야 한다.")
    void create() {
        // GIVEN
        Item item = new Item("itemA", 1000, 10);

        // WHEN
        Long id = itemRepository.save(item);

        // THEN
        Item findItem = itemRepository.findById(id).get();
        Assertions.assertThat(findItem.getName()).isEqualTo(item.getName());
    }

    @Test
    @DisplayName("아이디로 아이템이 조회되어야 한다.")
    void findById() {
        // GIVEN
        Item item = new Item("itemA", 1000, 10);
        Long id = itemRepository.save(item);

        // WHEN
        Optional<Item> findItem = itemRepository.findById(id);

        // THEN
        Assertions.assertThat(findItem).isPresent();
    }

    @Test
    @DisplayName("모든 아이템이 리스트로 반환되어야 한다.")
    void findAll() {
        // GIVEN
        Item itemA = new Item("itemA", 1000, 10);
        Item itemB = new Item("itemB", 1000, 10);

        itemRepository.save(itemA);
        itemRepository.save(itemB);

        // WHEN
        List<Item> items = itemRepository.findAll();

        // THEN
        Assertions.assertThat(items.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("아이템 정보를 업데이트하면 정상적으로 반영되어야 한다.")
    void update() {
        // GIVEN
        Item itemA = new Item("itemA", 1000, 10);
        Long id = itemRepository.save(itemA);
        Long warehouseId = setWarehouse("TEST-A");

        Item item = itemRepository.findById(id).get();

        // WHEN
        UpdateItemDTO updateItemDTO = new UpdateItemDTO();
        updateItemDTO.setName("TEST");
        updateItemDTO.setWarehouseId(warehouseId);

        itemRepository.update(id, updateItemDTO);

        Item updatedItem = itemRepository.findById(id).get();

        // THEN
        Assertions.assertThat(updatedItem.getWarehouseId()).isEqualTo(1L);
        Assertions.assertThat(updatedItem.getName()).isEqualTo("TEST");
    }

    private Long setWarehouse(String name) {
        Warehouse warehouse = new Warehouse(name, "Seoul");
        Long warehouseId = warehouseRepository.save(warehouse);
        return warehouseId;
    }

    @Test
    @DisplayName("특정 조건으로 검색 시 해당하는 아이템만 리스트로 반환되어야 한다.")
    void findAllWithCondition() {
        // GIVEN
        Item itemA = new Item("itemA", 1000, 10);
        Item itemB = new Item("itemB", 50000, 10);
        Long warehouseId = setWarehouse("TEST-A");

        itemA.setWarehouseId(warehouseId);
        itemB.setWarehouseId(warehouseId);
        itemRepository.save(itemA);
        itemRepository.save(itemB);

        // WHEN
        SearchItemDTO dto = new SearchItemDTO();
        dto.setWarehouseId(warehouseId);
        dto.setPrice(30000);

        List<Item> items = itemRepository.findAll(dto);

        // THEN
        Assertions.assertThat(items.size()).isEqualTo(1);
        Assertions.assertThat(items.get(0).getName()).isEqualTo(itemB.getName());
    }


}