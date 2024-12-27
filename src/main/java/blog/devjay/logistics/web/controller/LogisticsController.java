package blog.devjay.logistics.web.controller;

import blog.devjay.logistics.domain.item.Item;
import blog.devjay.logistics.dto.item.CreateItemDTO;
import blog.devjay.logistics.dto.item.SearchItemDTO;
import blog.devjay.logistics.service.ItemService;
import blog.devjay.logistics.web.utils.PaginationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/logistics")
@RequiredArgsConstructor
public class LogisticsController {

    private final ItemService itemService;

    @GetMapping
    public String index(Model model, @ModelAttribute("search") SearchItemDTO dto) {
        PaginationUtil paginationUtil = new PaginationUtil(dto, itemService.findAllCount(dto));
        model.addAttribute("items", itemService.findAll(dto));

        model.addAttribute("rowsPerPage", paginationUtil.rowsPerPage());
        model.addAttribute("totalPages", paginationUtil.getTotalPages());
        model.addAttribute("startPage", paginationUtil.getStartPage());
        model.addAttribute("endPage", paginationUtil.getEndPage());

        return "views/logistics/index";
    }

    @GetMapping("/create")
    public String createView(@ModelAttribute("createItemDTO") CreateItemDTO dto) {
        return "views/logistics/create";
    }

    @PostMapping("/create")
    public String create(@Validated @ModelAttribute("createItemDTO") CreateItemDTO dto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("error = {}", bindingResult.getAllErrors());
            return "views/logistics/create";
        }

        Item item = new Item(dto.getName(), dto.getPrice(), dto.getQuantity());

        try {
            itemService.create(item);
        } catch (DuplicateKeyException e) {
            log.error("e", e);
            bindingResult.rejectValue("name", "item.name.exist");
            return "views/logistics/create";
        }

        return "redirect:/logistics";
    }
}
