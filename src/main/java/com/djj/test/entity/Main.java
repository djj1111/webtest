package com.djj.test.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by djj on 2017/2/4.
 */

@Entity
@Table(name="main")
public class Main {
    private int id;
    private Date imputtime;
    private String user,num,cnum,name,address,cellphone,phone,year,monthe,money;
}
