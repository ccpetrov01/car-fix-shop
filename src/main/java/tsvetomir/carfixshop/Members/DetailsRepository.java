package tsvetomir.carfixshop.Members;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DetailsRepository extends JpaRepository<Details,Integer> {

    Optional<Details> findByEmail(String email);
    Optional<Details> findByFirstname(String firstname);
}
