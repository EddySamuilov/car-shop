package tu.carshop.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tu.carshop.dtos.BrandDTO;
import tu.carshop.services.BrandService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/brands")
public class BrandResource {
    private final BrandService brandService;

    @GetMapping
    public String getAllBrands(Model model) {
        List<BrandDTO> brands = brandService.getAll();
        model.addAttribute("brands", brands);

        return "brands";
    }
}
