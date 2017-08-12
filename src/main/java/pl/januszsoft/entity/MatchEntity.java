package pl.januszsoft.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table
public class MatchEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private long id;

    @Column
    private String host;

    @Column
    private String guest;

    @Column
    private MatchResult result;
}
