package com.ifarm.console.facade.controller;

import com.github.framework.util.cryp.Base64Utils;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.ifarm.console.facade.context.ConsoleContext;
import com.ifarm.console.facade.exceptions.AccessCodeInvalidException;
import com.ifarm.console.facade.service.IContentService;
import com.ifarm.console.facade.service.IResourceService;
import com.ifarm.console.facade.service.IUserInfoService;
import com.ifarm.console.shared.define.IFarmConstants;
import com.ifarm.console.shared.domain.dto.ResourceDTO;
import com.ifarm.console.shared.domain.dto.UserInfoDTO;
import com.ifarm.console.shared.domain.po.ContentPo;
import com.ifarm.console.shared.domain.po.UserInfoPO;
import com.ifarm.console.shared.domain.vo.ResponseVO;
import com.ifarm.console.shared.domain.vo.UserInfoVO;
import org.apache.commons.collections.map.HashedMap;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bigvision.web.util.AccessCodeStore;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static com.ifarm.console.shared.define.ResponseCode.*;

/**
 *
 **/

@RestController
public class LoginController extends AbstractController{
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);
    private static final String accessAttri = "rightCode";
    @Autowired
    private IUserInfoService userInfoService;

    
    @Autowired
    private IResourceService resourceService;
    
    @Autowired
    private IContentService contentSerivce;
    /**
     * 登录
     * @param userInfoVO
     * @return
     */
    @RequestMapping("/login")
    public ResponseVO<Map<String,Object>> login(@RequestBody UserInfoPO userInfoVO, HttpServletRequest request,HttpSession session) {
        ResponseVO responseVO = null;
        logger.info(userInfoVO.toString());
        UsernamePasswordToken token = new UsernamePasswordToken(userInfoVO.getUserName(), userInfoVO.getPassword());
        logger.info(userInfoVO.getUserName()+" try login system,"+" token:"+token.toString()+" sessionid:"+session.getId());
        Subject subject = SecurityUtils.getSubject();
        
        try {
        
            subject.login(token);
            //User
            UserInfoDTO userDetail = userInfoService.findByUserName(userInfoVO.getUserName());
            ConsoleContext.setCurrentUser(userDetail);
            //check access code 
            String accessToken = request.getHeader("accessToken");
            String accessCode = AccessCodeStore.getInstance().getToken(accessToken);
            logger.info("accessToken :"+accessToken+" accessCode:"+accessCode+" webbsubmitaccesscode:"+userInfoVO.getAccesscode());
            if(userInfoVO.getAccesscode()==null)
            {
            	logger.info("access code is empty");
            }
            if(userInfoVO.getAccesscode()!=null&&userInfoVO.getAccesscode().equals(accessCode))
            {
            	logger.info("access code correct:"+userInfoVO.getAccesscode());
            }
            else
            {
            	logger.info("access code is:"+(userInfoVO.getAccesscode()==null?null:userInfoVO.getAccesscode())+"  storedaccessCode："+accessCode);
            	throw new AccessCodeInvalidException("wrong access code for user:"+userInfoVO.getUserName());
            }
          

            Map<String, Object> resultMap = new HashedMap();
            resultMap.put("Authorization", session.getId());
            responseVO = returnSuccess();
            responseVO.setResult(resultMap);
            return responseVO;
        } catch (IncorrectCredentialsException e) {
            //密码错误
            logger.error("用户名/密码错误", e);
            responseVO = returnError("用户名/密码错误");
        } catch (LockedAccountException e) {
            //该用户已被冻结
            logger.error("该用户已被冻结", e);
            responseVO = returnError("该用户已被冻结");
        } catch (ExcessiveAttemptsException e){
            //尝试次数过多
            logger.error("尝试次数过多", e);
            responseVO = returnError("尝试次数过多");
        } catch (AuthenticationException e) {
            //该用户不存在
            logger.error("该用户不存在", e);
            responseVO = returnError("该用户不存在");
        } 
        catch(AccessCodeInvalidException e)
        {
        	logger.error("wrong access code for user:"+userInfoVO.getUserName());
        	responseVO = returnError("错误的随机码");
        }
        catch (Exception e) {
            logger.error("exception happened", e);
            responseVO = new ResponseVO(false, LOGIN_FAIL.getCode(), LOGIN_FAIL.getMessage());
        }
        return responseVO;
    }

    @RequestMapping("/userInfo")
    public ResponseVO userInfo() {
        ResponseVO<UserInfoVO> responseVO = returnSuccess();
        try {
            UserInfoVO userInfoVO = new UserInfoVO();
            String userName = ConsoleContext.getCurrentUserName();
            UserInfoDTO userInfoDTO = userInfoService.findByUserName(userName);
            userInfoDTO.clearCredentialsSalt();
            List<ResourceDTO> userMenus = resourceService.findMenuByUserAndParent(IFarmConstants.MENU_ROOT);
            userInfoVO.setUserInfoDTO(userInfoDTO);
            userInfoVO.setUserMenus(userMenus);
            responseVO.setResult(userInfoVO);
        } catch (Exception e) {
            logger.error("", e);
            return returnError(e.getMessage());
        }
        return responseVO;
    }

    @RequestMapping("/userMenu")
    public ResponseVO userMenu(String parentCode) {
        ResponseVO<List<ResourceDTO>> responseVO = returnSuccess();
        try {
            List<ResourceDTO> userMenus = resourceService.findMenuByUserAndParent(parentCode);
            responseVO.setResult(userMenus);
        } catch (Exception e) {
            logger.error("", e);
            return returnError(e.getMessage());
        }
        return responseVO;
    }

    @RequestMapping("/userPermission")
    public ResponseVO userPermission(String userName) {
        ResponseVO<List<String>> responseVO = returnSuccess();
        try {
            List<String> userMenus = resourceService.userPermission(userName);
            responseVO.setResult(userMenus);
        } catch (Exception e) {
            logger.error("", e);
            return returnError(e.getMessage());
        }
        return responseVO;
    }

    @RequestMapping("/userPermissionNotRole")
    public ResponseVO userPermissionNotRole(String userName) {
        ResponseVO<List<String>> responseVO = returnSuccess();
        try {
            List<String> userMenus = resourceService.userPermissionNotRole(userName);
            responseVO.setResult(userMenus);
          //  logger.info("permission is:"+responseVO.toString());
        } catch (Exception e) {
            logger.error("", e);
            return returnError(e.getMessage());
        }
        return responseVO;
    }

    /**
     * 登录成功
     * @return
     */
    @RequestMapping("/index")
    public ResponseVO index() {
        ResponseVO responseVO = new ResponseVO(true, LOGIN_SUCCESS.getCode(),LOGIN_SUCCESS.getMessage());
        return responseVO;
    }

    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     * @return
     */
    @RequestMapping(value = "/unauth")
    public ResponseVO unauth() {
        ResponseVO responseVO = new ResponseVO(false, UN_LOGIN.getCode(),UN_LOGIN.getMessage());
        return responseVO;
    }

    /**
     * 未授权
     * @return
     */
    @RequestMapping("/unauthorized")
    public ResponseVO unauthorized() {
        ResponseVO responseVO = new ResponseVO(false, UNAUTHORIZED.getCode(),UNAUTHORIZED.getMessage());
        return responseVO;
    }

    @RequestMapping("/logout")
    public ResponseVO logout() {
        try {
            Subject subject = SecurityUtils.getSubject();
            subject.logout();
        } catch (Exception e) {
            logger.error("", e);
            return returnError();
        }
        return returnSuccess();
    }

    Producer captchaProducer = null;
    @Autowired
    public void setCaptchaProducer(Producer captchaProducer) {
        this.captchaProducer = captchaProducer;
    }


    @Autowired
    DefaultKaptcha defaultKaptcha;
    @GetMapping("/getImageCode")
    public String doGetImageCode(HttpServletResponse response, HttpServletRequest request, HttpSession session) throws Exception {
    	
    	String accessToken=request.getHeader("accessToken");
    	
    	byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            // 生产验证码字符串并保存到session中
            String createText = defaultKaptcha.createText();
            logger.info("access code for session id:"+request.getSession().getId()+" code:"+createText);
            if(accessToken!=null)
            AccessCodeStore.getInstance().setToken(accessToken,createText);
            else
            	logger.error("accessCode token is null");
            request.getSession().setAttribute(accessAttri, createText);
            // 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = defaultKaptcha.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
            String base64ImageRepresentation =Base64Utils.encode(jpegOutputStream.toByteArray());
            jpegOutputStream.close();
           return base64ImageRepresentation;
           
        } catch (IllegalArgumentException e) {
        	logger.error("exception get:"+e.getMessage());
        	response.sendError(HttpServletResponse.SC_NOT_FOUND);
           // return;
        }
        catch(Exception e)
        {
        	logger.error("exception happend:"+e.getMessage());
        	e.printStackTrace();
        }
        return "Exception happened during image code created";

        // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        /*
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        
        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
        return null;*/
    }
    
    @RequestMapping("/cookie")
    public ResponseVO getCookie(HttpSession session) 
    {
    	
    	 ResponseVO responseVO = new  ResponseVO(true, SUCCESS.getCode(),SUCCESS.getMessage());
    	 Map<String, Object> resultMap = new HashedMap();
         resultMap.put("accessToken", UUID.randomUUID());
    	 responseVO.setResult(resultMap);
    	
         return responseVO;
    }
}
