package com.djj.test.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * Created by djj on 2017/2/4.
 */

@Entity
@Table(name="file")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class File {
    //设置主键
    @Id
    @Column
    private int id;
    @Column
    private int mid;
    @Column
    private String path;
    @Column(columnDefinition = "timestamp")
    private Timestamp updatetime;


    //配置uuid，本来jpa是不支持uuid的，但借用hibernate的方法可以实现。
    //@GeneratedValue(generator = "uuid")
    //@GenericGenerator(name = "uuid", strategy = "uuid")

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getMid() {
        return mid;
    }
    public void setMid(int mid) {
        this.mid = mid;
    }
    public String getPath(){
        return path;
    }
    public void setPath(String path){
        this.path=path;
    }

    public Timestamp getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Timestamp updatetime) {
        this.updatetime = updatetime;
    }

}
