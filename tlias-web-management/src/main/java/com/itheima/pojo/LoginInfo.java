package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 封装登录结果
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginInfo {
    private Integer id;
    private String username;
    private String name;
    private String token;
}
