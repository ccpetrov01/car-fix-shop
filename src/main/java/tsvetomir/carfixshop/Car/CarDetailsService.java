package tsvetomir.carfixshop.Car;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarDetailsService {
    private final CardetailsRepository cardetailsRepository;

    public CarDetailsService(CardetailsRepository cardetailsRepository) {
        this.cardetailsRepository = cardetailsRepository;
    }

    public List<CarDetails> getAllCarDetails(){

        return cardetailsRepository.findAll();
    }


    public CarDetails findCarDetailsById(Integer id) {
        return cardetailsRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Problem with ID " + id + " doesn't exist."));
    }


    public void addNewCarDetail(CarDetails cardetails) {

        cardetailsRepository.save(cardetails);
    }

    public void deleteCarDetails(Integer id) {
        if (!cardetailsRepository.existsById(id)) {
            throw new IllegalStateException("Problem with ID " + id + " doesn't exist.");
        }
        cardetailsRepository.deleteById(id);
    }
}
