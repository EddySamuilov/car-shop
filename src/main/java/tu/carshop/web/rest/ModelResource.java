package tu.carshop.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import tu.carshop.services.ModelService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/models")
public class ModelResource {
    private final ModelService modelService;

}
