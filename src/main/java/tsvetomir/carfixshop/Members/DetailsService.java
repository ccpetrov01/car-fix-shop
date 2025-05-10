package tsvetomir.carfixshop.Members;

import org.springframework.stereotype.Service;
import tsvetomir.carfixshop.Problems.Carproblem;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DetailsService {
    private final DetailsRepository detailsRepository;

    public DetailsService(DetailsRepository detailsRepository) {
        this.detailsRepository = detailsRepository;
    }

    public List<Details> getMemberDetails(){

        return detailsRepository.findAll();
    }


    public Details findMemberDetailsbyId(Integer id) {
        return detailsRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Problem with ID " + id + " doesn't exist."));
    }


    public void addNewMemberDetails(Details details) {

        detailsRepository.save(details);
    }

    public void addAllNewMembersDetails(List<Details> details){
        detailsRepository.saveAll(details);
    }

    public void deleteMemberDetails(Integer id) {
        if (!detailsRepository.existsById(id)) {
            throw new IllegalStateException("Problem with ID " + id + " doesn't exist.");
        }
        detailsRepository.deleteById(id);
    }

    public Details findByEmail(String email){
        return detailsRepository.findByEmail(email)
                .orElseThrow(() -> new NoSuchElementException("There is no client with this email: " + email));
    }

    public Details findByFirstname(String firstname){
        return detailsRepository.findByFirstname(firstname)
                .orElseThrow(() -> new NoSuchElementException("There is no client with this firstname: " + firstname));
    }
}
