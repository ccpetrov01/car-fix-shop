package tsvetomir.carfixshop.Car;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardetailsRepository extends JpaRepository<CarDetails,Integer> {

    Optional<CarDetails> findByModel(String Model);
    Optional<CarDetails> findByYearofpub(Integer yearofpub);
}
