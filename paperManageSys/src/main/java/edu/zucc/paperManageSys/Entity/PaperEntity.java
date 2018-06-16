package edu.zucc.paperManageSys.Entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "paper", schema = "papermanagasystem", catalog = "")
public class PaperEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "paper_name", nullable = false, length = 45)
    private String paperName;

    @Basic
    @Column(name = "paper_type", nullable = false)
    private Integer paperType;

    @Basic
    @Column(name = "teacher_username", nullable = false, length = 45)
    private String teacherUsername;

    @Basic
    @Column(name = "checked", nullable = false)
    private Integer checked;

    @Basic
    @Column(name = "admin_id", nullable = true)
    private Integer adminId;

    @Basic
    @Column(name = "create_time", nullable = true)
    private Date createTime;

    @Basic
    @Column(name = "paper_url", nullable = true, length = 255)
    private String paperUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaperName() {
        return paperName;
    }

    public void setPaperName(String paperName) {
        this.paperName = paperName;
    }

    public Integer getPaperType() {
        return paperType;
    }

    public void setPaperType(Integer paperType) {
        this.paperType = paperType;
    }

    public String getTeacherUsername() {
        return teacherUsername;
    }

    public void setTeacherUsername(String teacherUsername) {
        this.teacherUsername = teacherUsername;
    }

    public Integer getChecked() {
        return checked;
    }

    public void setChecked(Integer checked) {
        this.checked = checked;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getPaperUrl() {
        return paperUrl;
    }

    public void setPaperUrl(String paperUrl) {
        this.paperUrl = paperUrl;
    }

    @Override
    public String toString() {
        return "PaperEntity{" +
                "id=" + id +
                ", paperName='" + paperName + '\'' +
                ", paperType=" + paperType +
                ", teacherUsername='" + teacherUsername + '\'' +
                ", checked=" + checked +
                ", adminId=" + adminId +
                ", createTime=" + createTime +
                ", paperUrl='" + paperUrl + '\'' +
                '}';
    }
}
