package edu.zucc.paperManageSys.Entity;

import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

@Entity
@Table(name = "type", schema = "papermanagasystem", catalog = "")
@SQLDelete(sql = "DELETE FROM type WHERE id IN ( ? )")
public class PaperTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "type_name", nullable = true, length = 45)
    private String typeName;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
