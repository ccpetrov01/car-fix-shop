package tsvetomir.carfixshop.Car;


import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/car-details")
public class CarDetailsController {
    private final CarDetailsMapper carDetailsMapper;
    private final CarDetailsService carDetailsService;

    public CarDetailsController(CarDetailsMapper carDetailsMapper, CarDetailsService carDetailsService) {
        this.carDetailsMapper = carDetailsMapper;
        this.carDetailsService = carDetailsService;
    }

    @PostMapping("/add")
    public void addNewCarDetail(@RequestBody @Valid CarDetailsDto carDetailsDto)
    {
        CarDetails cardetails = carDetailsMapper.toCarDetailsDto(carDetailsDto);
        carDetailsService.addNewCarDetail(cardetails);
    }


    @GetMapping("/getDetail")
    public List<CarDetailsViewDto> getAllCarDetails() {

        return carDetailsService.getAllCarDetails()
                .stream()
                .map(carDetailsMapper::carDetailsViewDto)
                .collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public CarDetailsViewDto findCarDetailsById(@PathVariable @Valid Integer id){
        return carDetailsMapper.carDetailsViewDto(carDetailsService.findCarDetailsById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteCarDetails(@PathVariable @Valid Integer id){
        carDetailsService.deleteCarDetails(id);
    }
}
