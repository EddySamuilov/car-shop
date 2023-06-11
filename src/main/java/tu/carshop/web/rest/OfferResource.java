package tu.carshop.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tu.carshop.dtos.OfferDTO;
import tu.carshop.services.OfferService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/offers")
public class OfferResource {

    private final OfferService offerService;

    @GetMapping
    public List<OfferDTO> getAllOffers(Model model) {
        List<OfferDTO> offers = offerService.getAll();
        model.addAttribute("offers", offers);
        return offers;
    }

}
