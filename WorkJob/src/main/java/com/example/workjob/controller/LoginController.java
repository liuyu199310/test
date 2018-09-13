package com.example.workjob.controller;




import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.workjob.entity.BootStrapDataGrid;
import com.example.workjob.entity.UserInfo;
import com.example.workjob.entity.UserLogInfor;
import com.example.workjob.service.UserInfoService;
import com.example.workjob.service.UserLogInforService;
import com.example.workjob.until.CustomUntil;
import com.example.workjob.until.GeneralMethod;

/**
 * 
 * @author liuyu
 * @date 2018年8月30日
 *
 */
@Controller
public class LoginController {

	@Autowired
	private UserInfoService userInfoService;

	@Autowired
	private UserLogInforService userLogInforService;

	@Value(value = "${server.servlet.context-path}")
	private String workJob;

//	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping("/login")
	public String login(UserInfo userInfo, BindingResult bindingResult, RedirectAttributes redirectAttributes,
			HttpServletRequest request) throws Exception {
		if (bindingResult.hasErrors()) {
			return "login";
		}

		UsernamePasswordToken token = new UsernamePasswordToken(userInfo.getUsername(), userInfo.getPassword());
		Subject currentUser = SecurityUtils.getSubject();

		try {
			currentUser.login(token);
			
		} catch (IncorrectCredentialsException ice) {

			redirectAttributes.addFlashAttribute("error", "用户名或密码不正确！");
		} catch (UnknownAccountException uae) {

			redirectAttributes.addFlashAttribute("error", "未知账户！");
		} catch (LockedAccountException lae) {

			redirectAttributes.addFlashAttribute("error", "账户已锁定！");
		} catch (ExcessiveAttemptsException eae) {

			redirectAttributes.addFlashAttribute("error", "用户名或密码错误次数太多！");
		} catch (AuthenticationException ae) {

			ae.printStackTrace();
			redirectAttributes.addFlashAttribute("error", "用户名或密码不正确！");
		}

		if (currentUser.isAuthenticated()) {
			UserInfo userInfoMiddle = (@Valid UserInfo) currentUser.getPrincipal();
			request.setAttribute("userInfoMiddle", userInfoMiddle);


			return "layuimain";
		} else {
			token.clear();
			return "login";
		}
	}

	@RequestMapping("/quit")
	public String quit(HttpServletRequest request) {
		Subject currentUser = SecurityUtils.getSubject();
		UserInfo userInfoMiddle = (@Valid UserInfo) currentUser.getPrincipal();
		currentUser.logout();
		
		UserLogInfor userLogInfor = new UserLogInfor();
		userLogInfor.setCreateDate(GeneralMethod.getDate());
		userLogInfor.setMessage("用户退出系统");
		userLogInfor.setUsername(userInfoMiddle.getUsername());
		userLogInforService.insertUserLogInfor(userLogInfor);
		
		String filePath = request.getSession().getServletContext().getRealPath("logUplode/");
		String fileName=CustomUntil.getFileName(filePath,GeneralMethod.getDayString())+".log";
        GeneralMethod.writeFile(filePath,fileName, userLogInfor.toString());

		return "redirect:" + "/login";
	}

	@RequestMapping("/index")
	public String index(HttpServletRequest request) {

		Subject currentUser = SecurityUtils.getSubject();
		UserInfo userInfoMiddle = (@Valid UserInfo) currentUser.getPrincipal();
		request.setAttribute("userInfoMiddle", userInfoMiddle);
		// String strDirPath =
		// request.getServletContext().getRealPath("/resources/static/static/images/");
		// strDirPath=strDirPath.replaceAll("\\\\"+"webapp", "");
		// String strDirPath = request.getServletContext().getRealPath("/");
		// File file =new File(strDirPath);
		// System.out.println(file.getParent());
		return "index";
	}
	
	
	@RequestMapping("/layuimain")
	public String layuimain(HttpServletRequest request) {

		Subject currentUser = SecurityUtils.getSubject();
		UserInfo userInfoMiddle = (@Valid UserInfo) currentUser.getPrincipal();
		userInfoMiddle=userInfoService.findByUsername(userInfoMiddle.getUsername());
		request.setAttribute("userInfoMiddle", userInfoMiddle);
		return "layuimain";
	}
	
	@RequestMapping("/userInfo")
	public String userInfo(HttpServletRequest request) {

		Subject currentUser = SecurityUtils.getSubject();
		UserInfo userInfoMiddle = (@Valid UserInfo) currentUser.getPrincipal();
		userInfoMiddle=userInfoService.findByUsername(userInfoMiddle.getUsername());
		request.setAttribute("userInfoMiddle", userInfoMiddle);
		return "userInfo";
	}
	
