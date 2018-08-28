package com.ofsoft.cms.core.api.check;

import com.ofsoft.cms.core.api.ApiErrorCode;
import com.ofsoft.cms.core.api.ApiException;

/**
 * @author OF
 * @version v1.0
 * @className CheckFactory
 * @date 2018/8/24
 */
public class CheckFactory {
    public static CheckInterface getStrategy(Class  value) throws ApiException {
        CheckInterface check = null;
        try {
          Object object =   value.newInstance();
            if(object instanceof  CheckInterface){
                check = (CheckInterface) object;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApiException(ApiErrorCode.getErrCode(ApiErrorCode.ERROR_CODE_1003));
        }
        return check;
    }
}
