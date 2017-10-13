package pl.januszsoft.feature.match;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.januszsoft.error.ResourceNotFoundException;

import javax.transaction.Transactional;

@Service
@Transactional
public class MatchCRUDService {

    private MatchFinder matchFinder;

    @Autowired
    public MatchCRUDService(MatchFinder matchFinder) {
        this.matchFinder = matchFinder;
    }

    public Match getMatchById(long id){
        return matchFinder.find(id).orElseThrow(() -> new ResourceNotFoundException("No match with id: " + id));
    }

    public void removeMatchById(long id) {
        matchFinder.find(id).orElseThrow(() -> new ResourceNotFoundException("No match with id: " + id)).delete();
    }
}
