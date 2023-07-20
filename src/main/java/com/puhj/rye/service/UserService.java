package com.puhj.rye.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.puhj.rye.bo.PasswordBO;
import com.puhj.rye.entity.User;
import com.puhj.rye.vo.PageVO;
import com.puhj.rye.vo.UserListVO;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author puhanjie
 * @since 2022-11-30
 */
public interface UserService extends IService<User> {

    User getByUsername(String username);

    PageVO<UserListVO> getPageList(Page<UserListVO> page, String username, String phone, String email);

    int updatePassword(PasswordBO passwordBO);

}
