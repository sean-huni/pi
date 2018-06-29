package io.home.pi.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * PACKAGE : io.home.pi.domain
 * USER    : Kudzai Sean Huni
 * TIME    : 19:56
 * DATE    : Thursday-28-June-2018
 * E-MAIL  : kudzai@tangentsolutions.co.za
 * CELL    : +27-78-683-1982
 */
@Setter
@Getter
@Entity
@Table(schema = "rpi", name = "token_log")
public class TokenLog {

    public TokenLog() {
        //Default Constructor
    }

    public TokenLog(String username, @NotNull String series, @NotNull String token, @NotNull Timestamp timestamp) {
        this.username = username;
        this.series = series;
        this.token = token;
        this.timestamp = timestamp;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @NotNull
    @Column(name = "series", unique = true)
    private String series;


    @NotNull
    @Column(name = "token", unique = true)
    private String token;

    @NotNull
    @Column(name = "timestamp")
    private Timestamp timestamp;

}
