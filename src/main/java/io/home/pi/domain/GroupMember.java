package io.home.pi.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
@Table(name = "group_member")
public class GroupMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer pk;
    @Column(name = "group_id")
    private Integer groupId;
    @Column(name = "username")
    private String username;

    @Override
    public String toString() {
        return "GroupMember{" +
                "pk=" + pk +
                ", groupId=" + groupId +
                ", username='" + username + '\'' +
                '}';
    }
}
