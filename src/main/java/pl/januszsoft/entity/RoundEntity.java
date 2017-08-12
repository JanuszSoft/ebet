package pl.januszsoft.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table
public class RoundEntity {

    @Id
    @GeneratedValue
    private long id;

    @OneToMany
    private List<MatchEntity> matches;

}
