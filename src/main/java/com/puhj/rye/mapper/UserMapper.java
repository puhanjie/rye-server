package com.puhj.rye.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.puhj.rye.dto.UserPageDTO;
import com.puhj.rye.entity.User;
import com.puhj.rye.vo.UserListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author puhanjie
 * @since 2022-11-30
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    User selectByUsername(String username);

    List<UserListVO> selectAll();

    Page<UserListVO> selectPageList(Page<UserListVO> page, @Param("userPageDTO") UserPageDTO userPageDTO);

}
