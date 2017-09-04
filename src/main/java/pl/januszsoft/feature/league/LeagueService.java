package pl.januszsoft.feature.league;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.januszsoft.entity.LeagueEntity;
import pl.januszsoft.entity.entity.AbstractEntity;
import pl.januszsoft.error.ResourceNotFoundException;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LeagueService {

    private final LeagueRepository leagueRepository;

    @Autowired
    public LeagueService(LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

    public void removeById(long id){
        leagueRepository.findOne(id).ifPresent(AbstractEntity::deactivate);
    }

    public LeagueDTO getLeagueDTOById(long id) {
        final Optional<LeagueEntity> leagueEntity = leagueRepository.findOne(id);
        return leagueEntity.map(this::getLeagueDTO).orElseThrow(()->new ResourceNotFoundException("No League with id: "+id));
    }

    public List<LeagueDTO> getAllLeagues(){
        List<LeagueEntity> all = leagueRepository.findAll();
        return all.stream().map(this::getLeagueDTO).collect(Collectors.toList());
    }

    private LeagueDTO getLeagueDTO(@NotNull LeagueEntity league) {
        List<Long> longs = new ArrayList<>();
        league.getRoundEntities().forEach(e->longs.add(e.getId()));
        return new LeagueDTO(league.getId(),league.getName(), longs);
    }

    public boolean isLeagueExist(long id) {
        return leagueRepository.findOne(id).isPresent();
    }
}
