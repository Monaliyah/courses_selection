package com.me.courses_selection.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.*;


import java.math.BigDecimal;
import java.util.Map;

/**
 * 把map转换成对象
 * 把Map转换成指定类型
 * June
 */
@SuppressWarnings("rawtypes")
public class toBean {
    public static <T> T toBean(Map map, Class<T> clazz) {
        try {
            T bean = clazz.newInstance();
            ConvertUtils.register(new LongConverter(null), Long.class);
            ConvertUtils.register(new StringConverter(null), String.class);
            ConvertUtils.register(new IntegerConverter(null), Integer.class);
            ConvertUtils.register(new BigDecimalConverter(null), BigDecimal.class);
            ConvertUtils.register(new BooleanConverter(null), boolean.class);

            BeanUtils.populate(bean, map);
            return bean;}
        catch(Exception e) {
            throw new RuntimeException(e);
        }
    }
}