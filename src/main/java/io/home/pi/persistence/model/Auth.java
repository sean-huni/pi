package io.home.pi.persistence.model;

import lombok.Data;

import javax.persistence.*;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.domain
 * USER      : sean
 * DATE      : 21-June-2018
 * TIME      : 02:11
 */
@Data
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

    @Override
    public String toString() {
        return "Auth{" +
                "id=" + id +
                ", level='" + level + '\'' +
                '}';
    }
}
