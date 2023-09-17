package com.puhj.rye.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.puhj.rye.vo.DictionaryInfoVO;
import com.puhj.rye.dto.DictionaryDTO;
import com.puhj.rye.entity.Dictionary;
import com.baomidou.mybatisplus.extension.service.IService;
import com.puhj.rye.bo.DictionaryBO;
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

    boolean add(DictionaryDTO dictionaryDTO);

    boolean edit(DictionaryDTO dictionaryDTO);

    PageVO<DictionaryInfoVO> list(Page<DictionaryInfoVO> page, String dictType, String dictLabel);

    List<DictionaryBO> getItems(String dictType);

}
