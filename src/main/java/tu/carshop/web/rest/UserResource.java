package tu.carshop.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserResource {

    @GetMapping("/login")
    public String login() {
        return "auth-login";
    }

    @GetMapping("/register")
    public String register() {
        return "auth-register";
    }
}
