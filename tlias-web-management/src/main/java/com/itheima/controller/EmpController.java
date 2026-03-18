package com.itheima.controller;


import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.impl.EmpServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Array;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/emps")
public class EmpController {
    @Autowired
    private EmpServiceImpl empService;

    @GetMapping
    public Result list(EmpQueryParam empQueryParam){
        log.info("分页查询:{}", empQueryParam);
        PageResult<Emp> pageResult = empService.list(empQueryParam);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("保存员工:{}", emp);
        empService.add(emp);
        return Result.success();
    }

    //删除员工
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){
        log.info("删除员工:{}", ids);


        empService.delete(ids);
        return Result.success();
    }

    //根据ID查询
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("查询员工信息:{}", id);
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    //修改员工
    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工:{}", emp);
        empService.update(emp);
        return Result.success();
    }
}
