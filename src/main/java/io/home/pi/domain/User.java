package io.home.pi.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Project: RestfulDemo.
 * Created: s34n.
 * Date: 29-02-2016
 * Time: 16:29
 */
@Setter
@Getter
@Entity
@Table(schema = "r_pi", name = "user")
public class User {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "enabled")
    private Boolean enabled;

    @Transient
    private String token;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", token='" + token + '\'' +
                '}';
    }
}
