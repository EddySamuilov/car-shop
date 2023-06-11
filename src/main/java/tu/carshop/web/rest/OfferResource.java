package tu.carshop.web.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tu.carshop.dtos.CreateOfferDTO;
import tu.carshop.dtos.OfferDTO;
import tu.carshop.services.BrandService;
import tu.carshop.services.OfferService;

import java.security.Principal;
import java.util.List;

import static tu.carshop.common.GlobalConstants.BINDING_RESULT_PATH;

@Controller
@RequiredArgsConstructor
@RequestMapping("/offers")
public class OfferResource {

    private final OfferService offerService;
    private final BrandService brandService;

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
        model.addAttribute("offer", offer);
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
    public String addOffer(
        @AuthenticationPrincipal User user,
        @Valid CreateOfferDTO createOfferDTO,
        BindingResult bindingResult,
        RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("createOfferDTO", createOfferDTO)
                .addFlashAttribute(BINDING_RESULT_PATH + "createOfferDTO", bindingResult)
                .addFlashAttribute("brandsModels", brandService.getAll());

            return "redirect:/offers/create";
        }

        OfferDTO offer = offerService.create(createOfferDTO, user.getUsername());
        return "redirect:/offers/" + offer.getId() + "/details";
    }

}
