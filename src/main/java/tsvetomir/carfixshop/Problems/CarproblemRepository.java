package tsvetomir.carfixshop.Problems;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarproblemRepository extends JpaRepository<Carproblem,Integer> {

    List<Carproblem> findByPartsPriceOrderByPartsPriceAsc(Integer price);
    List<Carproblem> findByDescription(String description);
}
