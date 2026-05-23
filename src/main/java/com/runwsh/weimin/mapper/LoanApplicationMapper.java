
package com.runwsh.weimin.mapper;

import com.runwsh.weimin.entity.LoanApplication;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface LoanApplicationMapper {

    @Select("SELECT * FROM loan_applications WHERE id = #{id}")
    LoanApplication selectById(Long id);

    @Select("SELECT * FROM loan_applications WHERE user_id = #{userId} ORDER BY applied_at DESC")
    List<LoanApplication> selectByUserId(Long userId);

    @Select("SELECT * FROM loan_applications WHERE status = #{status} ORDER BY applied_at DESC")
    List<LoanApplication> selectByStatus(String status);

    @Select("SELECT * FROM loan_applications ORDER BY applied_at DESC")
    List<LoanApplication> selectAll();

    @Insert("INSERT INTO loan_applications (userId, amount, term, status, reason) " +
            "VALUES (#{userId}, #{amount}, #{term}, #{status}, #{reason})")
    int insert(LoanApplication loanApplication);

    @Update("UPDATE loan_applications SET status = #{status}, reviewed_at = #{reviewedAt} WHERE id = #{id}")
    int updateStatus(LoanApplication loanApplication);

    @Delete("DELETE FROM loan_applications WHERE id = #{id}")
    int deleteById(Long id);

}
