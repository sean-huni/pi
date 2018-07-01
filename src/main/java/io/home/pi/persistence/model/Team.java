package io.home.pi.persistence.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

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
@Table(schema = "rpi", name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne(cascade = CascadeType.DETACH)
    private Grp grp;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private GrpAuth grpAuth;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = User.class, fetch = FetchType.LAZY)
    private Set<User> users;

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", grp=" + grp +
                ", grpAuth=" + grpAuth +
                '}';
    }
}
