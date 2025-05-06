package tsvetomir.carfixshop.Members;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/member-details")
public class DetailsController {
    public DetailsController(DetailsService detailsService, DetailsMapper detailsMapper) {
        this.detailsService = detailsService;
        this.detailsMapper = detailsMapper;
    }

    private final DetailsService detailsService;
    private final DetailsMapper detailsMapper;



    @Operation(summary = "Add a new member")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Member successfully added"),
            @ApiResponse(responseCode = "400", description = "Validation failed or bad request")
    })
    @PostMapping("/add")
    public void addNewMember(@RequestBody @Valid DetailsDto detailsDto) {
        Details details = detailsMapper.toDetailsDto(detailsDto);
        detailsService.addNewMemberDetails(details);
    }
    @Operation(summary = "Get all members")
    @GetMapping("/members")
    public List<DetailsViewDto> getMemberDetails() {

        return detailsService.getMemberDetails()
                .stream()
                .map(detailsMapper::detailsViewDto)
                .collect(Collectors.toList());
    }

    @Operation(summary = "Find member by ID")
    @GetMapping("/{id}")
    public DetailsViewDto findMemberDetailsbyId(@PathVariable Integer id){
        return detailsMapper.detailsViewDto(detailsService.findMemberDetailsbyId(id));
    }
    @Operation(summary = "Delete member by ID")
    @DeleteMapping("/{id}")
    public void deleteMemberDetails(@PathVariable Integer id){
        detailsService.deleteMemberDetails(id);
    }
}
