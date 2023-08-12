package com.puhj.rye.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.puhj.rye.entity.UserRole;
import com.puhj.rye.mapper.UserRoleMapper;
import com.puhj.rye.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 *
 * @author puhanjie
 * @since 2023-07-22
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    private final UserRoleMapper userRoleMapper;

    public UserRoleServiceImpl(UserRoleMapper userRoleMapper) {
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public boolean setRolesByUserId(Integer userId, List<Integer> roleIds) {
        // 获取当前用户角色
        List<Integer> roles = this.getRoleIdsByUserId(userId);
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();

        try {
            List<Integer> removeRoles = new ArrayList<>();
            // 被移除的角色
            for (Integer roleId : roles) {
                if (!roleIds.contains(roleId)) {
                    removeRoles.add(roleId);
                }
            }
            if (!removeRoles.isEmpty()) {
                queryWrapper.clear();
                queryWrapper.eq("user_id", userId).in("role_id", removeRoles);
                if (this.userRoleMapper.delete(queryWrapper) < 0) {
                    return false;
                }
            }

            // 新分配的角色
            for (Integer roleId : roleIds) {
                if (!roles.contains(roleId)) {
                    UserRole userRole = new UserRole();
                    userRole.setUserId(userId);
                    userRole.setRoleId(roleId);
                    if (this.userRoleMapper.insert(userRole) < 0) {
                        return false;
                    }
                }
            }
        } catch (NullPointerException e) {
            // 若roleIds为空且用户有角色数据时,则清空角色
            if (!roles.isEmpty() && roleIds == null) {
                queryWrapper.clear();
                queryWrapper.eq("user_id", userId);
                return this.userRoleMapper.delete(queryWrapper) >= 0;
            }
            return false;
        }
        return true;
    }

    @Override
    public List<Integer> getRoleIdsByUserId(Integer userId) {
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("role_id").eq("user_id", userId);
        // 提取查询到的UserRole集合的role_id作为新集合返回
        return this.userRoleMapper.selectList(queryWrapper)
                .stream()
                .map(UserRole::getRoleId)
                .toList();
    }

}
