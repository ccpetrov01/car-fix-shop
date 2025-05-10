package tsvetomir.carfixshop.Members;


import org.springframework.stereotype.Service;
import tsvetomir.carfixshop.Car.CarDetails;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetailsMapper {
    public DetailsViewDto detailsViewDto(Details details){

        return new DetailsViewDto(
                details.getFirstname(),
                details.getLastname()
        );
    }

    public Details toDetailsDto(DetailsDto dto){
        var details = new Details();
        details.setFirstname(dto.firstname());
        details.setLastname(dto.lastname());
        details.setPhonenumber(dto.phonenumber());
        details.setEmail(dto.email());

        List<CarDetails> carDetailsList = dto.cardetailsid()
                .stream()
                .map(id -> {
            CarDetails carDetails = new CarDetails();
            carDetails.setId(id);
            carDetails.setDetails_id(details);
            return carDetails;
        })
                .toList();

        details.setCardetails_id(carDetailsList);



        return details;
    }

    public List<Details> toDetailsDtoList(List<DetailsDto> dtoList){
        return dtoList.stream().map(dto -> {
            var details = new Details();
            details.setFirstname(dto.firstname());
            details.setLastname(dto.lastname());
            details.setPhonenumber(dto.phonenumber());
            details.setEmail(dto.email());

            List<CarDetails> carDetailsList = dto.cardetailsid()
                    .stream()
                    .map(id -> {
                        CarDetails carDetails = new CarDetails();
                        carDetails.setId(id);
                        carDetails.setDetails_id(details);
                        return carDetails;
                    })
                    .toList();

            details.setCardetails_id(carDetailsList);
            return details;
        }).collect(Collectors.toList());

        }
    }

