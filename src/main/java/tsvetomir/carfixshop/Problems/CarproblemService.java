package tsvetomir.carfixshop.Problems;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarproblemService {

    private final CarproblemRepository carproblemRepository;

    public CarproblemService(CarproblemRepository carproblemRepository) {
        this.carproblemRepository = carproblemRepository;
    }


    public List<Carproblem> getAllCarsProblems(){

        return carproblemRepository.findAll();
    }


    public Carproblem findCarProblemById(Integer id) {
        return carproblemRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Problem with ID " + id + " doesn't exist."));
    }

    public void addNewCarProblem(Carproblem carproblem) {

        carproblemRepository.save(carproblem);
    }

    public void deleteCarProblem(Integer id) {
        if (!carproblemRepository.existsById(id)) {
            throw new IllegalStateException("Problem with ID " + id + " doesn't exist.");
        }
        carproblemRepository.deleteById(id);
    }

    public List<Carproblem> findByPartsPriceOrderedByPriceAscNative(Integer price){
        return carproblemRepository.findByPartsPriceOrderedByPriceAscNative(price);
    }


}
