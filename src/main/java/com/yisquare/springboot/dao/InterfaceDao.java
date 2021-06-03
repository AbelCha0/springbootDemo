package com.yisquare.springboot.dao;


import com.yisquare.springboot.dao.query.QueryCondition;
import com.yisquare.springboot.pojo.InterfaceInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface InterfaceDao {

    List<InterfaceInfo>  listInterface(QueryCondition queryCondition);

    int deleteInterface(InterfaceInfo interfaceInfo);

    int addInterface(InterfaceInfo interfaceInfo);

    int updateInterface(InterfaceInfo interfaceInfo);

    InterfaceInfo getInterfaceInfoByID(int id);

}
