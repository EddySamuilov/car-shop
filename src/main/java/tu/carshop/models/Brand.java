package tu.carshop.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "brands")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Brand extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String name;
}