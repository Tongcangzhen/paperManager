package edu.zucc.paperManageSys.Entity;

import javax.persistence.*;

@Entity
@Table(name = "type", schema = "papermanagasystem", catalog = "")
public class PaperTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name = "typename", nullable = true, length = 45)
    private String papername;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
