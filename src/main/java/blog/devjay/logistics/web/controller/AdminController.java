package blog.devjay.logistics.web.controller;

import blog.devjay.logistics.domain.user.Role;
import blog.devjay.logistics.domain.user.UserStatus;
import blog.devjay.logistics.dto.SearchUserDTO;
import blog.devjay.logistics.service.UserService;
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
        int totalPages = (int) Math.ceil((double) totalPageCount / dto.getSize());
        int startPage = ((dto.getPage() - 1) / 5) * 5 + 1; // 현재 페이지를 기준으로 시작 페이지 계산
        int endPage = Math.min(startPage + 4, totalPages);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        model.addAttribute("totalPages", totalPages);

        return "views/admin/users";
    }
}
