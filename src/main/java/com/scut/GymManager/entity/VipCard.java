package com.scut.GymManager.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("vip_card")
public class VipCard {

    @TableId(value = "vip_id")
    private String vipId;

    private int validTime;

    private boolean cardStatus;

    private boolean classStatus;
}
