package blog.devjay.logistics.repository.warehouse;

import blog.devjay.logistics.domain.warehouse.Warehouse;
import blog.devjay.logistics.dto.warehouse.UpdateWarehouseDTO;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class WarehouseRepositoryTest {
    @Autowired
    private WarehouseRepository warehouseRepository;

    @Test
    @DisplayName("창고가 정상적으로 저장되어야 한다.")
    void save() {
        // GIVEN
        Warehouse warehouse = createWarehouse("창고1");

        // WHEN
        Long id = warehouseRepository.save(warehouse);

        // THEN
        Warehouse findWarehouse = warehouseRepository.findById(id).get();
        Assertions.assertThat(findWarehouse.getName()).isEqualTo(warehouse.getName());
    }

    @Test
    @DisplayName("아이디로 창고가 조회되어야 한다.")
    void findById() {
        // GIVEN
        Warehouse warehouse = createWarehouse("창고1");
        Long id = warehouseRepository.save(warehouse);

        // WHEN
        Warehouse findWarehouse = warehouseRepository.findById(id).get();

        // THEN
        Assertions.assertThat(findWarehouse.getName()).isEqualTo(warehouse.getName());
    }

    @Test
    @DisplayName("이름으로 창고가 조회되어야 한다.")
    void findByName() {
        // GIVEN
        Warehouse warehouse = createWarehouse("창고1");
        Long id = warehouseRepository.save(warehouse);

        // WHEN
        Warehouse findWarehouse = warehouseRepository.findByName("창고1").get();

        // THEN
        Assertions.assertThat(findWarehouse.getName()).isEqualTo(warehouse.getName());
    }

    @Test
    @DisplayName("위치로 창고가 조회되어야 한다.")
    void findByLocation() {
        // GIVEN
        Warehouse warehouse = createWarehouse("창고1");
        Long id = warehouseRepository.save(warehouse);

        // WHEN
        Warehouse findWarehouse = warehouseRepository.findByName("창고1").get();

        // THEN
        Assertions.assertThat(findWarehouse.getName()).isEqualTo(warehouse.getName());
    }

    @Test
    @DisplayName("모든 창고가 리스트로 반환되어야 한다.")
    void findAll() {
        // GIVEN
        Warehouse warehouse1 = createWarehouse("창고1");
        Warehouse warehouse2 = createWarehouse("창고2");
        warehouseRepository.save(warehouse1);
        warehouseRepository.save(warehouse2);

        // WHEN
        List<Warehouse> warehouses = warehouseRepository.findAll();

        // THEN
        Assertions.assertThat(warehouses.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("창고 정보를 업데이트하면 정상적으로 반영되어야 한다.")
    void update() {
        // GIVEN
        Warehouse warehouse = createWarehouse("창고1");
        Long id = warehouseRepository.save(warehouse);

        // WHEN
        UpdateWarehouseDTO updateWarehouseDTO = new UpdateWarehouseDTO();
        updateWarehouseDTO.setName("ABC");
        updateWarehouseDTO.setLocation("DEF");
        updateWarehouseDTO.setId(id);

        warehouseRepository.update(updateWarehouseDTO);

        Warehouse findWarehouse = warehouseRepository.findById(id).get();

        // THEN
        Assertions.assertThat(findWarehouse.getName()).isEqualTo("ABC");
        Assertions.assertThat(findWarehouse.getLocation()).isEqualTo("DEF");
    }

    private Warehouse createWarehouse(String name) {
        return new Warehouse(name, name);
    }
}