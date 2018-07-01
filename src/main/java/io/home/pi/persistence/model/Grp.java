package io.home.pi.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.domain
 * USER      : sean
 * DATE      : 25-June-2018
 * TIME      : 20:38
 */
@Setter
@Getter
@Entity
@Table(schema = "rpi", name = "grp")
public class Grp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    //    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Override
    public String toString() {
        return "Grp{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
