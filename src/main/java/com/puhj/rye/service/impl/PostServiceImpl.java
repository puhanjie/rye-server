package com.puhj.rye.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puhj.rye.bo.PostBO;
import com.puhj.rye.vo.PostInfoVO;
import com.puhj.rye.common.constant.ResultCode;
import com.puhj.rye.common.exception.HttpException;
import com.puhj.rye.common.utils.ContrastUtil;
import com.puhj.rye.common.utils.SubjectUtil;
import com.puhj.rye.dto.PostDTO;
import com.puhj.rye.entity.Post;
import com.puhj.rye.mapper.PostMapper;
import com.puhj.rye.service.PostService;
import com.puhj.rye.vo.PageVO;
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
        Integer currentUserId = SubjectUtil.getSubjectId();
        Post post = new Post();
        post.setCode(postDTO.getCode());
        post.setName(postDTO.getName());
        post.setPostStatus(postDTO.getPostStatus());
        post.setRemark(postDTO.getRemark());
        post.setCreateUser(currentUserId);
        post.setUpdateUser(currentUserId);

        // 新增岗位
        if (this.postMapper.insert(post) <= 0) {
            throw new HttpException(ResultCode.POST_ADD_ERROR);
        }

        // 分配角色
        if (postDTO.getRoles() != null && !postDTO.getRoles().isEmpty()) {
            if (this.postMapper.insertRoleIdsByPostId(post.getId(), postDTO.getRoles()) <= 0) {
                throw new HttpException(ResultCode.POST_SET_ROLES_ERROR);
            }
        }

        return true;
    }

    @Transactional
    @Override
    public boolean edit(PostDTO postDTO) {
        Post post = this.postMapper.selectById(postDTO.getId());
        UpdateWrapper<Post> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", postDTO.getId())
                .set("code", postDTO.getCode())
                .set("name", postDTO.getName())
                .set("post_status", postDTO.getPostStatus())
                .set("remark", postDTO.getRemark())
                .set("update_user", SubjectUtil.getSubjectId());

        // 编辑岗位
        if (this.postMapper.update(post, updateWrapper) <= 0) {
            throw new HttpException(ResultCode.POST_EDIT_ERROR);
        }

        // 分配角色
        List<Integer> roleIds = this.postMapper.selectRoleIdsByPostId(postDTO.getId());
        Map<String, List<Integer>> roleMap = ContrastUtil.getContrast(roleIds, postDTO.getRoles());
        if (!roleMap.get("remove").isEmpty()) {
            // 清除被移除的角色
            if (!this.postMapper.deleteRoleIdsByPostId(postDTO.getId(), roleMap.get("remove"))) {
                throw new HttpException(ResultCode.POST_SET_ROLES_ERROR);
            }
        }
        if (!roleMap.get("add").isEmpty()) {
            // 添加新分配的角色
            if (this.postMapper.insertRoleIdsByPostId(postDTO.getId(), roleMap.get("add")) <= 0) {
                throw new HttpException(ResultCode.POST_SET_ROLES_ERROR);
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
        queryWrapper.isNull("delete_time");
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
