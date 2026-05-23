
package com.runwsh.weimin.mapper;

import com.runwsh.weimin.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    
    @Select("SELECT * FROM users WHERE id = #{id}")
    User selectById(Long id);
    
    @Select("SELECT * FROM users WHERE phone = #{phone}")
    User selectByPhone(String phone);
}
