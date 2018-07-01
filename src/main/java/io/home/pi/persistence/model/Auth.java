package io.home.pi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.domain
 * USER      : sean
 * DATE      : 21-June-2018
 * TIME      : 02:11
 */
@Setter
@Getter
@Entity
@Table(schema = "rpi", name = "auth")
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    //    @Enumerated(EnumType.STRING)
    @Column(name = "level", nullable = false, length = 50)
    private String level;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id", insertable = false, updatable = false)
    private GrpAuth grpAuth;

    @Override
    public String toString() {
        return "Auth{" +
                "id=" + id +
                ", level='" + level + '\'' +
                '}';
    }
}
