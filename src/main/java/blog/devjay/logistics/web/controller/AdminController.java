package blog.devjay.logistics.web.controller;

import blog.devjay.logistics.domain.log.Log;
import blog.devjay.logistics.domain.log.ResponseStatus;
import blog.devjay.logistics.domain.user.Role;
import blog.devjay.logistics.domain.user.UserStatus;
import blog.devjay.logistics.dto.log.SearchLogDTO;
import blog.devjay.logistics.dto.user.SearchUserDTO;
import blog.devjay.logistics.dto.user.UpdateUserDTO;
import blog.devjay.logistics.service.LogService;
import blog.devjay.logistics.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {
    private final UserService userService;
    private final LogService logService;

    @GetMapping
    public String usersView(Model model,
                            @ModelAttribute("searchUserForm") SearchUserDTO dto) {
        model.addAttribute("userStatus", UserStatus.values());
        model.addAttribute("userRole", Role.values());
        dto.setPagination(model, userService.findAllCount(dto));

        model.addAttribute("users", userService.findAll(dto));

        return "views/admin/users";
    }

    @PutMapping("/users/{userId}")
    @ResponseBody
    public void updateUser(@PathVariable("userId") Long userId, @RequestBody UpdateUserDTO dto) {
        userService.update(userId, dto);
    }

    @GetMapping("/logs")
    public String logView(Model model, @ModelAttribute SearchLogDTO searchLogDTO) {
        model.addAttribute("responseStatus", ResponseStatus.values());
        searchLogDTO.setPagination(model, logService.findAllCount(searchLogDTO));
        List<Log> logs = logService.findAll(searchLogDTO);
        model.addAttribute("logs", logs);
        return "views/admin/logs";
    }
}
