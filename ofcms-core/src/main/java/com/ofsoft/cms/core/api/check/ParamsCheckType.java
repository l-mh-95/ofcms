package com.ofsoft.cms.core.api.check;

import java.lang.annotation.*;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface ParamsCheckType {
    //名称
    String name();
    //最小长度
    int maxLength() default 0;
    //最大长度
    int minLength() default 0;
    //是否必填
    boolean isRequire() default true;
    //是否允许空值
    boolean isNotNull() default true;
    //是否解密
    boolean isDecrypt() default false;
    //检验类型
    Class checkType() default DefaultCheck.class;


}