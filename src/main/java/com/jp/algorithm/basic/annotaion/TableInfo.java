package com.jp.algorithm.basic.annotaion;

import com.jp.algorithm.basic.annotaion.anno.Entity;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据库 表信息(sql语句的存储)
 * Created by androidjp on 2016/11/14.
 */
public class TableInfo {
    private String tableName;
    private Class<?> clazz;
    private boolean needPersist = false;
    private Map<String, ColumnInfo> columnInfos = new HashMap<>();

    public TableInfo parse(Class<?> clazz) {
        this.clazz = clazz;
        this.tableName = this.clazz.getSimpleName();
        Annotation[] annotations = this.clazz.getAnnotations();
        for (Annotation annotation : annotations) {
            if (annotation.annotationType().equals(Entity.class)) {
                this.needPersist = true;
                Entity entity = (Entity) annotation;
                if (!entity.value().equals("")) {
                    this.tableName = entity.value();
                }
                break;
            }
        }
        //说明有 @Entity 注解
        if (this.needPersist) {
            Field[] fields = this.clazz.getDeclaredFields();
            for (Field field : fields) {
                ColumnInfo columnInfo = new ColumnInfo();
                columnInfo = columnInfo.parse(field);
                if (columnInfo != null) {
                    this.columnInfos.put(field.getName(), columnInfo);
                }
            }
            return this;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        StringBuilder sql = new StringBuilder();
        sql.append("\n");
        sql.append("CREATE TABLE ");
        sql.append(this.tableName).append(" (");
        for (ColumnInfo columnInfo : this.columnInfos.values()) {
            sql.append("\n    ");
            sql.append(columnInfo.toString());
        }
        sql.append("\n);");
        return sql.toString();
    }
}
