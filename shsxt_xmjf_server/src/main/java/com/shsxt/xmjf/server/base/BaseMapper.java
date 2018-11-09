package com.shsxt.xmjf.server.base;

public interface BaseMapper<T> {
     int insert(T entity);

     T queryById(Integer id);


     int update(T entity);


    int delete(Integer id);

}
