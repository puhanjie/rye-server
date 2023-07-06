package com.puhj.rye.vo;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author puhanjie
 * @description 分页结果VO
 * @create 2023-6-17
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageVO<T> {
    private List<T> records;

    private long total;

    private long size;

    private long current;

    private long pages;

    public PageVO(Page<T> page) {
        this.records = page.getRecords();
        this.total = page.getTotal();
        this.size = page.getSize();
        this.current = page.getCurrent();
        this.pages = page.getPages();
    }
}
