package tsvetomir.carfixshop.Members;

import java.util.List;

public record DetailsDto(
        String firstname,
        String lastname,
        String phonenumber,
        String email,
        List<Integer> cardetailsid

) {
}
