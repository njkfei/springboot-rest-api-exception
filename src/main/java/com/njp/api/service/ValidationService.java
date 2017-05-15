package com.njp.api.service;

import com.njp.api.model.ValidationModel;

/**
 * Created by niejinping on 2017/5/15.
 */
public interface ValidationService {
    ValidationModel verifyAccessToken(String accessToken);
}
