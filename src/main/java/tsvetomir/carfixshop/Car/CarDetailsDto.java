package tsvetomir.carfixshop.Car;

import tsvetomir.carfixshop.Members.Details;

import java.util.List;

public record CarDetailsDto(
        String Model,
        String Color,
        String regnumber,
        Integer yearofpub,
        Details details_id,
        List<Integer> carproblem_id

) {
}
