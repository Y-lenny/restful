package com.rest.aspect;


import com.rest.util.Constants;

import java.lang.annotation.*;

/**
 * <br>用于标注增删改操作方法</br>
 *
 * @Class InupOperLogAnnotation
 * @Author lennylv
 * @Date 2016/11/30 19:42
 * @Version 1.0
 * @Since 1.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface OperLogAnnotation {
    Constants.Operate operate();
}
