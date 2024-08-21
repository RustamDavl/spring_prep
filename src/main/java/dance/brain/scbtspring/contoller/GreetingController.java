package dance.brain.scbtspring.contoller;

import dance.brain.scbtspring.dto.UserReadDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api/v1")
@SessionAttributes({"user"})
public class GreetingController {


    @GetMapping("/hello")
    public String hello(Model model, HttpServletRequest request,
                        @ModelAttribute("modelAttribute") UserReadDto userReadDto) {
        model.addAttribute("user", new UserReadDto(1L, "Rustam", null, null, null,
                null, null));
        return "greeting/hello";
    }

    @GetMapping("/home")
    public String homePage(Model model) {
        return "user/home";
    }

    @GetMapping("/hello/{id}")
    public ModelAndView hello2(ModelAndView modelAndView,
                               @PathVariable("id") Integer id,
                               HttpServletRequest request,
                               @RequestParam(value = "age", required = false) Integer age,
                               @RequestHeader(value = "accept", required = false) String accept,
                               @CookieValue("JSESSIONID") String jsessionId,
                               HttpServletResponse response) {
        Integer age1 = Integer.valueOf(request.getParameter("age"));
        String header = request.getHeader("accept");
        modelAndView.setViewName("greeting/hello");
        Integer ag = age;
        String cookie = jsessionId;
        response.addCookie(new Cookie("you", "aboba"));
        Integer id1 = id;
        String requestURI = request.getRequestURI();
        return modelAndView;
    }

    @GetMapping("/bye")
    public ModelAndView bye(ModelAndView modelAndView,
                            @CookieValue(value = "you", required = false) String aboba,
                            @SessionAttribute("user") UserReadDto userReadDto,
                            HttpServletRequest request) {
        modelAndView.setViewName("greeting/bye");
        String abobaCookie = aboba;
        Cookie[] cookies = request.getCookies();

        modelAndView.addObject("username", userReadDto.getUsername());

        return modelAndView;
    }
}
