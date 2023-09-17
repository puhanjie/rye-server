package com.puhj.rye.bo;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author puhanjie
 * @description 树节点数据对象
 * @create 2023-09-02
 */
@Schema(name = "TreeNodeBO", description = "树节点数据对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TreeNodeBO {

    @Schema(description = "id")
    private Integer id;

    @Schema(description = "父节点id")
    private Integer parentId;

    @Schema(description = "子节点")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    List<TreeNodeBO> children = new ArrayList<>();

    public void addTreeNode(TreeNodeBO treeNodeBO) {
        children.add(treeNodeBO);
    }

}
