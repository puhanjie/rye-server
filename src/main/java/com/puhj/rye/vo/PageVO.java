package com.puhj.rye.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author puhanjie
 * @description 分页结果数据对象
 * @create 2023-6-17
 */
@Schema(name = "PageVO", description = "分页结果数据对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVO<T> {

    @Schema(description = "结果记录")
    private List<T> records;

    @Schema(description = "数据总量")
    private long total;

    @Schema(description = "每页数据大小")
    private long size;

    @Schema(description = "当前页")
    private long current;

    @Schema(description = "总页数")
    private long pages;

    public PageVO(Page<T> page) {
        this.records = page.getRecords();
        this.total = page.getTotal();
        this.size = page.getSize();
        this.current = page.getCurrent();
        this.pages = page.getPages();
    }

}
