package com.puhj.rye.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.puhj.rye.vo.PostInfoVO;
import com.puhj.rye.entity.Post;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 岗位表 Mapper 接口
 * </p>
 *
 * @author puhanjie
 * @since 2023-08-29
 */
@Mapper
public interface PostMapper extends BaseMapper<Post> {

    int insertRoleIdsByPostId(@Param("postId") Integer postId, @Param("roleIds") List<Integer> roleIds);

    boolean deleteRoleIdsByPostId(@Param("postId") Integer postId, @Param("roleIds") List<Integer> roleIds);

    List<Integer> selectRoleIdsByPostId(@Param("postId") Integer postId);

    Page<PostInfoVO> list(Page<PostInfoVO> page,
                          @Param("code") String code,
                          @Param("name") String name);

}
