package dance.brain.scbtspring.contoller;

import dance.brain.scbtspring.dto.UserLoginDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/login")
@SessionAttributes({"loggedInUser"})
public class LoginController {

    @GetMapping
    public String loginPage() {
        return "user/login";
    }

    @PostMapping
    public String login(Model model, UserLoginDto userLoginDto) {
        model.addAttribute("loggedInUser", userLoginDto);
        // TODO: 20.08.2024 save userLoginDto object.
        return "redirect:/api/v1/home";
    }
}
