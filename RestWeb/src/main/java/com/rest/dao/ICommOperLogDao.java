package com.rest.dao;


import com.rest.aspect.OperLogAnnotation;

/**
 * Created by lennylv on 2016/11/29.
 */
public interface ICommOperLogDao {

    void injectBasePropOperLog(Object operLogObj, OperLogAnnotation operate);

    void saveOperLog(Object operLogObj, OperLogAnnotation operLogAnnotation);

}
