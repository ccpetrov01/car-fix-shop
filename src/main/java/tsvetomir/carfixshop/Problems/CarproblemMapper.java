package tsvetomir.carfixshop.Problems;

import org.springframework.stereotype.Service;
import tsvetomir.carfixshop.Car.CarDetails;
import tsvetomir.carfixshop.Car.CarDetailsDto;
import tsvetomir.carfixshop.Members.Details;
import tsvetomir.carfixshop.Members.DetailsDto;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarproblemMapper {

    public CarproblemViewDto carproblemViewDto(Carproblem carproblem){
        return new CarproblemViewDto(
                carproblem.getDescription(),
                carproblem.getSolution(),
                carproblem.getPartsPrice(),
                carproblem.getHandworkPrice());
    }

    public Carproblem toCarproblemDto(CarproblemDto dto){
        var carproblem = new Carproblem();
        carproblem.setDescription(dto.description());
        carproblem.setSolution(dto.solution());
        carproblem.setPartsPrice(dto.parts_price());
        carproblem.setHandworkPrice(dto.handwork_price());
        carproblem.setCardetails_id(dto.cardetails_id());

        return carproblem;
    }

    public List<Carproblem> toCarproblemDtoList(List<CarproblemDto> dtoList) {
        return dtoList.stream().map(dto -> {
            Carproblem carproblem = new Carproblem();
            carproblem.setDescription(dto.description());
            carproblem.setSolution(dto.solution());
            carproblem.setPartsPrice(dto.parts_price());
            carproblem.setHandworkPrice(dto.handwork_price());
            carproblem.setCardetails_id(dto.cardetails_id());
            return carproblem;
        }).collect(Collectors.toList());
    }
}
