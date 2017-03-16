package com.rest.dao.impl;


import com.rest.aspect.OperLogAnnotation;
import com.rest.dao.ICommOperLogDao;
import com.rest.util.Constants;
import com.rest.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.Date;


/**
 * <br>处理操作日志业务实现</br>
 *
 * @Class CommLogServiceImpl
 * @Author lennylv
 * @Date 2016/11/29 17:37
 * @Version 1.0
 * @Since 1.0
 */
@Repository
public class CommOperLogDaoImpl implements ICommOperLogDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ICommOperLogDao.class);

    /**
     * <br>注入基本属性值</br>
     *
     * @param operLogObj, operLogAnnotation
     * @return void
     * @throws
     * @author lennylv
     * @date 2017-3-16 11:13
     * @version 1.0
     * @since 1.0
     */
    public void injectBasePropOperLog(Object operLogObj, OperLogAnnotation operLogAnnotation) {

        if (operLogObj == null || operLogAnnotation == null) {
            return;
        }

        Class clazz = operLogObj.getClass();
        Constants.Operate operate = operLogAnnotation.operate();
        Date time = new Date();
        try {
            if (operate == Constants.Operate.ADD) {
                Field cleanTime = clazz.getDeclaredField("createTime");
                cleanTime.setAccessible(true);
                cleanTime.set(operLogObj, time);
                Field cleanUserId = clazz.getDeclaredField("createUserId");
                cleanUserId.setAccessible(true);
//                cleanUserId.set(operLogObj, userId);
                Field updateTime = clazz.getDeclaredField("updateTime");
                updateTime.setAccessible(true);
                updateTime.set(operLogObj, time);
                Field updateUserId = clazz.getDeclaredField("updateUserId");
                updateUserId.setAccessible(true);
//                updateUserId.set(operLogObj, userId);
            } else if (operate == Constants.Operate.UPDATE || operate == Constants.Operate.STATUS) {
                Field updateTime = clazz.getDeclaredField("updateTime");
                updateTime.setAccessible(true);
                updateTime.set(operLogObj, time);
                Field updateUserId = clazz.getDeclaredField("updateUserId");
                updateUserId.setAccessible(true);
//                updateUserId.set(operLogObj, userId);
            }
        } catch (Exception e) {
            LOGGER.error("Error: check base pro error {}", e);
        }
    }


    /**
     * <br>保存操作详情日志到mongodb菲关系型数据库</br>
     *
     * @param operLogObj, operLogAnnotation
     * @return void
     * @throws
     * @author lennylv
     * @date 2017-3-16 11:13
     * @version 1.0
     * @since 1.0
     */
    @OperLogAnnotation(operate = Constants.Operate.UPDATE)
    public void saveOperLog(Object operLogObj, OperLogAnnotation operLogAnnotation) {
        // 插入到日志collection当中
        System.out.println(JsonUtil.obj2json(operLogObj));
    }


}
