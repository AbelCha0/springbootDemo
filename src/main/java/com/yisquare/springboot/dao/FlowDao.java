package com.yisquare.springboot.dao;




import com.yisquare.springboot.dao.query.QueryCondition;
import com.yisquare.springboot.pojo.Flow;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

@Mapper
public interface FlowDao {
    List<Flow> listFlowServiceByName(QueryCondition QueryCondition);

    int deleteFlowServiceByID(int id);

    int addFlowService(Flow flow);

    Flow getFlowByID(int id);

    Flow getFlowByCode(String systemCode, String flowName);

    int getFlowCount(String systemCode);

}
