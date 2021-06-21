package com.yisquare.springboot.dao;


import com.yisquare.springboot.dao.query.QueryCondition;
import com.yisquare.springboot.pojo.InterfaceInfo;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface InterfaceDao {

    List<InterfaceInfo>  listInterface(QueryCondition queryCondition);

    int deleteInterface(InterfaceInfo interfaceInfo);

    int addInterface(InterfaceInfo interfaceInfo);

    int updateInterface(InterfaceInfo interfaceInfo);

    InterfaceInfo getInterfaceInfoByID(int id);

    InterfaceInfo getInterfaceInfoByCode(String systemCode, String apiCode);



}
