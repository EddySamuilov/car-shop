package tu.carshop.web.rest;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tu.carshop.dtos.PostDTO;
import tu.carshop.services.PostService;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostResource {

  private final PostService postService;

  @PostMapping("/add")
  public String addPost(
      @ModelAttribute("post") PostDTO postDto,
      Principal principal
  ) {
    postService.addPost(postDto, principal.getName());

    return "redirect:/offers/" + postDto.getOfferId() + "/details";
  }
}
