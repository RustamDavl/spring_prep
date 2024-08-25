package dance.brain.scbtspring.contoller;

import dance.brain.scbtspring.dto.CreateUpdateUserDto;
import dance.brain.scbtspring.dto.PageResponse;
import dance.brain.scbtspring.dto.UserFilter;
import dance.brain.scbtspring.entity.Role;
import dance.brain.scbtspring.entity.User;
import dance.brain.scbtspring.mapper.CompanyMapper;
import dance.brain.scbtspring.mapper.UserMapper;
import dance.brain.scbtspring.service.CompanyService;
import dance.brain.scbtspring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    private final CompanyMapper companyMapper;
    private final CompanyService companyService;

    @Autowired
    public UserController(UserService userService, UserMapper userMapper, CompanyMapper companyMapper, CompanyService companyService) {
        this.userService = userService;
        this.userMapper = userMapper;
        this.companyMapper = companyMapper;
        this.companyService = companyService;
    }

    @GetMapping("/registration")
    public String registrationPage(Model model, @ModelAttribute("user") CreateUpdateUserDto userDto) {
        model.addAttribute("roles", Role.values());
        model.addAttribute("companies", companyMapper.toDtoList(companyService.getAll()));
        return "user/registration";
    }


    @GetMapping
    public String getAllUsers(Model model, @ModelAttribute("filter") UserFilter userFilter, Pageable pageable) {
        Page<User> page = userService.getAll(userFilter, pageable);
        PageResponse<User> pageResponse = PageResponse.of(page);
        model.addAttribute("pageResponse", pageResponse);
        return "user/users";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userMapper.toDto(userService.getById(id)));
        model.addAttribute("roles", Role.values());
        model.addAttribute("companies", companyMapper.toDtoList(companyService.getAll()));
        return "user/user";
    }

    @PostMapping
    public String createUser(@ModelAttribute @Validated CreateUpdateUserDto dto,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("user", dto);
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/api/v1/users/registration";
        }
        User newUser = userMapper.toEntity(dto);
        User createdUser = userService.create(dto.getCompanyId(), newUser);
        return "redirect:/api/v1/users/" + createdUser.getId();
    }

    //    @PutMapping("/{id}")
    // TODO: 20.08.2024 in rest app post will be replaced by put
    @PostMapping("/{id}/update")
    public String updateUser(@PathVariable("id") Long id,
                             @Validated
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
