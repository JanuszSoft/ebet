package pl.januszsoft.feature.round;

import lombok.Data;
import pl.januszsoft.feature.match.MatchDTO;

import java.util.List;

@Data
public class RoundDTO {
    private long id;
    private List<MatchDTO> matchDTOList;

    public RoundDTO(){

    }

    public RoundDTO(List<MatchDTO> matchDTOList) {
        this.matchDTOList = matchDTOList;
    }

    public RoundDTO(int number, List<MatchDTO> matchDTOList) {
      //  this.number = number;
        this.matchDTOList = matchDTOList;
    }

    public RoundDTO(long id, int number, List<MatchDTO> matchDTOList) {
        this.id = id;
        //this.number = number;
        this.matchDTOList = matchDTOList;
    }
}
