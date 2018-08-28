package com.ofsoft.cms.core.api.check;

import java.util.regex.Pattern;

/**
 * 数字检验
 * @author OF
 * @version v1.0
 * @className NumberCheck
 * @date 2018/8/24
 */
public class NumberCheck implements CheckInterface {
    @Override
    public boolean check(String value) {
        return Pattern.matches("^[1-9][0-9]\\d*$", value);
    }
}
