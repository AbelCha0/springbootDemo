package com.yisquare.springboot.dao;


import com.yisquare.springboot.dao.query.QueryCondition;
import com.yisquare.springboot.pojo.SystemInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface SystemDao {

    List<SystemInfo> listSystemInfoByCode(QueryCondition queryCondition);

    SystemInfo getSystemInfoByCode(String systemCode);

    int updateSystemInfo(SystemInfo systemInfo);

    int updateSystemInfoByPatch(SystemInfo systemInfo);

    int createSystemInfo(SystemInfo systemInfo);

    int deleteSystemInfo(String systemCode);

    int deleteSystemMember(String systemCode);

    int deleteSystemIP(String systemCOde);

    int insertSystemMember(SystemInfo systemInfo);

    int insertSystemIP(SystemInfo systemInfo);
}