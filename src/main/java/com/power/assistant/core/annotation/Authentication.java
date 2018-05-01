package com.power.assistant.core.annotation;

import java.lang.annotation.*;

/**
 * @author wuhanhong
 * @date 2018 - 04 - 30
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Authentication {
}
