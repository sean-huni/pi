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
@Table(schema = "rpi", name = "g_auth")
public class GrpAuth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    //    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grp_authority")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Auth.class)
    @JoinColumn(name = "id")
    private Set<Auth> authorities;

    @Override
    public String toString() {
        return "GrpAuth{" +
                "id=" + id +
                ", authorities=" + authorities +
                '}';
    }
}
