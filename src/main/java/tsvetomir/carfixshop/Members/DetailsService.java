package tsvetomir.carfixshop.Members;

import org.springframework.stereotype.Service;

import java.util.List;

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

    public void deleteMemberDetails(Integer id) {
        if (!detailsRepository.existsById(id)) {
            throw new IllegalStateException("Problem with ID " + id + " doesn't exist.");
        }
        detailsRepository.deleteById(id);
    }
}
