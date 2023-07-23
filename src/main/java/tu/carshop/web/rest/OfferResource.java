package tu.carshop.web.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tu.carshop.dtos.CreateOfferDTO;
import tu.carshop.dtos.OfferDTO;
import tu.carshop.dtos.PostDTO;
import tu.carshop.services.BrandService;
import tu.carshop.services.OfferService;
import tu.carshop.services.PostService;

import java.security.Principal;
import java.util.List;

import static tu.carshop.common.GlobalConstants.BINDING_RESULT_PATH;

@Controller
@RequiredArgsConstructor
@RequestMapping("/offers")
public class OfferResource {

    private final OfferService offerService;
    private final BrandService brandService;
    private final PostService postService;

    @GetMapping
    public List<OfferDTO> getAllOffers(Model model) {
        List<OfferDTO> offers = offerService.getAll();
        model.addAttribute("offers", offers);
        return offers;
    }

    @GetMapping("/{id}/details")
    public String getOfferDetails(
        @PathVariable("id") Long id,
        Model model,
        Principal principal
    ) {
        OfferDTO offer = offerService.findById(id);
        List<PostDTO> posts = postService.getAllPostsForOffer(id);
        PostDTO postDTO = new PostDTO();
        postDTO.setOfferId(id);

        model.addAttribute("offer", offer);
        model.addAttribute("isOwner", principal.getName().equals(offer.getSeller().getUsername()));
        model.addAttribute("posts", posts);
        model.addAttribute("newPost", postDTO);
        return "details";
    }

    @GetMapping("/create")
    public String createOffer(Model model) {
        if (!model.containsAttribute("createOfferDTO")) {
            model.addAttribute("createOfferDTO", new CreateOfferDTO())
                .addAttribute("brands", brandService.getAll());
        }

        return "offer-create";
    }

    @PostMapping("/create")
    public String createOffer(
        @AuthenticationPrincipal User user,
        @Valid CreateOfferDTO createOfferDTO,
        BindingResult bindingResult,
        RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("createOfferDTO", createOfferDTO)
                .addFlashAttribute(BINDING_RESULT_PATH + "createOfferDTO", bindingResult)
                .addFlashAttribute("brands", brandService.getAll());

            return "redirect:/offers/create";
        }

        OfferDTO offer = offerService.create(createOfferDTO, user.getUsername());
        return "redirect:/offers/" + offer.getId() + "/details";
    }

    @GetMapping("/{id}/edit")
    public String editOffer(
        @PathVariable("id") Long id,
        @AuthenticationPrincipal User user,
        Model model
    ) {
        model.addAttribute("offer", offerService.findById(id));
        return "offer-edit";
    }

    @PostMapping("/{id}/edit")
    public String editOffer(
        @PathVariable("id") Long id,
        @Valid OfferDTO offer,
        BindingResult bindingResult,
        RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offer", offer);
            redirectAttributes.addFlashAttribute(BINDING_RESULT_PATH + "offer", bindingResult);

            return "redirect:/offers/" + id + "/edit/errors";
        }

        offerService.update(id, offer);

        return "redirect:/offers/" + id + "/details";
    }

    @DeleteMapping("/{id}")
    public String deleteOffer(@PathVariable("id") Long id) {
        offerService.deleteById(id);

        return "redirect:/offers";
    }
}
