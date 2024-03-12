package organization.structural.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Setter
@Getter
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table (name = "person", schema = "public")
public class Person {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "nama", nullable = false)
    private String nama;

    @Column(name = "atasan", nullable = true)
    private UUID atasan;
}
