package tsvetomir.carfixshop.Car;


import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CarDetailsController {
    private final CarDetailsMapper carDetailsMapper;
    private final CarDetailsService carDetailsService;

    public CarDetailsController(CarDetailsMapper carDetailsMapper, CarDetailsService carDetailsService) {
        this.carDetailsMapper = carDetailsMapper;
        this.carDetailsService = carDetailsService;
    }

    @PostMapping("/car-details/add")
    public void addNewCarDetail(@RequestBody @Valid CarDetailsDto carDetailsDto)
    {
        CarDetails cardetails = carDetailsMapper.toCarDetailsDto(carDetailsDto);
        carDetailsService.addNewCarDetail(cardetails);
    }


    @GetMapping("/car-details/getDetail")
    public List<CarDetailsViewDto> getAllCarDetails() {

        return carDetailsService.getAllCarDetails()
                .stream()
                .map(carDetailsMapper::carDetailsViewDto)
                .collect(Collectors.toList());
    }


    @GetMapping("/car-details/{id}")
    public CarDetailsViewDto findCarDetailsById(@PathVariable Integer id){
        return carDetailsMapper.carDetailsViewDto(carDetailsService.findCarDetailsById(id));
    }

    @DeleteMapping("/car-details/{id}")
    public void deleteCarDetails(@PathVariable Integer id){
        carDetailsService.deleteCarDetails(id);
    }
}
