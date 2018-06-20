package io.home.pi.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PROJECT   :RestfulDemo
 * PACKAGE   :com.site.model
 * USER      :s34n
 * DATE      :2016/03/14
 * TIME      :7:23 PM
 */
@Setter
@Getter
@Entity
@Table(schema = "r_pi", name = "group_member")
public class GroupMember {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "group_num")
    private Integer groupNum;
    @Column(name = "username")
    private String username;

    @Override
    public String toString() {
        return "GroupMember{" +
                "id=" + id +
                ", groupNum=" + groupNum +
                ", username='" + username + '\'' +
                '}';
    }
}
