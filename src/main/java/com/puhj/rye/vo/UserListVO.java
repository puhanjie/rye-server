package com.puhj.rye.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author puhanjie
 * @description 用户列表VO
 * @create 2023-6-15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserListVO {
    private Integer id;
    private String username;
    private String phone;
    private String avatar;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    private List<RoleSimpleVO> roles;
}
