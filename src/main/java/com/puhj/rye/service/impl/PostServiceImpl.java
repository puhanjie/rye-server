package com.puhj.rye.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puhj.rye.bo.PostBO;
import com.puhj.rye.common.constant.Result;
import com.puhj.rye.common.exception.HttpException;
import com.puhj.rye.common.utils.ContrastUtil;
import com.puhj.rye.dto.PostDTO;
import com.puhj.rye.entity.Post;
import com.puhj.rye.mapper.PostMapper;
import com.puhj.rye.service.PostService;
import com.puhj.rye.vo.PageVO;
import com.puhj.rye.vo.PostInfoVO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 岗位表 服务实现类
 * </p>
 *
 * @author puhanjie
 * @since 2023-08-29
 */
@Service
public class PostServiceImpl extends ServiceImpl<PostMapper, Post> implements PostService {

    private final PostMapper postMapper;

    public PostServiceImpl(PostMapper postMapper) {
        this.postMapper = postMapper;
    }

    @Transactional
    @Override
    public boolean add(PostDTO postDTO) {
        Post post = postDTO.entity();

        // 新增岗位
        if (this.postMapper.insert(post) <= 0) {
            throw new HttpException(Result.POST_ADD_ERROR);
        }

        // 分配角色
        if (postDTO.getRoles() != null && !postDTO.getRoles().isEmpty()) {
            if (this.postMapper.insertRoleIdsByPostId(post.getId(), postDTO.getRoles()) <= 0) {
                throw new HttpException(Result.POST_SET_ROLES_ERROR);
            }
        }

        return true;
    }

    @Transactional
    @Override
    public boolean edit(PostDTO postDTO) {
        Post post = postDTO.entity();

        // 编辑岗位
        if (this.postMapper.updateById(post) <= 0) {
            throw new HttpException(Result.POST_EDIT_ERROR);
        }

        // 分配角色
        List<Integer> roleIds = this.postMapper.selectRoleIdsByPostId(postDTO.getId());
        Map<String, List<Integer>> roleMap = ContrastUtil.getContrast(roleIds, postDTO.getRoles());
        if (!roleMap.get("remove").isEmpty()) {
            // 清除被移除的角色
            if (!this.postMapper.deleteRoleIdsByPostId(postDTO.getId(), roleMap.get("remove"))) {
                throw new HttpException(Result.POST_SET_ROLES_ERROR);
            }
        }
        if (!roleMap.get("add").isEmpty()) {
            // 添加新分配的角色
            if (this.postMapper.insertRoleIdsByPostId(postDTO.getId(), roleMap.get("add")) <= 0) {
                throw new HttpException(Result.POST_SET_ROLES_ERROR);
            }
        }

        return true;
    }

    @Override
    public PageVO<PostInfoVO> list(Page<PostInfoVO> page, String code, String name) {
        Page<PostInfoVO> pageList = this.postMapper.list(page, code, name);
        return new PageVO<>(pageList);
    }

    @Override
    public List<PostBO> getOptions() {
        QueryWrapper<Post> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("post_status", "0").isNull("delete_time");
        List<Post> posts = this.postMapper.selectList(queryWrapper);
        return posts.stream().map(post -> {
            PostBO postBO = new PostBO();
            postBO.setId(post.getId());
            postBO.setCode(post.getCode());
            postBO.setName(post.getName());
            return postBO;
        }).toList();
    }

}
