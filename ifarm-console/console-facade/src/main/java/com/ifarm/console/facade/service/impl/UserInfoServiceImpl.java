package com.ifarm.console.facade.service.impl;

import com.bigvision.web.util.Constants;
import com.github.framework.util.string.StringUtils;
import com.github.pagehelper.PageHelper;
import com.ifarm.console.facade.service.IUserInfoService;
import com.ifarm.console.mapper.UserMapper;
import com.ifarm.console.shared.define.IFarmConstants;
import com.ifarm.console.shared.domain.dto.UserInfoDTO;
import com.ifarm.console.shared.domain.po.PermissionNotRolePO;
import com.ifarm.console.shared.domain.po.UserInfoPO;
import com.ifarm.console.shared.domain.po.UserRolePO;
import com.ifarm.console.shared.domain.vo.UserInfoVO;
import com.ifarm.console.shared.exception.RegisterException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 **/
@Service
public class UserInfoServiceImpl implements IUserInfoService {

    private Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);
    Integer mapBase = 1000000;
    Integer permissionPossibility = 1000;
    
    @Autowired
    private UserMapper userMapper;

    private void check(UserInfoVO userInfoVO) {
        if (userInfoVO == null || userInfoVO.getUserInfoDTO() == null) {
            throw new IllegalArgumentException("参数不能为空！");
        }
    }

    @Override
    @Transactional
    public int distributeRole(UserInfoVO userInfoVO) {
        if (userInfoVO == null) {
            throw new IllegalArgumentException("参数不能为空！");
        }
        Integer userId = userInfoVO.getUserId();
        List<Integer> roleIds = userInfoVO.getIds();
        if (userId == null) {
            logger.info("userId is empty");
            throw new IllegalArgumentException("userId is empty");
        }
        //每次分配角色，清空
        userMapper.deleteUserRole(userId);
        if (roleIds == null || roleIds.isEmpty()) {
            return 0;
        }
        List<UserRolePO> list = new ArrayList<>(roleIds.size());
        UserRolePO po = null;
        for (Integer roleId : roleIds) {
            po = new UserRolePO();
            po.setRoleTid(roleId);
            po.setUserTid(userId);
            po.setCreateTime(new Date());
            list.add(po);
        }
        return userMapper.saveUserRoleBatch(list);
    }

    @Override
    public UserInfoDTO findById(Integer tid) {
        UserInfoDTO userInfoDTO = null;
        if (tid != null) {
            UserInfoPO userInfoPO = userMapper.findById(tid);
            if (userInfoPO != null) {
                userInfoDTO = userInfoPO.convertDTO();
            }
        }

        return userInfoDTO;
    }

    @Override
    public UserInfoVO findByParam(UserInfoVO userInfoVO) {
        //check
        this.check(userInfoVO);
        //query
        PageHelper.startPage(userInfoVO.getPageNo(), userInfoVO.getPageSize());
        UserInfoDTO userInfoDTO = userInfoVO.getUserInfoDTO();
        UserInfoPO userInfoPO = userInfoDTO.convertPO();
        List<UserInfoDTO> result = new ArrayList<>();
        List<UserInfoPO> userInfoPOS = userMapper.findByParam(userInfoPO);
        for (UserInfoPO po : userInfoPOS) {
            result.add(po.convertDTO());
        }
        userInfoVO.setTotalCount(userMapper.totalCount(userInfoPO));
        userInfoVO.setUserInfoDTOS(result);
        return userInfoVO;
    }

    @Override
    public long totalCount(UserInfoVO userInfoVO) {
        this.check(userInfoVO);
        //query
        UserInfoPO userInfoPO = userInfoVO.getUserInfoDTO().convertPO();
        return userMapper.totalCount(userInfoPO);
    }

    @Override
    public UserInfoDTO findByUserName(String userName) {
        if (StringUtils.isBlank(userName)) {
            throw new IllegalArgumentException("参数不能为空！");
        }
        UserInfoPO userInfoPO = userMapper.findByUserName(userName);
        if (userInfoPO != null) {
            return userInfoPO.convertDTO();
        }
        return null;
    }

    @Override
    public UserInfoDTO findLoginUser(String userName) {
        if (StringUtils.isBlank(userName)) {
            throw new IllegalArgumentException("参数不能为空！");
        }
        UserInfoPO userInfoPO = userMapper.findLoginUser(userName);
        if (userInfoPO != null) {
            return userInfoPO.convertDTO();
        }
        return null;
    }

    @Override
    public void changePassword(UserInfoVO userInfoVO) {
        //TODO
    }

    @Override
    public int delete(UserInfoVO userInfoVO) {
        if (userInfoVO == null) {
            throw new IllegalArgumentException("参数不能为空");
        }
       List< Integer> userids = userInfoVO.getIds();
       int rt = 1;
       for(Integer userid:userids)
       {
        logger.info("delete user by id:"+userid);
        rt =userMapper.deleteUserPermissionNotRole(userid);
        rt = userMapper.deleteUserByID(userid);
       }
        /*
        List<Integer> ids = userInfoVO.getIds();
        if (ids == null || ids.isEmpty()) {
            return 0;
        }
        return userMapper.updateActive(ids, IFarmConstants.INACTIVE);*/
        
        return rt;
    }

    @Override
    public int delete(String userName) {
        if (StringUtils.isBlank(userName)) {
            throw new IllegalArgumentException("参数不能为空！");
        }
        UserInfoPO userInfoPO = new UserInfoPO();
        userInfoPO.setUserName(userName);
        userInfoPO.setActive(IFarmConstants.INACTIVE);
        userInfoPO.setModifyTime(new Date());
        return userMapper.update(userInfoPO);
    }

    
    
    @Override
    public int update(UserInfoVO userInfoVO) {
        this.check(userInfoVO);
        //update
        UserInfoPO userInfoPO = userInfoVO.getUserInfoDTO().convertPO();
        userInfoPO.setModifyTime(new Date());
   
        int rt =  userMapper.update(userInfoPO);
        //set user permission
        List<String> permissions =Constants.convertRoleToResourceID( userInfoVO.getPermissionsNotRole());
        
        Integer userid =userInfoVO.getUserInfoDTO().getTid();
        //do map for new permission table id
        Integer resouceidbase = mapBase+permissionPossibility*(userid-2);
        logger.info("resouceidbase from:"+resouceidbase);
        userMapper.deleteUserPermissionNotRole(userid);
        for(String p:permissions)
        {
        	 PermissionNotRolePO upo = new PermissionNotRolePO();
        	upo.setTid(resouceidbase);
        	upo.setUserid(userid);
        	upo.setPermissionid(new Integer(p));
        	upo.setCreateTime(new Date());
        	resouceidbase++;
        	userMapper.insertUserPermissionNotRole(upo);
        }
     
        return rt;
    }

    @Override
    public int insert(UserInfoVO userInfoVO) {
        this.check(userInfoVO);
        UserInfoDTO userInfoDTO = userInfoVO.getUserInfoDTO();
        if (StringUtils.isBlank(userInfoDTO.getUserName())) {
            throw new RegisterException("用户名不能为空");
        } else if (StringUtils.isBlank(userInfoDTO.getPassword())) {
            throw new RegisterException("登录密码不能为空");
        }
        String userName = userInfoDTO.getUserName();
        String password = userInfoDTO.getPassword();
        UserInfoDTO exist = this.findByUserName(userName);
        if (exist != null) {
            throw new RegisterException("此用户名已注册");
        }
       
        // 将用户名作为盐值
        ByteSource salt = ByteSource.Util.bytes(userName);
        /*
        * MD5加密：
        * 使用SimpleHash类对原始密码进行加密。
        * 第一个参数代表使用MD5方式加密
        * 第二个参数为原始密码
        * 第三个参数为盐值，即用户名
        * 第四个参数为加密次数
        * 最后用toHex()方法将加密后的密码转成String
        * */
        String newPs = new SimpleHash("MD5", password, salt, 2).toHex().toUpperCase();

        userInfoDTO.setPassword(newPs);
        userInfoDTO.setSalt(salt.toString());
        //insert
        UserInfoPO userInfoPO = userInfoVO.getUserInfoDTO().convertPO();
        userInfoPO.setCreateTime(new Date());
        userInfoPO.setModifyTime(new Date());
        userInfoPO.setActive(IFarmConstants.ACTIVE);
        
        int ret =  userMapper.insert(userInfoPO);
        //set user permission
        List<String> permissions =Constants.convertRoleToResourceID( userInfoVO.getPermissionsNotRole());
        UserInfoPO po = userMapper.findLoginUser(userInfoPO.getUserName());
        Integer userid = po.getTid();
        //do map for new permission table id
        Integer resouceidbase = mapBase+permissionPossibility*(userid-2);
        logger.info("resouceidbase from:"+resouceidbase);
       
        for(String p:permissions)
        {
        	 PermissionNotRolePO upo = new PermissionNotRolePO();
        	upo.setTid(resouceidbase);
        	upo.setUserid(userid);
        	upo.setPermissionid(new Integer(p));
        	upo.setCreateTime(new Date());
        	resouceidbase++;
        	userMapper.insertUserPermissionNotRole(upo);
        }
        return ret;
    }

	@Override
	public int updatePWD(UserInfoVO userInfoVO) {
		// TODO Auto-generated method stub
		int rt = -1;
		 this.check(userInfoVO);
	        //update
	        UserInfoPO userInfoPO = userInfoVO.getUserInfoDTO().convertPO();
	        userInfoPO.setModifyTime(new Date());
	   
		//set new password
        
	       
        if(userInfoPO.getPassword()!=null && userInfoPO.getPassword()!= "")
        {
        logger.info("change user password, user:"+userInfoPO.getUserName());
        String userName = userInfoPO.getUserName();
        String password = userInfoPO.getPassword();
       
       
        // 将用户名作为盐值
        ByteSource salt = ByteSource.Util.bytes(userName);
        /*
        * MD5加密：
        * 使用SimpleHash类对原始密码进行加密。
        * 第一个参数代表使用MD5方式加密
        * 第二个参数为原始密码
        * 第三个参数为盐值，即用户名
        * 第四个参数为加密次数
        * 最后用toHex()方法将加密后的密码转成String
        * */
        String newPs = new SimpleHash("MD5", password, salt, 2).toHex().toUpperCase();

        userInfoPO.setPassword(newPs);
       rt= userMapper.update(userInfoPO);
        }
		return rt;
	}
}
