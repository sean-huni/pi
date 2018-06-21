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
@Table(schema = "r_pi", name = "grp")
public class Grp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "grp_name")
    private String grp_name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grp")
    private Set<User> users;

    @OneToOne(cascade = CascadeType.ALL)
    private GrpAuthority grpAuthority;

    @Override
    public String toString() {
        return "Grp{" +
                "id=" + id +
                ", grp_name='" + grp_name + '\'' +
                ", users=" + users +
                ", grpAuthority=" + grpAuthority +
                '}';
    }
}
