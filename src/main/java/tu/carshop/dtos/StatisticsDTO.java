package tu.carshop.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticsDTO {
  private int usersCount;
  private int offersCount;
  private int brandsCount;
}
