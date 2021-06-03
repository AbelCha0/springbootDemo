package com.yisquare.springboot.dao;




import com.yisquare.springboot.dao.query.QueryCondition;
import com.yisquare.springboot.pojo.Flow;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.List;

@Mapper
@Repository
public interface FlowDao {
    List<Flow> listFlowServiceByName(QueryCondition QueryCondition);

    int deleteFlowService(Flow flow);

    int addFlowService(Flow flow);

    Flow getFlowByID(int id);

}