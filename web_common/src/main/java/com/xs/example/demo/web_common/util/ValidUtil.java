package com.xs.example.demo.web_common.util;

import com.xs.example.demo.common.pojo.Result;
import org.springframework.validation.BindingResult;

/**
 * <p>
 * 统一参数校验工具类
 * </p>
 *
 * @author XieShuang
 * @version v1.0
 * @since 2018-12-17
 */
public class ValidUtil {

    /**
     * 统一参数校验返回错误信息方法
     * @param bindingResult
     * @return Result
     */
    public static Result VaildMessage(BindingResult bindingResult){
        StringBuffer stringBuffer = new StringBuffer();
        bindingResult.getAllErrors().forEach(objectError -> {
            stringBuffer.append(objectError.getDefaultMessage()).append(",");
        });
        stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        return Result.fail(stringBuffer.toString());
    }
}
