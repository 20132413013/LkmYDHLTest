package com.lkm.lkmydhltest.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Note {
    @Id(autoincrement = true)
    private Long id;
    private String text;
    private String des;
    @Generated(hash = 758786428)
    public Note(Long id, String text, String des) {
        this.id = id;
        this.text = text;
        this.des = des;
    }
    @Generated(hash = 1272611929)
    public Note() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getText() {
        return this.text;
    }
    public void setText(String text) {
        this.text = text;
    }
    public String getDes() {
        return this.des;
    }
    public void setDes(String des) {
        this.des = des;
    }

    @Override
    public String toString() {
        return "Note{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", des='" + des + '\'' +
                '}';
    }
}
