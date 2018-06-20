package io.home.pi.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
    //    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private Integer id;
    @Column(name="group_name")
    private Integer groupName;

    @Override
    public String toString() {
        return "Group{" +
//                "id=" + id +
                ", groupName=" + groupName +
                '}';
    }
}
