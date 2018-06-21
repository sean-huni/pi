package io.home.pi.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
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
@Table(schema = "r_pi", name = "group_authority")
public class GrpAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "group_id")
    private Integer groupId;
    @Column(name = "authority")
    private String authority;

    @Override
    public String toString() {
        return "GrpAuthority{" +
                "id=" + id +
                ", group_id=" + groupId +
                ", authority='" + authority + '\'' +
                ", users=" + users +
                '}';
    }

    //    @ManyToMany(mappedBy="groupAuthorities")
    @Transient
    private Set<User> users = new HashSet<>();

}
