package edu.zucc.paperManageSys.Entity;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "paper", schema = "papermanagasystem", catalog = "")
public class PaperEntity {
    private int idpaper;
    private String papername;
    private Integer papertype;
    private Integer teacherId;
    private Integer check;
    private Integer adminId;
    private Timestamp creatTime;
    private Timestamp editTime;
    private String paperUrl;

    @Id
    @Column(name = "idpaper", nullable = false)
    public int getIdpaper() {
        return idpaper;
    }

    public void setIdpaper(int idpaper) {
        this.idpaper = idpaper;
    }

    @Basic
    @Column(name = "papername", nullable = true, length = 45)
    public String getPapername() {
        return papername;
    }

    public void setPapername(String papername) {
        this.papername = papername;
    }

    @Basic
    @Column(name = "papertype", nullable = true)
    public Integer getPapertype() {
        return papertype;
    }

    public void setPapertype(Integer papertype) {
        this.papertype = papertype;
    }

    @Basic
    @Column(name = "teacher_id", nullable = true)
    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    @Basic
    @Column(name = "check", nullable = true)
    public Integer getCheck() {
        return check;
    }

    public void setCheck(Integer check) {
        this.check = check;
    }

    @Basic
    @Column(name = "admin_id", nullable = true)
    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    @Basic
    @Column(name = "creat_time", nullable = true)
    public Timestamp getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Timestamp creatTime) {
        this.creatTime = creatTime;
    }

    @Basic
    @Column(name = "edit_time", nullable = true)
    public Timestamp getEditTime() {
        return editTime;
    }

    public void setEditTime(Timestamp editTime) {
        this.editTime = editTime;
    }

    @Basic
    @Column(name = "paper_url", nullable = true, length = 255)
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
