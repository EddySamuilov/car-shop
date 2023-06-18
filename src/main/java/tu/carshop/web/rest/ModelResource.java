package tu.carshop.web.rest;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import tu.carshop.dtos.CreateBrandDTO;
import tu.carshop.dtos.CreateModelDTO;
import tu.carshop.services.ModelService;

import static tu.carshop.common.GlobalConstants.BINDING_RESULT_PATH;

@Controller
@RequiredArgsConstructor
@RequestMapping("/models")
public class ModelResource {
    private final ModelService modelService;

    @GetMapping("/create")
    public String createBrand(Model model) {
        if (!model.containsAttribute("createModelDTO")) {
            model.addAttribute("createModelDTO", new CreateModelDTO());
        }

        return "model-create";
    }

    @PostMapping("/create")
    public String createModel(
            @Valid CreateModelDTO createModelDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("createModelDTO", createModelDTO)
                    .addFlashAttribute(BINDING_RESULT_PATH + "createModelDTO", bindingResult);

            return "redirect:/models/create";
        }

        modelService.create(createModelDTO);

        return "redirect:/brands";
    }
}
