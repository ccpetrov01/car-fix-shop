package tsvetomir.carfixshop.Car;

import org.springframework.stereotype.Service;
import tsvetomir.carfixshop.Problems.Carproblem;

import java.util.List;

@Service
public class CarDetailsMapper {
    public CarDetailsViewDto carDetailsViewDto(CarDetails cardetails){
        return new CarDetailsViewDto(
                cardetails.getModel(),
                cardetails.getColor(),
                cardetails.getYearofpub()
                );
    }

    public CarDetails toCarDetailsDto(CarDetailsDto dto){
        var cardetails = new CarDetails();
        cardetails.setModel(dto.Model());
        cardetails.setColor(dto.Color());
        cardetails.setRegnumber(dto.regnumber());
        cardetails.setYearofpub(dto.yearofpub());
        cardetails.setDetails_id(dto.details_id());

        List<Carproblem> carproblemList = dto.carproblem_id()
                .stream()
                .map(id -> {
                    Carproblem carproblem = new Carproblem();
                    carproblem.setId(id);
                    carproblem.setCardetails_id(cardetails);
                    return carproblem;
                })
                .toList();

        cardetails.setCarproblem_id(carproblemList);



        return cardetails;
    }
}
