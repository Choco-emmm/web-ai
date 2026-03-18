package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/*
*
*
* 员工信息
* */

@Mapper
public interface EmpMapper {

//    @Select("select e.*,d.name deptName from emp e left join dept d on e.dept_id=d.id order by entry_date desc")
    public List<Emp> list(EmpQueryParam empQueryParam);

    @Options(useGeneratedKeys = true, keyProperty = "id")
   public  void add(Emp emp);

    void deleteById(List<Integer> ids);

    Emp getInfoById(Integer id);

    void updateById(Emp emp);
}
