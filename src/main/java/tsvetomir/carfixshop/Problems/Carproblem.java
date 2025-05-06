package tsvetomir.carfixshop.Problems;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tsvetomir.carfixshop.Car.CarDetails;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Carproblem {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "carproblem_generator"
    )
    @SequenceGenerator(
            name="carproblem_generator",
            sequenceName = "carproblem_generator",
            allocationSize = 1
    )
    private Integer id;

    @NotBlank(message = "description cannot be blank")
    private String description;

    @NotBlank(message = "solution cannot be blank")
    private String solution;

    @NotBlank(message = "parts_price cannot be blank")
    @NotNull
    @Column(name = "parts_price")
    private Integer partsPrice;

    @NotBlank(message = "handwork_price cannot be blank")
    @NotNull
    @Column(name = "handwork_price")
    private Integer handworkPrice;


    @ManyToOne
    @JoinColumn(
            name = "cardetails_id"
    )
    private CarDetails cardetails_id;
}
