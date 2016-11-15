package main.basic.annotaion;

import main.basic.annotaion.anno.Column;
import main.basic.annotaion.anno.Entity;
import main.basic.annotaion.anno.ID;

/**
 * Created by androidjp on 2016/11/14.
 */
@Entity
public class Person {
    @ID
    public Integer id;
    @Column
    public String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
