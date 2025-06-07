package tsvetomir.carfixshop.Car;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tsvetomir.carfixshop.Members.Details;
import tsvetomir.carfixshop.Problems.Carproblem;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CarDetails {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cardetails_generator"
    )
    @SequenceGenerator(
            name="cardetails_generator",
            sequenceName = "cardetails_generator",
            allocationSize = 1
    )
    private Integer id;
    @NotBlank(message = "model of car cannot be blank")
    private String model;
    @NotBlank(message = "color of car cannot be blank")
    private String color;
    @NotBlank(message = "registration number of car cannot be blank")
    @NotNull
    private String regnumber;
    @NotBlank(message = "year of publication of car cannot be blank")
    @NotNull
    private Integer yearofpub;


    @ManyToOne
    @JoinColumn(
            name = "details_id"
    )
    private Details details_id;
    @OneToMany(
            mappedBy = "cardetails_id"
    )
    private List<Carproblem> carproblem_id;
}
