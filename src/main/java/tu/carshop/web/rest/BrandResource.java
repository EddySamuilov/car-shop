package tu.carshop.web.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tu.carshop.dtos.BrandDTO;
import tu.carshop.dtos.CreateBrandDTO;
import tu.carshop.dtos.CreateOfferDTO;
import tu.carshop.dtos.OfferDTO;
import tu.carshop.services.BrandService;

import java.util.List;

import static tu.carshop.common.GlobalConstants.BINDING_RESULT_PATH;

@Controller
@RequiredArgsConstructor
@RequestMapping("/brands")
public class BrandResource {

    private final BrandService brandService;

    @GetMapping
    public String getAllBrands(Model model) {
        model.addAttribute("brands", brandService.getAll());
        return "brands";
    }

    @GetMapping("/create")
    public String createBrand(Model model) {
        if (!model.containsAttribute("createBrandDTO")) {
            model.addAttribute("createBrandDTO", new CreateBrandDTO());
        }

        return "brand-create";
    }

    @PostMapping("/create")
    public String createBrand(
            @Valid CreateBrandDTO createBrandDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("createBrandDTO", createBrandDTO)
                .addFlashAttribute(BINDING_RESULT_PATH + "createBrandDTO", bindingResult);

            return "redirect:/brands/create";
        }

        brandService.create(createBrandDTO);

        return "redirect:/brands";
    }
}
