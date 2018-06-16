package edu.zucc.paperManageSys.Entity;

import java.util.Date;

public class ComplexPaper {
    private int id;
    private String paperName;
    private Integer paperType;
    private String typeName;
    private Integer teacherId;
    private String teacherName;
    private String teacherUserName;
    private String checked;
    private String createTime;
    private String paperUrl;

    public ComplexPaper(int id, String paperName, Integer paperType, String typeName, Integer teacherId, String teacherName, String teacherUserName, String checked, String createTime, String paperUrl) {
        this.id = id;
        this.paperName = paperName;
        this.paperType = paperType;
        this.typeName = typeName;
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.teacherUserName = teacherUserName;
        this.checked = checked;
        this.createTime = createTime;
        this.paperUrl = paperUrl;
    }

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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPaperUrl() {
        return paperUrl;
    }

    public void setPaperUrl(String paperUrl) {
        this.paperUrl = paperUrl;
    }

    public String getTeacherUserName() {
        return teacherUserName;
    }

    public void setTeacherUserName(String teacherUserName) {
        this.teacherUserName = teacherUserName;
    }

    @Override
    public String toString() {
        return "ComplexPaper{" +
                "id=" + id +
                ", paperName='" + paperName + '\'' +
                ", paperType=" + paperType +
                ", typeName='" + typeName + '\'' +
                ", teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", teacherUserName='" + teacherUserName + '\'' +
                ", checked='" + checked + '\'' +
                ", createTime='" + createTime + '\'' +
                ", paperUrl='" + paperUrl + '\'' +
                '}';
    }
}

