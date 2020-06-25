package com.scut.GymManager.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * create by YellowBroke on 2020年5月31日 21点44分
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@TableName("user_basic")
public class UserBasic {

    @JsonIgnore
    @TableId(value = "user_id",type = IdType.ASSIGN_UUID)
    private String id;

    private String username;

    @JsonIgnore
    private String password;
}
