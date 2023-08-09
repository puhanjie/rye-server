package com.puhj.rye.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.puhj.rye.entity.Dictionary;
import com.baomidou.mybatisplus.extension.service.IService;
import com.puhj.rye.vo.DictionaryListVO;
import com.puhj.rye.vo.PageVO;

import java.util.List;

/**
 * <p>
 * 字典表 服务类
 * </p>
 *
 * @author puhanjie
 * @since 2023-07-27
 */
public interface DictionaryService extends IService<Dictionary> {

    PageVO<DictionaryListVO> list(Page<DictionaryListVO> page, String dictName, String itemText);

    List<Dictionary> getItems(String dictName);

}