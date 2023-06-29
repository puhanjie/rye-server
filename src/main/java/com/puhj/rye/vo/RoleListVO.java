package com.puhj.rye.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author puhanjie
 * @description 角色列表VO
 * @create 2023/6/15 23:08
 * @modify 2023/6/15 23:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleListVO {
    private Integer id;

    private String name;

    private String info;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private List<PermissionSimpleVO> permissions;
}
