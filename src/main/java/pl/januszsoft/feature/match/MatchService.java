package pl.januszsoft.feature.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.januszsoft.entity.MatchEntity;
import pl.januszsoft.error.ResourceNotFoundException;
import pl.januszsoft.feature.bet.BetDTO;

import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;

@Service
public class MatchService {

    private final MatchRepository matchRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public MatchDTO getMatchDTOById(long id){
        return matchRepository.findOne(id).map(this::createDTO).orElseThrow(()->new ResourceNotFoundException("No Match with id: "+id));
    }

    public MatchEntity getMatchById(long id){
        return matchRepository.findOne(id).orElseThrow(()->new ResourceNotFoundException("No Match with id: "+id));
    }

    private MatchDTO createDTO(@NotNull MatchEntity entity){
        MatchDTO matchDTO = new MatchDTO();
        matchDTO.setId(entity.getId());
        matchDTO.setGuest(entity.getGuest());
        matchDTO.setHost(entity.getHost());
        return matchDTO;
    }

    public void addBet(BetDTO betDTO) {
        matchRepository.findOne(betDTO.getMatchId()).ifPresent(e->{e.addBet(betDTO);matchRepository.save(e);});
    }

    public void save(MatchEntity match) {
        matchRepository.save(match);
    }
}
