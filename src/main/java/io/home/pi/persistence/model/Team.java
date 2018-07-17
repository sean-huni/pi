package io.home.pi.persistence.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

/**
 * PROJECT   :pi
 * PACKAGE   :io.home.pi.domain
 * USER      :Sean
 * DATE      :2018/06/14
 * TIME      :21:40
 */
@Data
@Entity
@Table(schema = "rpi", name = "team")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "fk_auth_id")
    private Integer fk_auth_id;

    @Column(name = "fk_grp_id")
    private Integer fk_grp_id;

    //    @OneToMany(cascade = CascadeType.ALL, mappedBy = "grp_authority")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Auth.class)
    @JoinColumn(name = "id", foreignKey = @ForeignKey(name = "fk_auth_id"))
    private Set<Auth> authorities;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Grp.class)
    @JoinColumn(name = "id", foreignKey = @ForeignKey(name = "fk_grp_id"))
    private Set<Grp> grps;

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", fk_auth_id=" + fk_auth_id +
                ", fk_grp_id=" + fk_grp_id +
                '}';
    }
}
