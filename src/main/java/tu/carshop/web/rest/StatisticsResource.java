package tu.carshop.web.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import tu.carshop.services.StatistiscService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/statistics")
public class StatisticsResource {

  private final StatistiscService statistiscService;

  @GetMapping
  public String getStatistics(Model model) {
    model.addAttribute("statistics", statistiscService.getStatistics());
    return "statistics";
  }
}
