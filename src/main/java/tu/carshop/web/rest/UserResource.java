package tu.carshop.web.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tu.carshop.dtos.UserRegisterDTO;
import tu.carshop.services.UserService;

import static tu.carshop.common.GlobalConstants.BINDING_RESULT_PATH;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserResource {

    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login-error")
    public String login(
        @ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY) String username,
        RedirectAttributes redirectAttributes
    ) {
        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
        redirectAttributes.addFlashAttribute("bad_credentials", true);

        return "redirect:/users/login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String register(
        @Valid UserRegisterDTO userRegisterDTO,
        BindingResult bindingResult,
        RedirectAttributes redirectAttributes
    ) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                .addFlashAttribute("userRegisterDTO", userRegisterDTO)
                .addFlashAttribute(BINDING_RESULT_PATH + "userRegisterDTO", bindingResult);

            return "redirect:/users/register";
        }

        userService.register(userRegisterDTO);

        return "redirect:/users/login";
    }

    @ModelAttribute(name = "userRegisterDTO")
    public UserRegisterDTO initUserRegisterDTO() {
        return new UserRegisterDTO();
    }
}
