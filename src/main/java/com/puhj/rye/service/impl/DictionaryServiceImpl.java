package com.puhj.rye.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puhj.rye.vo.DictionaryInfoVO;
import com.puhj.rye.common.constant.ResultCode;
import com.puhj.rye.common.exception.HttpException;
import com.puhj.rye.common.utils.SubjectUtil;
import com.puhj.rye.dto.DictionaryDTO;
import com.puhj.rye.entity.Dictionary;
import com.puhj.rye.mapper.DictionaryMapper;
import com.puhj.rye.service.DictionaryService;
import com.puhj.rye.bo.DictionaryBO;
import com.puhj.rye.vo.PageVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional
    @Override
    public boolean add(DictionaryDTO dictionaryDTO) {
        Integer currentUserId = SubjectUtil.getSubjectId();
        Dictionary dictionary = new Dictionary();
        dictionary.setDictType(dictionaryDTO.getDictType());
        dictionary.setDictName(dictionaryDTO.getDictName());
        dictionary.setDictValue(dictionaryDTO.getDictValue());
        dictionary.setDictLabel(dictionaryDTO.getDictLabel());
        dictionary.setDescription(dictionaryDTO.getDescription());
        dictionary.setCreateUser(currentUserId);
        dictionary.setUpdateUser(currentUserId);

        // 新增字典
        if (this.dictionaryMapper.insert(dictionary) <= 0) {
            throw new HttpException(ResultCode.DICTIONARY_ADD_ERROR);
        }

        return true;
    }

    @Transactional
    @Override
    public boolean edit(DictionaryDTO dictionaryDTO) {
        Dictionary dictionary = this.dictionaryMapper.selectById(dictionaryDTO.getId());
        UpdateWrapper<Dictionary> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", dictionaryDTO.getId())
                .set("dict_type", dictionaryDTO.getDictType())
                .set("dict_name", dictionaryDTO.getDictName())
                .set("dict_value", dictionaryDTO.getDictValue())
                .set("dict_label", dictionaryDTO.getDictLabel())
                .set("update_user", SubjectUtil.getSubjectId());

        // 编辑字典
        if (this.dictionaryMapper.update(dictionary, updateWrapper) <= 0) {
            throw new HttpException(ResultCode.DICTIONARY_EDIT_ERROR);
        }

        return true;
    }

    @Override
    public PageVO<DictionaryInfoVO> list(Page<DictionaryInfoVO> page, String dictType, String dictLabel) {
        Page<DictionaryInfoVO> pageList = this.dictionaryMapper.list(page, dictType, dictLabel);
        return new PageVO<>(pageList);
    }

    @Override
    public List<DictionaryBO> getItems(String dictType) {
        QueryWrapper<Dictionary> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dict_type", dictType).isNull("delete_time");
        List<Dictionary> dictionaries = this.dictionaryMapper.selectList(queryWrapper);
        return dictionaries.stream().map(dictionary -> {
            DictionaryBO dictionaryBO = new DictionaryBO();
            dictionaryBO.setDictValue(dictionary.getDictValue());
            dictionaryBO.setDictLabel(dictionary.getDictLabel());
            return dictionaryBO;
        }).toList();
    }
}
