package com.yisquare.springboot.dao;



import com.yisquare.springboot.dao.query.QueryCondition;
import com.yisquare.springboot.pojo.SystemInfo;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface SystemDao {


    List<SystemInfo>  listSystemInfoByCode(QueryCondition queryCondition);

    SystemInfo getSystemInfoByCode(String systemCode);

    int updateSystemInfo(SystemInfo systemInfo);

    int createSystemInfo(SystemInfo systemInfo);

    int deleteSystemInfo(String systemCode);

    int deleteSystemIP(String systemCOde);

    int insertSystemIP(SystemInfo systemInfo);

}
