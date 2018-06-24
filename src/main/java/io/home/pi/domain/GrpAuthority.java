package io.home.pi.domain;

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
@Table(schema = "rpi", name = "grp_authority")
public class GrpAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne(cascade = CascadeType.ALL)
    private Grp grp;

    //    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grp_authority")
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Authority> authorities;

    @Override
    public String toString() {
        return "GrpAuthority{" +
                "id=" + id +
                ", grp=" + grp +
                ", authorities=" + authorities +
                '}';
    }
}
