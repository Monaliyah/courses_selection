package com.me.courses_selection.validator;

import com.me.courses_selection.utils.idUtil;
import org.thymeleaf.util.StringUtils;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * id判断工具类
 */
public class isIdValidator implements ConstraintValidator<isId,String> {

    private  boolean required=false;

    @Override
    public void initialize(isId constraintAnnotation) {
        required=constraintAnnotation.required();
    }

    /**
     * id判断函数
     */
    @Override
    public boolean isValid(String value,ConstraintValidatorContext context) {
        System.out.printf("a");
        if(required){
            return idUtil.isId(value);
        }else{
            if(StringUtils.isEmpty(value)){
                return true;
            }else{
                return idUtil.isId(value);
            }
        }
    }
}
