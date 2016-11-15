package main.basic.annotaion;

import main.basic.annotaion.anno.Column;
import main.basic.annotaion.anno.Entity;
import main.basic.annotaion.anno.ID;

/**
 * Created by androidjp on 2016/11/14.
 */
@Entity("person")
public class Person {
    @ID("pid")
    @Column(autoIncrement=true,length = 10)
    public Integer id;
    @Column(value = "pname",length = 255)
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
