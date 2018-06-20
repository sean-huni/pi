package io.home.pi.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * PROJECT   :RestfulDemo
 * PACKAGE   :com.site.model
 * USER      :s34n
 * DATE      :2016/03/14
 * TIME      :10:40 AM
 */
@Getter
@Setter
@Entity
@Table(name = "authority")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk")
    private Integer pk;
    @Column(name = "username")
    private String username;
    @Column(name = "authority")
    private String authority;

    @Transient
    private User user;

    @Override
    public String toString() {
        return "Authority{" +
                "pk=" + pk +
                ", username='" + username + '\'' +
                ", authority='" + authority + '\'' +
                ", user=" + user +
                '}';
    }
}
