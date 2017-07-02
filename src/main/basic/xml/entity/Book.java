package main.basic.xml.entity;

/**
 * 用于将XML文件内容，生成相应的Class类
 * Created by androidjp on 2017/7/1.
 */
public class Book {
    private String name;
    private String author;
    private int id;
    private int year;


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "这本书：id="+this.id+", name="+this.name+", author="+ this.author+", year="+this.year;
    }
}
