package io.home.pi.persistence.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * PACKAGE : io.home.pi.domain
 * USER    : Kudzai Sean Huni
 * TIME    : 19:56
 * DATE    : Thursday-28-June-2018
 * E-MAIL  : kudzai@tangentsolutions.co.za
 * CELL    : +27-78-683-1982
 */
@Data
@Entity
@Table(schema = "rpi", name = "v_token")
public class TokenLog {
    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;

    //    @NotNull
//    @Column(name = "series", unique = true)
    private String series;

    @NotNull
    @Column(name = "token", unique = true)
    private String token;

//    @NotNull
    @Column(name = "lastUpdated")
    @UpdateTimestamp
    private Timestamp lastUpdated;// = new Timestamp(new Date().getTime());

    @CreationTimestamp
//    @NotNull
    private Timestamp created;

    @NotNull
    private Timestamp expiry;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER, mappedBy = "tokenLog")
    private User user;

    public TokenLog() {
        super();
    }

    public TokenLog(User user, String series, String token, Timestamp lastUpdated) {
        super();
        this.user = user;
        this.series = series;
        this.token = token;
        this.lastUpdated = lastUpdated;
    }


    public TokenLog(String series, String token, Timestamp lastUpdated) {
        super();
        this.series = series;
        this.token = token;
        this.lastUpdated = lastUpdated;
    }


    public TokenLog(final String token) {
        super();

        this.token = token;
        this.expiry = calculateExpiryDate(EXPIRATION);
    }

    public TokenLog(final String token, final User user) {
        super();

        this.token = token;
        this.user = user;
        this.expiry = calculateExpiryDate(EXPIRATION);
    }

    private Timestamp calculateExpiryDate(final int expiryTimeInMinutes) {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(new Date().getTime());
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Timestamp(cal.getTime().getTime());
    }

    public void updateToken(final String token) {
        this.token = token;
        this.expiry = calculateExpiryDate(EXPIRATION);
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((expiry == null) ? 0 : expiry.hashCode());
        result = prime * result + ((token == null) ? 0 : token.hashCode());
        result = prime * result + ((user == null) ? 0 : user.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TokenLog other = (TokenLog) obj;
        if (expiry == null) {
            if (other.expiry != null) {
                return false;
            }
        } else if (!expiry.equals(other.expiry)) {
            return false;
        }
        if (token == null) {
            if (other.token != null) {
                return false;
            }
        } else if (!token.equals(other.token)) {
            return false;
        }
        if (user == null) {
            return other.user == null;
        } else return user.equals(other.user);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Token [String=").append(token).append("]").append("[Expires").append(expiry).append("]");
        return builder.toString();
    }

}
