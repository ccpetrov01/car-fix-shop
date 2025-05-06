package tsvetomir.carfixshop.Problems;

import tsvetomir.carfixshop.Car.CarDetails;

public record CarproblemDto(
        String description,
        String solution,
        Integer parts_price,
        Integer handwork_price,
        CarDetails cardetails_id
) {
}
