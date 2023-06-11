package tu.carshop.models;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
@NoArgsConstructor
public abstract class BaseEntity {

    @Id
    @Column(unique = true, nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime created;

    private LocalDateTime modified;
}