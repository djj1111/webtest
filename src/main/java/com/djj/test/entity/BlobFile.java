package com.djj.test.entity;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.bytecode.internal.javassist.FieldHandled;
import org.hibernate.bytecode.internal.javassist.FieldHandler;

import javax.persistence.*;

/**
 * Created by djj on 2017/2/10.
 */

@Entity
@Table(name = "tmp")
//有Blob不适合用二级缓存?
//理解有误，延迟加载不进入二级缓存，但不影响其它字段的二级缓存。二级缓存适合经常读取又不太写入的数据
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class BlobFile implements FieldHandled {
    //设置主键
    //配置uuid，本来jpa是不支持uuid的，但借用hibernate的方法可以实现。
    //@GeneratedValue(generator = "uuid")
    //@GenericGenerator(name = "uuid", strategy = "uuid")
    private transient FieldHandler fieldHandler;
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
    //mysql 当表中有Blob,Clob字段时，timestamp字段被当作text，此时应对应String,否则易出现不可预知的错误
    @Column//(columnDefinition = "timestamp")
    private String time;
    @Column
    private String type;

    @Override

    public FieldHandler getFieldHandler() {

        return fieldHandler;

    }

    @Override

    public void setFieldHandler(FieldHandler fieldHandler) {

        this.fieldHandler = fieldHandler;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getPhoto() {
        //return photo;
        if (fieldHandler != null) {
            return (byte[]) (fieldHandler.readObject(this, "photo", photo));
        }
        return null;
    }

    public void setPhoto(byte[] photo) {
        //this.photo = photo;
        if (fieldHandler == null) {

            this.photo = photo;

        } else {

            this.photo = (byte[]) fieldHandler.writeObject(this, "photo", this.photo, photo);

        }
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
