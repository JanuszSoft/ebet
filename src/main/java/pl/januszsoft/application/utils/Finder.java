package pl.januszsoft.application.utils;


import javax.transaction.Transactional;
import java.util.Optional;

public interface Finder<T,K> {

    @Transactional
    Optional<T> find(K id);

    @Transactional
    boolean exist(K id);

}
