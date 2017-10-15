package pl.januszsoft.feature.bet;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.januszsoft.entity.BetEntity;
import pl.januszsoft.error.ResourceNotFoundException;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class BetCRUDService {

    private final BetRepository betRepository;
    private final BetFinder betFinder;

    @Autowired
    public BetCRUDService(BetRepository betRepository, BetFinder betFinder) {
        this.betRepository = betRepository;
        this.betFinder = betFinder;
    }

    public void deleteBetById(long id) {
        betFinder.find(id).orElseThrow(() -> new ResourceNotFoundException("No Bet with id: " + id)).delete();
    }

    public BetDTO getBetById(long id) {
        final Optional<BetEntity> entity = betRepository.findOne(id);
        return entity.map(this::createBetDTO).orElseThrow(() -> new ResourceNotFoundException("No Bet with id: " + id));
    }

    private BetDTO createBetDTO(@NotNull BetEntity entity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, BetDTO.class);
    }

    public List<BetDTO> getAllBetsByUsername(String username) {
        List<BetEntity> allByUsername = betRepository.getAllByUsernameAndActiveTrue(username);
        return allByUsername.stream().map(this::createBetDTO).collect(Collectors.toList());
    }
}