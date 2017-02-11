package com.djj.test.entity;

import javax.persistence.*;

/**
 * Created by djj on 2017/2/10.
 */

@Entity
@Table(name = "tmp")
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BlobFile {
    //设置主键
    //配置uuid，本来jpa是不支持uuid的，但借用hibernate的方法可以实现。
    //@GeneratedValue(generator = "uuid")
    //@GenericGenerator(name = "uuid", strategy = "uuid")

    @Id
    @Column
    private int id;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "photo", columnDefinition = "BLOB", nullable = true)
    private byte[] photo;
    @Column
    private String text;


    /*@Lob注释用作映射这些大数据类型，当属性的类型为byte[],Byte[]或java.io.Serializable时，
    @Lob注释映射为数据库的Blob类型，当属性的类型为char[]，Character[]或java.lang.String时，@Lob注释将映射为数据库的Clob类型。
    对于加了@Lob注释的大数据类型，为了避免每次加载实体时占用大量内存，有必要对该属性进行延时加载，
    这是需要用到@Basic注释。@Basic注释的定义：FetchType属性指定是否延时加载，默认为立即加载，optional属性指定在生成数据库结构时字段能否为null。*/
    @Column
    private String ip;
    @Column(columnDefinition = "timestamp")

    //当表中有Blob,Clob字段时，timestamp字段被当作text
    private String time;
    @Column
    private String type;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
