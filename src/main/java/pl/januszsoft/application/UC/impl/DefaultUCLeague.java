package pl.januszsoft.application.UC.impl;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.januszsoft.application.UC.UCLeague;
import pl.januszsoft.entity.LeagueEntity;
import pl.januszsoft.feature.league.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DefaultUCLeague implements UCLeague{

    private final LeagueCreator leagueCreator;
    private final LeagueCRUDService leagueCRUDService;


    @Autowired
    public DefaultUCLeague(LeagueCreator leagueCreator, LeagueCRUDService leagueCRUDService) {
        this.leagueCreator = leagueCreator;
        this.leagueCRUDService = leagueCRUDService;
    }


    @Override
    public long createLeague(String name) {
        League league = leagueCreator.createLeague(name);
        return league.getId();
    }

    @Override
    public void removeLeague(long id) {
        leagueCRUDService.removeById(id);
    }

    @Override
    public LeagueDTO getLeagueDTO(long id) {
        League league = leagueCRUDService.getLeagueById(id);
        return mapToDTO(league.attached());
    }

    @Override
    public List<LeagueDTO> getAllLeagues() {
        return leagueCRUDService.getAllLeagues()
                .stream()
                .map(League::attached)
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private LeagueDTO mapToDTO(@NotNull LeagueEntity league) {
        ModelMapper modelMapper = new ModelMapper();
        PropertyMap<LeagueEntity,LeagueDTO> propertyMap = new PropertyMap<LeagueEntity, LeagueDTO>() {
            @Override
            protected void configure() {
                map().setNumberOfRounds(source.getRoundEntities().size());
                map().setLeagueId(source.getId());
                map().setName(source.getName());
            }
        };

        modelMapper.createTypeMap(league,LeagueDTO.class).addMappings(propertyMap);
        return modelMapper.map(league,LeagueDTO.class);
    }
}
