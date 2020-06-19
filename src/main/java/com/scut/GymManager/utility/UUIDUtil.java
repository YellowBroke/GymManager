package com.scut.GymManager.utility;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * create by YellowBroke on 2020年6月18日 收件
 */

@Component
public class UUIDUtil {

    public String get32UUIDString(){
        String uuid = UUID.randomUUID().toString();

        uuid = uuid.replace("-","");
        return uuid;
    }


}
