package com.burndy.algorithms.utils.mark;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 优雅的算法
 *
 * @author dingdong
 * @since 2021/4/23
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.SOURCE)
public @interface Graceful {

    /**
     * some description
     *
     * @return description
     */
    String value() default "";
}
