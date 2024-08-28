package com.puhj.rye.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.puhj.rye.vo.UserInfoVO;
import com.puhj.rye.entity.User;
import com.puhj.rye.vo.*;
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

    int insertRoleIdsByUserId(@Param("userId") Integer userId, @Param("roleIds") List<Integer> roleIds);

    boolean deleteRoleIdsByUserId(@Param("userId") Integer userId, @Param("roleIds") List<Integer> roleIds);

    List<Integer> selectRoleIdsByUserId(@Param("userId") Integer userId);

    int insertPostIdsByUserId(@Param("userId") Integer userId, @Param("postIds") List<Integer> postIds);

    boolean deletePostIdsByUserId(@Param("userId") Integer userId, @Param("postIds") List<Integer> postIds);

    List<Integer> selectPostIdsByUserId(@Param("userId") Integer userId);

    UserBasicInfoVO selectBasicInfo(@Param("userId") Integer userId);

    Page<UserInfoVO> list(Page<UserInfoVO> page,
                          @Param("username") String username,
                          @Param("name") String name,
                          @Param("phone") String phone,
                          @Param("email") String email);

}
