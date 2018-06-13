package edu.zucc.paperManageSys.Entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user", schema = "papermanagasystem", catalog = "")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "username", nullable = true, length = 45)
    private String username;

    @Basic
    @Column(name = "name", nullable = true, length = 45)
    private String name;

    @Basic
    @Column(name = "gender", nullable = false)
    private Integer gender;

    @Basic
    @Column(name = "jobtitle", nullable = true, length = 45)
    private String jobtitle;

    @Basic
    @Column(name = "type", nullable = true)
    private Integer type;

    @Basic
    @Column(name = "password", nullable = true, length = 45)
    private String password;

    @Basic
    @Column(name = "email", nullable = true, length = 45)
    private String email;

    @Basic
    @Column(name = "creat_time", nullable = true, length = 45)
    private String creatTime;

    @Basic
    @Column(name = "edit_time", nullable = true, length = 45)
    private String editTime;

    @Basic
    @Column(name = "salt", nullable = true, length = 45)
    private String salt;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name==null?"NULL":name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String name) {
        this.salt = name;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        if(gender==1 || gender==0)
            this.gender = gender;
        else
            this.gender = -1;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(String creatTime) {
        this.creatTime = creatTime;
    }

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
                Objects.equals(username, that.username) &&
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

        return Objects.hash(id, username, name, gender, jobtitle, type, password, email, creatTime, editTime,salt);
    }
}
