package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

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

    /**
     * 统计员工职位人数
     * @return
     */
    @MapKey("pos")
    //如果返回值是map需要用注解指定谁是键，但我们是list就不用了
    // 但由于插件问题还是报错了
    List<Map<String,Object>> countEmpJobData();

    /**
     * 统计员工性别人数
     * @return
     */
    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();

    Emp selectByUsernameAndPassword(Emp emp);
}
