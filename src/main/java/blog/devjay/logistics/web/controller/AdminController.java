package blog.devjay.logistics.web.controller;

import blog.devjay.logistics.domain.user.Role;
import blog.devjay.logistics.domain.user.UserStatus;
import blog.devjay.logistics.dto.SearchUserDTO;
import blog.devjay.logistics.service.UserService;
import blog.devjay.logistics.web.utils.PaginationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {
    private final UserService userService;

    @GetMapping("/users")
    public String usersView(Model model,
                            @ModelAttribute("searchUserForm") SearchUserDTO dto) {
        model.addAttribute("userStatus", UserStatus.values());
        model.addAttribute("userRole", Role.values());
        model.addAttribute("rowsPerPage", new int[]{10, 20, 40});
        model.addAttribute("users", userService.findAll(dto));

        int totalPageCount = userService.findAllCount(dto);
        int totalPages = PaginationUtil.getTotalPages(totalPageCount, dto.getSize());
        int startPage = PaginationUtil.getStartPage(dto.getPage());
        int endPage = PaginationUtil.getEndPage(startPage, totalPages);

        model.addAttribute("totalPages", totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "views/admin/users";
    }
}
