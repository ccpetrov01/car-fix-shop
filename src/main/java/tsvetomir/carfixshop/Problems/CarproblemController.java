package tsvetomir.carfixshop.Problems;


import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/car-problem")
public class CarproblemController {


    private final CarproblemService carproblemService;
    private final CarproblemMapper carproblemMapper;

    public CarproblemController(CarproblemService carproblemService, CarproblemMapper carproblemMapper) {
        this.carproblemService = carproblemService;
        this.carproblemMapper = carproblemMapper;
    }


    @PostMapping("/add")
    public void addNewCarProblem(@RequestBody @Valid CarproblemDto carproblemDto)
    {
        Carproblem carproblem = carproblemMapper.toCarproblemDto(carproblemDto);
        carproblemService.addNewCarProblem(carproblem);
    }

    @PostMapping("/addAll")
    public void addAllNewCarProblems(@RequestBody @Valid List<CarproblemDto> carproblemDto){
        List<Carproblem> carproblem = carproblemMapper.toCarproblemDtoList(carproblemDto);
        carproblemService.addAllNewCarProblems(carproblem);
    }


    @GetMapping("/problems")
    public List<CarproblemViewDto> getAllCarsProblems() {

        return carproblemService.getAllCarsProblems()
                .stream()
                .map(carproblemMapper::carproblemViewDto)
                .collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public CarproblemViewDto findCarProblemById(@PathVariable @Valid Integer id){
        return carproblemMapper.carproblemViewDto(carproblemService.findCarProblemById(id));
    }

    @DeleteMapping("/{id}")
    public void deleteCarProblem(@PathVariable @Valid Integer id){
         carproblemService.deleteCarProblem(id);
    }

    @GetMapping("/price/{price}")
    public List<CarproblemViewDto> findByPartsPriceOrderedByPriceAscNative(@PathVariable @Valid Integer price){
        return carproblemService.findByPartsPriceOrderedByPriceAscNative(price)
                .stream()
                .map(carproblemMapper::carproblemViewDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/description/{description}")
    public List<CarproblemViewDto> findByDescription(@PathVariable @Valid String description){
        return carproblemService.findByDescription(description)
                .stream()
                .map(carproblemMapper::carproblemViewDto)
                .collect(Collectors.toList());
    }
}
