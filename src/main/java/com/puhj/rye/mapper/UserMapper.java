package com.puhj.rye.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.puhj.rye.entity.User;
import com.puhj.rye.vo.UserListVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

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

    Page<UserListVO> list(Page<UserListVO> page,
                          @Param("username") String username,
                          @Param("phone") String phone,
                          @Param("email") String email);

}
