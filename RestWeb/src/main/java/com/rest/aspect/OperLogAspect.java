package com.rest.aspect;

import com.rest.dao.ICommOperLogDao;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <br> 拦截Service中增删改操作 </br>
 *
 * @Class ServiceLogAspect
 * @Author lennylv
 * @Date 2016/11/29 19:01
 * @Version 1.0
 * @Since 1.0
 */
@Aspect
@Component
public class OperLogAspect {

    @Autowired
    private ICommOperLogDao commOperLogDao;

    /**
     * <br>拦截save这种保存的操作</br>
     *
     * @param operLogObj, operLogAnnotation
     * @return void
     * @throws
     * @author lennylv
     * @date 2017-3-16 11:12
     * @version 1.0
     * @since 1.0
     */
    @Pointcut("args(operLogObj,..)&&@annotation(operLogAnnotation))")
    public void dmlOperLogPoint(Object operLogObj, OperLogAnnotation operLogAnnotation) {
    }


    /**
     * <br>拦截inup操作进而注入基本属性</br>
     *
     * @param operLogObj, operLogAnnotation
     * @return void
     * @throws
     * @author lennylv
     * @date 2017-3-16 11:12
     * @version 1.0
     * @since 1.0
     */
    @Before(value = "dmlOperLogPoint(operLogObj,operLogAnnotation)")
    public void injectBasePropOperLog(Object operLogObj, OperLogAnnotation operLogAnnotation) {
        // 根据不同的operate做对应的操作
        commOperLogDao.injectBasePropOperLog(operLogObj, operLogAnnotation);
    }


    /**
     * <br>拦截inup操作进而保存操作详细日志</br>
     *
     * @param operLogObj, operLogAnnotation
     * @return void
     * @throws
     * @author lennylv
     * @date 2017-3-16 11:11
     * @version 1.0
     * @since 1.0
     */
    @After(value = "dmlOperLogPoint(operLogObj,operLogAnnotation)")
    public void saveOperLog(Object operLogObj, OperLogAnnotation operLogAnnotation) {
        // 根据不同的operate做对应的操作
        commOperLogDao.saveOperLog(operLogObj, operLogAnnotation);
    }


}
