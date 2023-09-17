package com.puhj.rye.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.puhj.rye.vo.DictionaryInfoVO;
import com.puhj.rye.entity.Dictionary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 字典表 Mapper 接口
 * </p>
 *
 * @author puhanjie
 * @since 2023-07-27
 */
@Mapper
public interface DictionaryMapper extends BaseMapper<Dictionary> {

    Page<DictionaryInfoVO> list(Page<DictionaryInfoVO> page,
                                @Param("dictType") String dictType,
                                @Param("dictLabel") String dictLabel);

}
