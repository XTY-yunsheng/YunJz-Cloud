package com.yunjz.common.core.myvalid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

/**
 * @author yunsheng
 * @create 2022-10-17 20:46
 */
public class DefaultStatusConstraintValidator implements ConstraintValidator<DefaultStatus, Integer> {

    private Set<Integer> set = new HashSet<>();

    @Override
    public void initialize(DefaultStatus constraintAnnotation) {
        int[] vals = constraintAnnotation.vals();
        for (int val : vals) {
            set.add(val);
        }
    }

    /**
     * 判断是否校验成功
     * @param value 需要校验的值
     * @param context
     * @return
     */
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return set.contains(value);
    }

}
