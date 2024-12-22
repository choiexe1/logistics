package blog.devjay.logistics.web.controller;

import blog.devjay.logistics.dto.warehouse.SearchWarehouseDTO;
import blog.devjay.logistics.service.WarehouseService;
import blog.devjay.logistics.web.utils.PaginationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/warehouse")
@RequiredArgsConstructor
@Slf4j
public class WarehouseController {

    private final WarehouseService warehouseService;

    @GetMapping
    public String indexView(Model model, @Validated @ModelAttribute("searchForm") SearchWarehouseDTO dto,
                            BindingResult bindingResult) {
        PaginationUtil paginationUtil = new PaginationUtil(dto, warehouseService.findAllCount(dto));
        model.addAttribute("warehouses", warehouseService.findAll(dto));

        model.addAttribute("rowsPerPage", paginationUtil.rowsPerPage());
        model.addAttribute("totalPages", paginationUtil.getTotalPages());
        model.addAttribute("startPage", paginationUtil.getStartPage());
        model.addAttribute("endPage", paginationUtil.getEndPage());

        return "views/warehouse/index";
    }
}