	@RequestMapping("/changePwd")
	public String changePwd(HttpServletRequest request) {

		Subject currentUser = SecurityUtils.getSubject();
		UserInfo userInfoMiddle = (@Valid UserInfo) currentUser.getPrincipal();
		request.setAttribute("userInfoMiddle", userInfoMiddle);
		return "changePwd";
	}
	
	@RequestMapping(value="/firstIndex",method=RequestMethod.GET)
	public String firstIndex(HttpServletRequest request) {

		Subject currentUser = SecurityUtils.getSubject();
		UserInfo userInfoMiddle = (@Valid UserInfo) currentUser.getPrincipal();
		request.setAttribute("userInfoMiddle", userInfoMiddle);
		
		UserLogInfor userLogInfor = new UserLogInfor();
		userLogInfor.setCreateDate(GeneralMethod.getDate());
		userLogInfor.setMessage("用户登录系统");
		userLogInfor.setUsername(userInfoMiddle.getUsername());
		userLogInforService.insertUserLogInfor(userLogInfor);
		
		String filePath = request.getSession().getServletContext().getRealPath("logUplode/");
		String fileName=CustomUntil.getFileName(filePath,GeneralMethod.getDayString())+".log";
        GeneralMethod.writeFile(filePath,fileName, userLogInfor.toString());
		
		return "redirect:" + "/layuimain";
	}
	
	

	@RequestMapping("/register")
	public String register() {
		return "register";
	}
	
	@RequestMapping("/homePage")
	public String homePage() {
		return "homePage";
	}
	
	@RequestMapping("/updataPassword")
	@ResponseBody
	public String updataPassword(UserInfo userInfo) {
		
		
		return userInfoService.updataPassword(userInfo);
	}
	
	@RequestMapping("/updataUserInfo")
	@ResponseBody
	public String updataUserInfo(UserInfo userInfo, RedirectAttributes redirectAttributes,
			@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		
		String fileName = file.getOriginalFilename();
		int uid = (int) Math.round(Math.random() * 10000);
		fileName = GeneralMethod.getDateString() + uid + fileName;
		String filePath = request.getSession().getServletContext().getRealPath("imgupload/");
		try {
			CustomUntil.uploadFile(file.getBytes(), filePath, fileName);
		} catch (Exception e) {

		}
		userInfo.setHeadPortraitPath(workJob + "/imgupload/" + fileName);
		UserInfo oldUserInfo=userInfoService.findByUsername(userInfo.getUsername());
		String[] oldFileName=oldUserInfo.getHeadPortraitPath().split("/imgupload/");
		CustomUntil.deleteLoadFile(filePath, oldFileName[1]);
		userInfoService.updataUserInfo(userInfo);
		return "";
	}
	

	@RequestMapping(value = "/registerUser", method = RequestMethod.POST)
	public String registerUser(UserInfo userInfo, RedirectAttributes redirectAttributes,
			@RequestParam("file") MultipartFile file, HttpServletRequest request) {

		String fileName = file.getOriginalFilename();
		/*
		 * System.out.println("fileName-->" + fileName);
		 * System.out.println("getContentType-->" + contentType);
		 */

		int uid = (int) Math.round(Math.random() * 10000);
		userInfo.setUid(uid);
		userInfo.setName("普通注册用户");
		if (null == fileName || "".equals(fileName)) {
			userInfo.setHeadPortraitPath(workJob + "/imgupload/user_defalut.png");
		} else {
			fileName = GeneralMethod.getDateString() + uid + fileName;
			String filePath = request.getSession().getServletContext().getRealPath("imgupload/");
			try {
				CustomUntil.uploadFile(file.getBytes(), filePath, fileName);
			} catch (Exception e) {

			}
			userInfo.setHeadPortraitPath(workJob + "/imgupload/" + fileName);
		}

		userInfoService.insertUserInfo(userInfo);
		return "redirect:" + "/login";
	}
	
	
	@RequestMapping(value = { "getUserLogInforListData" })
	@ResponseBody
	public BootStrapDataGrid getUserLogInforListData(UserLogInfor userLogInfor,HttpServletRequest request,HttpServletResponse response) {

		BootStrapDataGrid dataGrid = new BootStrapDataGrid();

		dataGrid=userLogInforService.getUserLogInforListData(userLogInfor,request);

		return dataGrid;
	}

}
