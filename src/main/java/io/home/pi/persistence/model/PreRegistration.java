package io.home.pi.persistence.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * PROJECT   : pi
 * PACKAGE   : io.home.pi.persistence.model
 * USER      : sean
 * DATE      : 03-July-2018
 * TIME      : 20:12
 * DESCR     : Store secure tokens for the pre-registrations
 */
@Getter
@Setter
@Entity
@Table(schema = "rpi", name = "g_auth")
public class PreRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String name;
    private String username;
    private String password;
    private String token;

    @Override
    public String toString() {
        return "PreRegistration{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
