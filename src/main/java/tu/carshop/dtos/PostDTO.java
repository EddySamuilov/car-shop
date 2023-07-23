package tu.carshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tu.carshop.models.User;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {

  private Long offerId;
  private String title;
  private String content;
  private User author;
  private LocalDate created;
  private LocalDate modified;

}
