package com.njp.api.service.impl;

import com.njp.api.model.ValidationModel;
import com.njp.api.service.ValidationService;
import org.springframework.stereotype.Service;

/**
 * Created by niejinping on 2017/5/15.
 */
@Service("validationService")
public class ValidationServiceImpl implements ValidationService {
    @Override
    public ValidationModel verifyAccessToken(String accessToken) {
        return null;
    }
}
