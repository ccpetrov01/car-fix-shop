package tsvetomir.carfixshop.Car;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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

    public void AddAllNewCarsDetails(List<CarDetails> carDetailsList){
        cardetailsRepository.saveAll(carDetailsList);
    }

    public void deleteCarDetails(Integer id) {
        if (!cardetailsRepository.existsById(id)) {
            throw new IllegalStateException("Problem with ID " + id + " doesn't exist.");
        }
        cardetailsRepository.deleteById(id);
    }

    public CarDetails findByModel(String Model){
        return cardetailsRepository.findByModel(Model)
                .orElseThrow(() -> new NoSuchElementException("There is no car with this Model: " + Model));
    }

    public CarDetails findByYearOfPub(Integer yop){
        return cardetailsRepository.findByYearofpub(yop)
                .orElseThrow(() -> new NoSuchElementException("There is no car with this year of publication: " + yop));
    }
}
