package tsvetomir.carfixshop.Problems;


import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

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

    public void addAllNewCarProblems(List<Carproblem> problems){
        carproblemRepository.saveAll(problems);
    }

    public void deleteCarProblem(Integer id) {
        if (!carproblemRepository.existsById(id)) {
            throw new IllegalStateException("Problem with ID " + id + " doesn't exist.");
        }
        carproblemRepository.deleteById(id);
    }

    public List<Carproblem> findByPartsPriceOrderedByPriceAscNative(Integer price) {
        List<Carproblem> result = carproblemRepository.findByPartsPriceOrderByPartsPriceAsc(price);

        if (result.isEmpty()) {
            throw new NoSuchElementException("No car problems found with price greater than:" + price);
        }

        return result;
    }

    public List<Carproblem> findByDescription(String description){
        List<Carproblem> result = carproblemRepository.findByDescription(description);

        if (result.isEmpty()) {
            throw new NoSuchElementException("There is no carproblem with description:" + description);
        }

        return result;
    }


}
