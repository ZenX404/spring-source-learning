package org.spring.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// 要设置注解的生命周期是运行时，如果不设置运行时，那么代码在运行时注解就被去掉了，spring也就扫描不到了
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {
    public String value();
}
