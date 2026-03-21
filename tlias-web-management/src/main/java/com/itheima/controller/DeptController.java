package com.itheima.controller;

import com.itheima.anno.Log;
import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import jakarta.servlet.annotation.WebServlet;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {
//    private static final Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

//@RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping
    public Result list() {
//        System.out.println("查询全部部门数据");
        log.info("查询全部部门数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }


    @Log
    @DeleteMapping
    public Result del(Integer id){
//        System.out.println("删除部门数据");
        log.info("删除部门数据");
        deptService.deleteById(id);
        return Result.success();
    }
    @Log
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("添加部门数据:{}",dept);
//        System.out.println("添加部门数据");
        deptService.add(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id){
        log.info("通过id查询部门数据:{}", id);
//        System.out.println("通过id查询部门数据");
        Dept dept = deptService.findById(id);
        return Result.success(dept);
    }

    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("修改部门数据:{}", dept);
//        System.out.println("修改部门数据");
        deptService.update(dept);
        return Result.success();
    }
}
