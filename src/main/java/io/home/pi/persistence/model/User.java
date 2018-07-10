package io.home.pi.persistence.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;

/**
 * PROJECT   :pi
 * PACKAGE   :io.home.pi.domain
 * USER      :Sean
 * DATE      :2018/06/14
 * TIME      :21:40
 */
@Setter
@Getter
@Entity
@Table(schema = "rpi", name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "enabled")
    private Boolean enabled;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    @JoinColumn(name = "TOKENLOG_ID")
    private TokenLog token;

    @NotNull
    @Column(name = "lastUpdated")
    private Timestamp lastUpdated = new Timestamp(new Date().getTime());


    @ManyToOne(targetEntity = Team.class, fetch = FetchType.EAGER)
    private Team team;

    @Override
    public String toString() {
        final String tokenStr = null != token ? token.toString() : "";
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", token='" + tokenStr + '\'' +
                ", teams=" + team +
                '}';
    }
}
