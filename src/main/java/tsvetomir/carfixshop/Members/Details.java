package tsvetomir.carfixshop.Members;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tsvetomir.carfixshop.Car.CarDetails;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Details {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "details_generator"
    )
    @SequenceGenerator(
            name="details_generator",
            sequenceName = "details_generator",
            allocationSize = 1
    )
    private Integer id;
    @NotBlank(message = "firstname cannot be blank")
    private String firstname;
    @NotBlank(message = "lastname cannot be blank")
    private String lastname;
    @NotBlank(message = "phonenumber cannot be blank")
    @Column(unique = true)
    private String phonenumber;
    @NotBlank(message = "email cannot be blank")
    @Email(message = "email is invalid")
    @Column(unique = true)
    private String email;

    

    @OneToMany(
            mappedBy = "details_id"
    )
    private List<CarDetails> cardetails_id;

}
