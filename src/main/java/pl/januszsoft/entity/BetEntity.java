package pl.januszsoft.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class BetEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @ManyToOne
    private MatchEntity match;

    @Column
    private MatchResult result;

    @Column
    private String username;

}
