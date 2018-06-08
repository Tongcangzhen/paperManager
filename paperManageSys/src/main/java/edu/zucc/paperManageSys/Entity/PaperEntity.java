package edu.zucc.paperManageSys.Entity;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "paper", schema = "papermanagasystem", catalog = "")
public class PaperEntity {
    @Id
    @Column(name = "idpaper", nullable = false)
    private int idpaper;

    @Basic
    @Column(name = "papername", nullable = true, length = 45)
    private String papername;

    @Basic
    @Column(name = "papertype", nullable = true)
    private Integer papertype;

    @Basic
    @Column(name = "teacher_id", nullable = true)
    private Integer teacherId;

    @Basic
    @Column(name = "check", nullable = true)
    private Integer check;

    @Basic
    @Column(name = "admin_id", nullable = true)
    private Integer adminId;

    @Basic
    @Column(name = "creat_time", nullable = true)
    private Timestamp creatTime;

    @Basic
    @Column(name = "edit_time", nullable = true)
    private Timestamp editTime;

    @Basic
    @Column(name = "paper_url", nullable = true, length = 255)
    private String paperUrl;

    public int getIdpaper() {
        return idpaper;
    }

    public void setIdpaper(int idpaper) {
        this.idpaper = idpaper;
    }

    public String getPapername() {
        return papername;
    }

    public void setPapername(String papername) {
        this.papername = papername;
    }

    public Integer getPapertype() {
        return papertype;
    }

    public void setPapertype(Integer papertype) {
        this.papertype = papertype;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getCheck() {
        return check;
    }

    public void setCheck(Integer check) {
        this.check = check;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Timestamp getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Timestamp creatTime) {
        this.creatTime = creatTime;
    }

    public Timestamp getEditTime() {
        return editTime;
    }

    public void setEditTime(Timestamp editTime) {
        this.editTime = editTime;
    }

    public String getPaperUrl() {
        return paperUrl;
    }

    public void setPaperUrl(String paperUrl) {
        this.paperUrl = paperUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaperEntity that = (PaperEntity) o;
        return idpaper == that.idpaper &&
                Objects.equals(papername, that.papername) &&
                Objects.equals(papertype, that.papertype) &&
                Objects.equals(teacherId, that.teacherId) &&
                Objects.equals(check, that.check) &&
                Objects.equals(adminId, that.adminId) &&
                Objects.equals(creatTime, that.creatTime) &&
                Objects.equals(editTime, that.editTime) &&
                Objects.equals(paperUrl, that.paperUrl);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idpaper, papername, papertype, teacherId, check, adminId, creatTime, editTime, paperUrl);
    }
}
