package pl.januszsoft.feature.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.januszsoft.error.ResourceNotFoundException;

import javax.transaction.Transactional;

@Service
@Transactional
public class MatchCRUDService {

    private MatchFinder matchFinder;
    private final MatchRepository matchRepository;

    @Autowired
    public MatchCRUDService(MatchFinder matchFinder, MatchRepository matchRepository) {
        this.matchFinder = matchFinder;
        this.matchRepository = matchRepository;
    }

    public Match getMatchById(long id){
        return matchFinder.find(id).orElseThrow(() -> new ResourceNotFoundException("No match with id: " + id));
    }

    public void removeMatchById(long id) {
        //matchFinder.find(id).orElseThrow(() -> new ResourceNotFoundException("No match with id: " + id)).delete();
        matchRepository.delete(id);
    }
}
