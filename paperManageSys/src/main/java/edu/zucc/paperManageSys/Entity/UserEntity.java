package edu.zucc.paperManageSys.Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "papermanagasystem", catalog = "")
public class UserEntity {
    private int id;
    private String name;
    private Boolean gender;
    private String jobtitle;
    private Integer type;
    private String password;
    private String email;
    private String creatTime;
    private String editTime;
    private String salt;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 45)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "salt", nullable = true, length = 45)
    public String getSalt() {
        return salt;
    }

    public void setSalt(String name) {
        this.salt = name;
    }

    @Basic
    @Column(name = "gender", nullable = true)
    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "jobtitle", nullable = true, length = 45)
    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    @Basic
    @Column(name = "type", nullable = true)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 45)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 45)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "creat_time", nullable = true, length = 45)
    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

    @Basic
    @Column(name = "edit_time", nullable = true, length = 45)
    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(gender, that.gender) &&
                Objects.equals(jobtitle, that.jobtitle) &&
                Objects.equals(type, that.type) &&
                Objects.equals(password, that.password) &&
                Objects.equals(email, that.email) &&
                Objects.equals(creatTime, that.creatTime) &&
                Objects.equals(editTime, that.editTime) &&
                Objects.equals(salt, that.salt);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, gender, jobtitle, type, password, email, creatTime, editTime,salt);
    }
}
