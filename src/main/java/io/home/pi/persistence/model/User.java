package io.home.pi.persistence.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * PROJECT   :pi
 * PACKAGE   :io.home.pi.domain
 * USER      :Sean
 * DATE      :2018/06/14
 * TIME      :21:40
 */
@Data
@Entity
@Table(schema = "rpi", name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "enabled")
    private Boolean enabled;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "TOKEN_ID")
    private TokenLog tokenLog;


    @NotNull
    @UpdateTimestamp
    @Column(name = "lastUpdated")
    private Timestamp lastUpdated;

//    @NotNull
    @CreationTimestamp
    @Column(name = "created", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdDate;


    @ManyToOne(targetEntity = Team.class, fetch = FetchType.EAGER)
    private Team team;

    @Override
    public String toString() {
        final String tokenStr = null != tokenLog ? tokenLog.toString() : "";
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", tokenLog='" + tokenStr + '\'' +
                ", teams=" + team +
                '}';
    }
}
