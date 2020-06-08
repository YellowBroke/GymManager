package com.scut.GymManager.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scut.GymManager.entity.UserBasic;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * create by YellowBroke on 2020年5月31日 21点47分
 */
public interface UserBasicMapper extends BaseMapper<UserBasic> {

    @Select("SELECT user_id FROM user_basic WHERE username = #{username}")
    String getUserIdByName(@Param("username") String username);

}
