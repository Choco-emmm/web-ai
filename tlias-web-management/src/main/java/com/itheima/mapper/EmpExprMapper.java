package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 工作经历Mapper
 */
@Mapper
public interface EmpExprMapper {
    void add(List<EmpExpr> exprList);

    void deleteByEmpId(List<Integer> ids);
}
