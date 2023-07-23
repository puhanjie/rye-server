package com.puhj.rye.service;

import com.puhj.rye.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author puhanjie
 * @since 2023-07-22
 */
public interface UserRoleService extends IService<UserRole> {

    boolean setRolesByUserId(Integer userId, List<Integer> roleIds);

    List<Integer> getRoleIdsByUserId(Integer userId);

}
