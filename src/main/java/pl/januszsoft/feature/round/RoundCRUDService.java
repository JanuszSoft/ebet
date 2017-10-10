package pl.januszsoft.feature.round;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.januszsoft.error.ResourceNotFoundException;

import javax.transaction.Transactional;

@Service
@Transactional
public class RoundCRUDService {

    private final RoundFinder roundFinder;
    private final RoundRepository roundRepository;

    @Autowired
    public RoundCRUDService(RoundFinder roundFinder, RoundRepository roundRepository) {
        this.roundFinder = roundFinder;
        this.roundRepository = roundRepository;
    }

    public Round getRoundById(long id){
        return roundFinder.find(id).orElseThrow(()->new ResourceNotFoundException("No round with id: " + id));
    }

    public void removeById(long id) {
        //roundFinder.find(id).orElseThrow(()->new ResourceNotFoundException("No round with id: " + id)).delete();
        if(roundRepository.exists(id)){
            roundRepository.delete(id);
        }else{
            throw new ResourceNotFoundException("No round with  id: "+id);
        }
    }
}
