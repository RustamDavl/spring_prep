package dance.brain.scbtspring.contoller;

import dance.brain.scbtspring.dto.CreateUpdateUserDto;
import dance.brain.scbtspring.entity.User;
import dance.brain.scbtspring.mapper.CompanyMapper;
import dance.brain.scbtspring.mapper.UserMapper;
import dance.brain.scbtspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    private final CompanyMapper companyMapper;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper, CompanyMapper companyMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.companyMapper = companyMapper;
    }

    @GetMapping
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAll());
        return "user/users";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "user/user";
    }

    @PostMapping
    public String createUser(@ModelAttribute CreateUpdateUserDto dto) {
        User newUser = userMapper.toEntity(dto);
        User createdUser = userService.create(dto.getCompanyId(), newUser);
        return "redirect:/api/v1/users/" + createdUser.getId();
    }

    //    @PutMapping("/{id}")
    // TODO: 20.08.2024 in rest app post will be replaced by put
    @PostMapping("/{id}/update")
    public String updateUser(@PathVariable("id") Long id,
                             @ModelAttribute CreateUpdateUserDto dto) {
        User newUser = userMapper.toEntity(dto);
        User updatedUser = userService.update(dto.getCompanyId(), id, newUser);
        return "redirect:/api/v1/users/" + updatedUser.getId();
    }

    //    @DeleteMapping("/{id}")
    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/api/v1/users";
    }
}
