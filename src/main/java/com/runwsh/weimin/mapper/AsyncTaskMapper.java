package com.runwsh.weimin.mapper;

import com.runwsh.weimin.entity.AsyncTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AsyncTaskMapper {
    int insert(AsyncTask task);
    
    int updateById(AsyncTask task);
    
    AsyncTask selectById(Long id);
    
    List<AsyncTask> selectByStatus(@Param("status") String status);
    
    List<AsyncTask> selectByType(@Param("type") String type);
    
    List<AsyncTask> selectPendingTasks();
}