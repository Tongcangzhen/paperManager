package edu.zucc.paperManageSys.Entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "login_ticket", schema = "papermanagasystem", catalog = "")
public class LoginTicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "user_id", nullable = false)
    private int userId;

    @Basic
    @Column(name = "ticket", nullable = false, length = 45)
    private String ticket;

    @Basic
    @Column(name = "expired", nullable = false)
    private Date expired;

    @Basic
    @Column(name = "status", nullable = true)
    private Integer status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginTicketEntity that = (LoginTicketEntity) o;
        return id == that.id &&
                userId == that.userId &&
                Objects.equals(ticket, that.ticket) &&
                Objects.equals(expired, that.expired) &&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, ticket, expired, status);
    }
}
