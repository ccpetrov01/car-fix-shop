package tsvetomir.carfixshop.Problems;


import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class CarproblemController {


    private final CarproblemService carproblemService;
    private final CarproblemMapper carproblemMapper;

    public CarproblemController(CarproblemService carproblemService, CarproblemMapper carproblemMapper) {
        this.carproblemService = carproblemService;
        this.carproblemMapper = carproblemMapper;
    }


    @PostMapping("/car-problem/add")
    public void addNewCarProblem(@RequestBody @Valid CarproblemDto carproblemDto)
    {
        Carproblem carproblem = carproblemMapper.toCarproblemDto(carproblemDto);
        carproblemService.addNewCarProblem(carproblem);
    }


    @GetMapping("/car-problem/problems")
    public List<CarproblemViewDto> getAllCarsProblems() {

        return carproblemService.getAllCarsProblems()
                .stream()
                .map(carproblemMapper::carproblemViewDto)
                .collect(Collectors.toList());
    }


    @GetMapping("/car-problem/{id}")
    public CarproblemViewDto findCarProblemById(@PathVariable Integer id){
        return carproblemMapper.carproblemViewDto(carproblemService.findCarProblemById(id));
    }

    @DeleteMapping("/car-problem/{id}")
    public void deleteCarProblem(@PathVariable Integer id){
         carproblemService.deleteCarProblem(id);
    }
}
