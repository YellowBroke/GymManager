package com.scut.GymManager.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("vip_info")
public class VipInfo {

    @TableId("vip_id")
    private String vipId;

    private String idCard;

    private String name;

    private String phoneNumber;

    private Date birthday;
}
