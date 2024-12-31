package blog.devjay.logistics.web.controller;

import blog.devjay.logistics.domain.exception.NotFoundException;
import blog.devjay.logistics.domain.item.Item;
import blog.devjay.logistics.domain.warehouse.Warehouse;
import blog.devjay.logistics.dto.item.CreateItemDTO;
import blog.devjay.logistics.dto.item.SearchItemDTO;
import blog.devjay.logistics.dto.item.UpdateItemDTO;
import blog.devjay.logistics.service.ItemService;
import blog.devjay.logistics.service.WarehouseService;
import java.util.List;
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

@Slf4j
@Controller
@RequestMapping("/logistics")
@RequiredArgsConstructor
public class LogisticsController {

    private final ItemService itemService;
    private final WarehouseService warehouseService;

    @GetMapping
    public String index(Model model, @ModelAttribute("search") SearchItemDTO dto) {
        List<Warehouse> warehouses = warehouseService.findAll();

        model.addAttribute("warehouses", warehouses);
        int totalPageCount = itemService.findAllCount(dto);
        dto.setPagination(model, totalPageCount);
        model.addAttribute("items", itemService.findAll(dto));

        return "views/logistics/index";
    }

    @GetMapping("/create")
    public String createView(@ModelAttribute("createItemDTO") CreateItemDTO dto) {
        return "views/logistics/create";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute("createItemDTO") CreateItemDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("error = {}", bindingResult.getAllErrors());
            return "views/logistics/create";
        }

        Item item = new Item(dto.getName(), dto.getPrice(), dto.getQuantity());

        try {
            itemService.create(item);
        } catch (DuplicateKeyException e) {
            bindingResult.rejectValue("name", "item.name.exist");
            return "views/logistics/create";
        }

        return "redirect:/logistics";
    }

    @GetMapping("/{itemId}")
    public String updateView(@PathVariable("itemId") Long itemId,
                             Model model) {
        Item item = itemService.findById(itemId);

        if (!model.containsAttribute("updateItemDTO")) {
            UpdateItemDTO dto = new UpdateItemDTO();
            dto.setName(item.getName());
            dto.setPrice(item.getPrice());
            dto.setQuantity(item.getQuantity());
            dto.setWarehouseId(item.getWarehouseId());
            model.addAttribute("updateItemDTO", dto);
        }

        // Flash Attribute에 없는 경우에만 추가
        if (!model.containsAttribute("item")) {
            model.addAttribute("item", item);
        }
        if (!model.containsAttribute("warehouses")) {
            model.addAttribute("warehouses", warehouseService.findAll());
        }
        if (!model.containsAttribute("warehouse")) {
            try {
                model.addAttribute("warehouse", warehouseService.findById(item.getWarehouseId()));
            } catch (NotFoundException e) {
                model.addAttribute("warehouse", null);
            }
        }

        return "views/logistics/info";
    }

    @PostMapping("/update/{itemId}")
    public String updateItem(@PathVariable("itemId") Long itemId,
                             @Validated @ModelAttribute("updateItemDTO") UpdateItemDTO dto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {

        redirectAttributes.addAttribute("itemId", itemId);

        if (bindingResult.hasErrors()) {
            log.error("error = {}", bindingResult.getAllErrors());

            Item item = itemService.findById(itemId);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.updateItemDTO",
                    bindingResult);
            redirectAttributes.addFlashAttribute("updateItemDTO", dto);
            redirectAttributes.addFlashAttribute("item", item);
            redirectAttributes.addFlashAttribute("warehouses", warehouseService.findAll());
            redirectAttributes.addFlashAttribute("warehouse", warehouseService.findById(item.getWarehouseId()));

            return "redirect:/logistics/{itemId}";
        }

        try {
            itemService.update(itemId, dto);
        } catch (RuntimeException e) {
            log.error("e", e);
            return "redirect:/logistics/{itemId}";
        }

        return "redirect:/logistics/{itemId}";
    }

    @PostMapping("/delete/{itemId}")
    public String delete(@PathVariable("itemId") Long itemId) {
        try {
            itemService.delete(itemId);
        } catch (RuntimeException e) {
            log.error("e", e);
        }

        return "redirect:/logistics";
    }
}
