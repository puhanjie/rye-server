package com.puhj.rye.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.puhj.rye.bo.DictionaryBO;
import com.puhj.rye.bo.RoleBO;
import com.puhj.rye.bo.UserBO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author puhanjie
 * @description 岗位列表数据对象
 * @create 2023-08-29
 */
@Schema(name = "PostInfoVO", description = "岗位列表数据对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostInfoVO {

    @Schema(description = "岗位id")
    private Integer id;

    @Schema(description = "岗位编码")
    private String code;

    @Schema(description = "岗位名称")
    private String name;

    @Schema(description = "岗位状态")
    private DictionaryBO postStatus;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "创建者")
    private UserBO createUser;

    @Schema(description = "更新者")
    private UserBO updateUser;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

    @Schema(description = "角色")
    private List<RoleBO> roles;

}
