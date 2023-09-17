package com.puhj.rye.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.puhj.rye.bo.PasswordBO;
import com.puhj.rye.bo.UserBO;
import com.puhj.rye.dto.UserDTO;
import com.puhj.rye.entity.User;
import com.puhj.rye.vo.FileVO;
import com.puhj.rye.vo.PageVO;
import com.puhj.rye.vo.UserBasicInfoVO;
import com.puhj.rye.vo.UserInfoVO;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author puhanjie
 * @since 2022-11-30
 */
public interface UserService extends IService<User> {

    boolean add(UserDTO userDTO);

    boolean edit(UserDTO userDTO);

    User getByUsername(String username);

    UserBasicInfoVO getBasicInfo();

    PageVO<UserInfoVO> list(Page<UserInfoVO> page, String username, String name, String phone, String email);

    int updatePassword(PasswordBO passwordBO);

    String modifyAvatar(User user, FileVO avatar) throws IOException;

    List<UserBO> getOptions();

}
