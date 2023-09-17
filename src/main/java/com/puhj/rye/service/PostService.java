package com.puhj.rye.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.puhj.rye.bo.PostBO;
import com.puhj.rye.dto.PostDTO;
import com.puhj.rye.entity.Post;
import com.puhj.rye.vo.PageVO;
import com.puhj.rye.vo.PostInfoVO;

import java.util.List;

/**
 * <p>
 * 岗位表 服务类
 * </p>
 *
 * @author puhanjie
 * @since 2023-08-29
 */
public interface PostService extends IService<Post> {

    boolean add(PostDTO postDTO);

    boolean edit(PostDTO postDTO);

    PageVO<PostInfoVO> list(Page<PostInfoVO> page, String code, String name);

    List<PostBO> getOptions();

}
