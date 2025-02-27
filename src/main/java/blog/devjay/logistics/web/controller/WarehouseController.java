package blog.devjay.logistics.web.controller;

import blog.devjay.logistics.domain.exception.NotFoundException;
import blog.devjay.logistics.domain.warehouse.Warehouse;
import blog.devjay.logistics.dto.item.SearchItemDTO;
import blog.devjay.logistics.dto.warehouse.CreateWarehouseDTO;
import blog.devjay.logistics.dto.warehouse.SearchWarehouseDTO;
import blog.devjay.logistics.dto.warehouse.UpdateWarehouseDTO;
import blog.devjay.logistics.service.ItemService;
import blog.devjay.logistics.service.WarehouseService;
import blog.devjay.logistics.web.aop.annotation.Logging;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/warehouse")
@RequiredArgsConstructor
@Slf4j
public class WarehouseController {
    private final WarehouseService warehouseService;
    private final ItemService itemService;

    @GetMapping
    public String indexView(Model model, @ModelAttribute("searchForm") SearchWarehouseDTO dto) {
        dto.setPagination(model, warehouseService.findAllCount(dto));
        model.addAttribute("warehouses", warehouseService.findAll(dto));
        return "views/warehouse/index";
    }

    @GetMapping("/{warehouseId}")
    public String warehouseInfoView(Model model, @PathVariable("warehouseId") Long warehouseId,
                                    @ModelAttribute("search") SearchItemDTO searchItemDTO,
                                    @ModelAttribute("updateWarehouseDTO") UpdateWarehouseDTO dto) {

        try {
            Warehouse warehouse = warehouseService.findById(warehouseId);
            dto.setName(warehouse.getName());
            dto.setLocation(warehouse.getLocation());

            model.addAttribute("items", itemService.findItemsByWarehouseId(warehouseId, searchItemDTO));
            model.addAttribute("warehouse", warehouse);
            searchItemDTO.setPagination(model, itemService.findAllCount(searchItemDTO));
            return "views/warehouse/info";
        } catch (NotFoundException e) {
            return "redirect:/warehouse";
        }
    }

    @Logging
    @PostMapping("/update/{warehouseId}")
    public String updateWarehouse(@PathVariable("warehouseId") Long warehouseId,
                                  @Validated @ModelAttribute("updateWarehouseDTO") UpdateWarehouseDTO dto,
                                  BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            log.error("error = {}", bindingResult.getAllErrors());
            return "views/warehouse/info";
        }

        try {
            warehouseService.update(warehouseId, dto);
            redirectAttributes.addAttribute("warehouseId", warehouseId);
            return "redirect:/warehouse/{warehouseId}";
        } catch (DuplicateKeyException e) {
            bindingResult.rejectValue("name", "warehouse.name.exist");
            return "views/warehouse/info";
        }
    }


    @GetMapping("/create")
    public String createView(@ModelAttribute("warehouse") CreateWarehouseDTO dto) {
        return "views/warehouse/create";
    }

    @Logging
    @PostMapping("/create")
    public String create(@Validated @ModelAttribute("warehouse") CreateWarehouseDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("error = {}", bindingResult.getAllErrors());
            return "views/warehouse/create";
        }

        Warehouse warehouse = new Warehouse(dto.getName(), dto.getLocation());

        try {
            warehouseService.create(warehouse);
        } catch (DuplicateKeyException e) {
            bindingResult.rejectValue("name", "warehouse.name.exist");
            return "views/warehouse/create";
        }

        return "redirect:/warehouse";
    }

    @Logging
    @PostMapping("/delete/{warehouseId}")
    public String delete(@PathVariable("warehouseId") Long warehouseId) {
        try {
            warehouseService.delete(warehouseId);
        } catch (RuntimeException e) {
            log.error("e", e);
        }

        return "redirect:/warehouse";
    }
}
