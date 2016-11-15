package main.basic.annotaion;

import main.basic.annotaion.anno.Column;
import main.basic.annotaion.anno.ID;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 数据库 表字段 信息
 * Created by androidjp on 2016/11/14.
 */
public class ColumnInfo {
    private String columnName;///字段名称
    private Class<?> type;//字段类型
    private boolean isID = false;//主键？
    private boolean nullable = true;//非空？
    private boolean isAutoIncrement = false;//自增？
    private int length = 32;//字段长度（默认）
    private boolean needPersist = false; ///是否保存本字段到数据库中

    public ColumnInfo parse(Field field) {
        this.columnName = field.getName();
        this.type = field.getType();
        Annotation[] annotations = field.getAnnotations();

        ////做注解的两个判断：①如果是@Column 则说明有字段别名，需要更新字段名 ②如果是@ID，说明是主键
        for (Annotation annotation:annotations){
            if (annotation.annotationType().equals(Column.class)){
                this.needPersist = true;
                Column column = (Column)annotation;//强转赋值
                if (!column.value().equals("")) this.columnName=column.value();
                this.nullable = column.nullable();
                this.isAutoIncrement = column.autoIncrement();
                if (column.length()!=-1) this.length = column.length();
            }else if (annotation.annotationType().equals(ID.class)){
                this.needPersist = true;
                ID id = (ID)annotation;
                isID = true;
                if (!id.value().equals("")){
                    this.columnName = id.value();
                }
            }
        }
        if (this.needPersist)
            return this;
        else
            return null;
    }


    @Override
    public String toString() {

        StringBuilder sql = new StringBuilder(columnName);
        if (this.type.equals(String.class)){
            sql.append(" VARCHAR(").append(this.length).append(")");
        }else if (this.type.equals(Integer.class)){
            sql.append(" INT");
        }
        if (this.isID){
            sql.append(" PRIMARY KEY");
        }
        if (this.isAutoIncrement){
            sql.append(" AUTO_INCREMENT");
        }
        if (!this.nullable){
            sql.append(" NOT NULL");
        }
        sql.append(";");
        return sql.toString();
    }
}
