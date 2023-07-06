package com.puhj.rye.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author puhanjie
 * @description 权限列表VO
 * @create 2023-6-15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PermissionListVO {
    private Integer id;

    private String name;

    private String info;

    private String menu;

    private String menuName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
}
