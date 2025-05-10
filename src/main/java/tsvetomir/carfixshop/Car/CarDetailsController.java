package tsvetomir.carfixshop.Car;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Add a new carDetail")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "CarDetail successfully added"),
            @ApiResponse(responseCode = "400", description = "Validation failed or bad request")
    })
    @PostMapping("/add")
    public void addNewCarDetail(@RequestBody @Valid CarDetailsDto carDetailsDto)
    {
        CarDetails cardetails = carDetailsMapper.toCarDetailsDto(carDetailsDto);
        carDetailsService.addNewCarDetail(cardetails);
    }

    @Operation(summary = "Add a new carDetails")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "CarDetails successfully added"),
            @ApiResponse(responseCode = "400", description = "Validation failed or bad request")
    })
    @PostMapping("/addAll")
    public void AddAllNewCarsDetails(@RequestBody @Valid List<CarDetailsDto> carDetailsDto)
    {
        List<CarDetails> cardetails = carDetailsMapper.toCarDetailsDtoList(carDetailsDto);
        carDetailsService.AddAllNewCarsDetails(cardetails);
    }
    @Operation(summary = "Get details of all Cars")
    @GetMapping("/getDetail")
    public List<CarDetailsViewDto> getAllCarDetails() {

        return carDetailsService.getAllCarDetails()
                .stream()
                .map(carDetailsMapper::carDetailsViewDto)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Find Car by ID")
    @GetMapping("/{id}")
    public CarDetailsViewDto findCarDetailsById(@PathVariable @Valid Integer id){
        return carDetailsMapper.carDetailsViewDto(carDetailsService.findCarDetailsById(id));
    }
    @Operation(summary = "Delete Car by ID")
    @DeleteMapping("/{id}")
    public void deleteCarDetails(@PathVariable @Valid Integer id){
        carDetailsService.deleteCarDetails(id);
    }

    @Operation(summary = "Find Car by Model")
    @GetMapping("/Model/{Model}")
    public CarDetailsViewDto findByModel(@PathVariable @Valid String Model){
        return carDetailsMapper.carDetailsViewDto(carDetailsService.findByModel(Model));
    }

    @Operation(summary = "Find Car by Year Of Publication")
    @GetMapping("/YearOfPub/{YOP}")
    public CarDetailsViewDto findByYearOfPub(@PathVariable @Valid Integer yop){
        return carDetailsMapper.carDetailsViewDto(carDetailsService.findByYearOfPub(yop));
    }
}
