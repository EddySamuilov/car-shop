package tu.carshop.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "brands")
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseEntity {

  @NotBlank
  private String text;

}
