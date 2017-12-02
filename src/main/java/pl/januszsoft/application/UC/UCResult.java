package pl.januszsoft.application.UC;

import pl.januszsoft.feature.leagueUsersResults.ResultDTO;

import java.util.List;

public interface UCResult {

    ResultDTO getResultById(long id);

    List<ResultDTO> getAllResultsByUsername(String username);

}
