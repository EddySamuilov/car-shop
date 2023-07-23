package tu.carshop.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Data
@Entity
@Table(name = "posts")
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseEntity {

  private String title;

  private String content;

  @ManyToOne
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private User author;

  @ManyToOne
  @JoinColumn(name = "offer_id", referencedColumnName = "id")
  private Offer offer;

}
