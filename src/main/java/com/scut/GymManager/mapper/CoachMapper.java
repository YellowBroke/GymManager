package com.scut.GymManager.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scut.GymManager.entity.CoachInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * create by wxh on 2020年6月18日
 */

public interface CoachMapper extends BaseMapper<CoachInfo>{

    @Select("SELECT coach_id FROM coach_info WHERE coach_name = #{coach_name}")
    String getCoachIdByName(@Param("coach_name") String coach_name);
}
