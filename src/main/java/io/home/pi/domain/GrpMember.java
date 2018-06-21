package io.home.pi.domain;

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
@Table(schema = "r_pi", name = "group_member")
public class GrpMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "group_id")
    private Integer group_id;

    @Column(name = "username")
    private String username;

    @Override
    public String toString() {
        return "GrpMember{" +
                "id=" + id +
                ", group_id=" + group_id +
                ", username='" + username + '\'' +
                '}';
    }
}
