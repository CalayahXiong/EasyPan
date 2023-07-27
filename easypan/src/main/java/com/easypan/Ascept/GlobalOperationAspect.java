package com.easypan.Ascept;

import com.easypan.annotation.GlobalInterceptor;
import com.easypan.annotation.VerifyParam;
import com.easypan.enums.ResponseCodeEnum;
import com.easypan.exception.BusinessException;
import com.easypan.utils.StringTools;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 全局拦截切面
 */
@Aspect
@Component("globalOperationAscpet")
public class GlobalOperationAspect {

    private static final Logger logger = LoggerFactory.getLogger(GlobalOperationAspect.class);

    private static final String TYPE_STRING = "java.lang.String";
    private static final String TYPE_INTEGER = "java.lang.Integer";
    private static final String TYPE_LONG = "java.lang.Long";

    @Pointcut("@annotation(com.easypan.annotation.GlobalInterceptor)")
    private void requestInterceptor() {

    }
    //事件通知
    @Before("requestInterceptor()")
    public void interceptorDo(JoinPoint point) throws BusinessException {
        try {
            Object target = point.getTarget();
            Object[] arguments = point.getArgs();
            String methodName = point.getSignature().getName();
            Class<?>[] parameterTypes = ((MethodSignature) point.getSignature()).getMethod().getParameterTypes();
            Method method = target.getClass().getMethod(methodName, parameterTypes);
            GlobalInterceptor interceptor = method.getAnnotation(GlobalInterceptor.class);
            if (null == interceptor) {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validateParams(Method m, Object[] arguments) throws BusinessException {
        Parameter[] parameters = m.getParameters();
        for(int i = 0; i < parameters.length; i++) {
            Parameter parameter = parameters[i];
            Object value = arguments[i];
            VerifyParam verifyParam = parameter.getAnnotation(VerifyParam.class);
            if (verifyParam == null) {
                continue;
            }
            //基本数据类型
            if (TYPE_STRING.equals(parameter.getParameterizedType().getTypeName()) ||
                    TYPE_LONG.equals(parameter.getParameterizedType().getTypeName()) ||
                    TYPE_INTEGER.equals(parameter.getParameterizedType().getTypeName())) {
                checkValue(value, verifyParam);
            } else { //传递的是对象
                checkObjectValue(parameter, value);
            }
        }
    }

    private void checkObjectValue(Parameter parameter, Object value) {
        try {
            String typeName = parameter.getParameterizedType().getTypeName();
            Class classz = Class.forName(typeName);
            Field[] fields = classz.getDeclaredFields();
            for (Field field : fields) {
                VerifyParam verifyParam = field.getAnnotation(VerifyParam.class);
                if(verifyParam == null) continue;
                field.setAccessible(true);
                Object resultValue = field.get(value);
                checkValue(resultValue, verifyParam);
            }
        } catch (BusinessException e) {
            logger.error("校验参数失败", e);
            throw e;
        } catch (Exception e) {
            logger.error("校验参数失败", e);
            throw new BusinessException(ResponseCodeEnum.CODE_600);
        }
    }

    private void checkValue(Object value, VerifyParam param) throws BusinessException {
        Boolean isEmpty = value == null || StringTools.isEmpty(value.toString());
    }
}
