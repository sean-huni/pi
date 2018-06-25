package io.home.pi.domain;

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
@Table(schema = "rpi", name = "authority")
public class Authority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Enumerated(EnumType.STRING)
    @Column(name = "level")
    private Enum level;

    @Override
    public String toString() {
        return "Authority{" +
                "id=" + id +
                ", level='" + level + '\'' +
                '}';
    }
}
