/*
 * Gavin Tang
 * Each line should be prefixed with  * 
 */

package com.gtang.longclaw.core.j8sdk;

import com.gtang.longclaw.core.util.DateFormatUtil;
import java.util.Date;

/**
 *
 * @author gtang
 */
public class Person {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getSex() {
        return sex;
    }

    public void setSex(Gender sex) {
        this.sex = sex;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        
        return "Person{" + "id=" + id + ", name=" + name + 
                ", sex=" + sex + ", age=" + age + ", dateOfBirth=" + DateFormatUtil.toDayFormat(dateOfBirth) + '}';
    }
    
    private long id;
    private String name;
    private Gender sex;
    private int age;
    private Date dateOfBirth;

}


