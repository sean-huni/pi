package io.home.pi.persistence.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

    //    @Transient
    private String token;

    @ManyToOne(targetEntity = Team.class, fetch = FetchType.EAGER)
    private Team team;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", token='" + token + '\'' +
                ", teams=" + team +
                '}';
    }
}
