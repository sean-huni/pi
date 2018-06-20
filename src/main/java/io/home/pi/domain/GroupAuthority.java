package io.home.pi.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * PROJECT   :RestfulDemo
 * PACKAGE   :com.site.model
 * USER      :s34n
 * DATE      :2016/03/14
 * TIME      :10:37 AM
 */
@Setter
@Getter
@Entity
@Table(schema = "r_pi", name = "group_authority")
public class GroupAuthority {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "group_id")
    private Integer groupId;
    @Column(name = "authority")
    private String authority;

    @Override
    public String toString() {
        return "GroupAuthority{" +
                "id=" + id +
                ", groupNum=" + groupId +
                ", authority='" + authority + '\'' +
                ", users=" + users +
                '}';
    }

    //    @ManyToMany(mappedBy="groupAuthorities")
    @Transient
    private Set<User> users = new HashSet<>();

}
