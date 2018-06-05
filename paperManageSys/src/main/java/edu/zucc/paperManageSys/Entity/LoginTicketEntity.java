package edu.zucc.paperManageSys.Entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "login_ticket", schema = "papermanagasystem", catalog = "")
public class LoginTicketEntity {
    private int id;
    private int userId;
    private String ticket;
    private Date expired;
    private Integer status;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id", nullable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "ticket", nullable = false, length = 45)
    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    @Basic
    @Column(name = "expired", nullable = false)
    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

    @Basic
    @Column(name = "status", nullable = true)
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
