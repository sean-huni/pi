package io.home.pi.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * PROJECT   :RestfulDemo
 * PACKAGE   :com.site.model
 * USER      :s34n
 * DATE      :2016/03/14
 * TIME      :10:39 AM
 */

@Setter
@Getter
@Entity
@Table(name= "group")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer pk;
    @Column(name="group_name")
    private Integer groupName;

    @Override
    public String toString() {
        return "Group{" +
                "pk=" + pk +
                ", groupName=" + groupName +
                '}';
    }
}
