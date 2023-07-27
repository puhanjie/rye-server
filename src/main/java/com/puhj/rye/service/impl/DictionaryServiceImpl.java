package com.puhj.rye.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puhj.rye.entity.Dictionary;
import com.puhj.rye.mapper.DictionaryMapper;
import com.puhj.rye.service.DictionaryService;
import com.puhj.rye.vo.PageVO;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author puhanjie
 * @since 2023-07-27
 */
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements DictionaryService {

    private final DictionaryMapper dictionaryMapper;

    public DictionaryServiceImpl(DictionaryMapper dictionaryMapper) {
        this.dictionaryMapper = dictionaryMapper;
    }

    @Override
    public PageVO<Dictionary> query(Page<Dictionary> page, String dictName, String itemText) {
        Page<Dictionary> pageList = this.dictionaryMapper.query(page, dictName, itemText);
        return new PageVO<>(pageList);
    }
}
