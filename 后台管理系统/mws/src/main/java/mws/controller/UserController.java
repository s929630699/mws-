package mws.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import mws.pojo.Page;
import mws.pojo.Product;
import mws.pojo.RequestResult;
import mws.pojo.User;
import mws.pojo.UserRole;
import mws.service.UserService;

@CrossOrigin(origins = "*") //跨域
@Controller
@RequestMapping("user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RequestResult<User> result;
	
	Page page=new Page();
	
	/**
	 * API接口
	 */
	@RequestMapping(value = "/list.action", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public RequestResult<User> list(){
		List<User> users = this.userService.getUserListWhere(null);
		result.setCode(666);
		result.setMessage("成功查询到用户！");
		result.setSuccess(true);
		result.setData(users);
		return result;
	}
	
	/**
	 * MVC方法
	 */
	@RequestMapping(value = "/list2.action", method = { RequestMethod.POST, RequestMethod.GET })
	public String list2(User user, Model model,HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String select=request.getParameter("select");
		String value=request.getParameter("value");
		System.out.println("---------------select:"+select);
		
		List<User> list=new ArrayList<User>();
		if(select==null){
			select="0";
		}
		if(select.equals("0")){
			list = this.userService.getUserListWhere(null);
		}else if (select.equals("1")){
			list=this.userService.getUserByUsername(value);
		}else if (select.equals("2")){
			list=this.userService.getUserByTelephone(value);
		}
		System.out.println("---------------list:"+list);
		
		System.out.println(page.getCurrentPage());
	        page.setCurrentPage(1);
		System.out.println(page.getCurrentPage());
			page.setPageSize(10);
			page.setStar((page.getCurrentPage()-1)*page.getPageSize());
			int count=list.size();
			page.setTotalPage(count%10==0?count/10:count/10+1);
			page.setDataList(list.subList(page.getStar(), count-page.getStar()>page.getPageSize()?page.getStar()+page.getPageSize():count));
		model.addAttribute("users", page.getDataList());
		model.addAttribute("paging", page);
		model.addAttribute("user", user);
		return "userTable";
	}
	
	/**
	 * 添加产品
	 */
	@RequestMapping(value = "/insert.action", method = { RequestMethod.POST, RequestMethod.GET })
	public String insertUser(User user,Model model) throws ParseException {
		System.out.println("会员信息："+user);
		String registTime="";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date =new Date();
		registTime=sdf.format(date);
		user.setRegistTime(registTime);
		int state=this.userService.insertUser(user);
		User u=this.userService.getOneUserByUsername(user.getUsername());
		System.out.println("会员信息："+u);
		int userId=u.getId();
		int roleId=0;
		if (user.getRole().equals("管理员")){
			roleId=1;
		}else if(user.getRole().equals("普通会员")){
			roleId=2;
		}
		UserRole userRole=new UserRole();
		userRole.setUserId(userId);
		userRole.setRoleId(roleId);
		System.out.println("UserRole"+userRole);
		this.userService.insertUserRole(userRole);
		if(state>0){
			model.addAttribute("url", "list2.action");
			model.addAttribute("infomation", "添加会员成功！");
			model.addAttribute("second", 2);
		}else{
			model.addAttribute("url", "list2.action");
			model.addAttribute("infomation", "添加会员失败！！！");
			model.addAttribute("second", 2);
		}
		
		return "tip";
	}
	
	/**
	 * 修改产品
	 */
	@RequestMapping(value = "/update.action", method = {RequestMethod.GET })
	public String jumpUpdate(int id,Model model) {
		User user=this.userService.getUserById(id);
		System.out.println("user:"+user);
		model.addAttribute("user", user);
		return "updateUser";
	}
	
	@RequestMapping(value = "/update.action", method = {RequestMethod.POST })
	public String updateUser(User user,Model model) {
		System.out.println("会员信息："+user);
		int state=this.userService.updateUser(user);
		if(state>0){
			model.addAttribute("url", "list2.action");
			model.addAttribute("infomation", "修改会员信息成功！");
			model.addAttribute("second", 2);
		}else{
			model.addAttribute("url", "list2.action");
			model.addAttribute("infomation", "修改会员信息失败！！！");
			model.addAttribute("second", 2);
		}
		return "tip";
	}
	
	/**
	 * 删除产品
	 */
	@RequestMapping(value = "/delete.action", method = { RequestMethod.POST, RequestMethod.GET })
	public String deleteUser(int id,Model model) {
		int state=this.userService.deleteUser(id);
		this.userService.deleteUserRole(id);
		if(state>0){
			model.addAttribute("url", "list2.action");
			model.addAttribute("infomation", "删除会员成功！");
			model.addAttribute("second", 2);
		}else{
			model.addAttribute("url", "list2.action");
			model.addAttribute("infomation", "删除会员失败！！！");
			model.addAttribute("second", 2);
		}
		
		return "tip";
	}
	
	@RequestMapping(value = "/upPage.action", method = { RequestMethod.POST, RequestMethod.GET })
	public String upPage(User user, Model model) {
		List<User> list = this.userService.getUserListWhere(null);
		System.out.println(page.getCurrentPage());
		if (page.getCurrentPage() == 0){
	        page.setCurrentPage(1);
	    }else if(page.getCurrentPage()>1){
	    	page.setCurrentPage(page.getCurrentPage()-1);
	    }
		System.out.println(page.getCurrentPage());
			page.setPageSize(10);
			page.setStar((page.getCurrentPage()-1)*page.getPageSize());
			int count=list.size();
			page.setTotalPage(count%10==0?count/10:count/10+1);
			page.setDataList(list.subList(page.getStar(), count-page.getStar()>page.getPageSize()?page.getStar()+page.getPageSize():count));
			model.addAttribute("users", page.getDataList());
			model.addAttribute("paging", page);
			model.addAttribute("user", user);
			return "userTable";
	}
	
	@RequestMapping(value = "/downPage.action", method = { RequestMethod.POST, RequestMethod.GET })
	public String downPage(User user, Model model) {
		List<User> list = this.userService.getUserListWhere(null);
		System.out.println(page.getCurrentPage());
		if (page.getCurrentPage() == 0){
	        page.setCurrentPage(1);
	    }else if(page.getCurrentPage()<page.getTotalPage()){
	    	page.setCurrentPage(page.getCurrentPage()+1);
	    }
		System.out.println(page.getCurrentPage());
			page.setPageSize(10);
			page.setStar((page.getCurrentPage()-1)*page.getPageSize());
			int count=list.size();
			page.setTotalPage(count%10==0?count/10:count/10+1);
			page.setDataList(list.subList(page.getStar(), count-page.getStar()>page.getPageSize()?page.getStar()+page.getPageSize():count));
			model.addAttribute("users", page.getDataList());
			model.addAttribute("paging", page);
			model.addAttribute("user", user);
			return "userTable";
	}
	
	@RequestMapping(value = "/homePage.action", method = { RequestMethod.POST, RequestMethod.GET })
	public String homePage(User user, Model model) {
		List<User> list = this.userService.getUserListWhere(null);
	        page.setCurrentPage(1);
			page.setPageSize(10);
			page.setStar(0);
			int count=list.size();
			page.setTotalPage(count%10==0?count/10:count/10+1);
			page.setDataList(list.subList(page.getStar(), count-page.getStar()>page.getPageSize()?page.getStar()+page.getPageSize():count));
			model.addAttribute("users", page.getDataList());
			model.addAttribute("paging", page);
			model.addAttribute("user", user);
			return "userTable";
	}
	
	@RequestMapping(value = "/lastPage.action", method = { RequestMethod.POST, RequestMethod.GET })
	public String lastPage(User user, Model model) {
		List<User> list = this.userService.getUserListWhere(null);
			page.setPageSize(10);
			page.setStar((page.getTotalPage()-1)*page.getPageSize());
			int count=list.size();
			page.setTotalPage(count%10==0?count/10:count/10+1);
			System.out.println("totalPage:"+page.getTotalPage());
			page.setCurrentPage(page.getTotalPage());
			page.setDataList(list.subList(page.getStar(), count-page.getStar()>page.getPageSize()?page.getStar()+page.getPageSize():count));
			model.addAttribute("users", page.getDataList());
			model.addAttribute("paging", page);
			model.addAttribute("user", user);
			return "userTable";
	}
	
	@RequestMapping(value = "/jumpPage.action", method = { RequestMethod.POST, RequestMethod.GET })
	public String jumpPage(User user, Model model,HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		List<User> list = this.userService.getUserListWhere(null);
		System.out.println("pageNumber:"+request.getParameter("pageNumber"));
        int currentPage=1;
		
		if(request.getParameter("pageNumber")==null||request.getParameter("pageNumber")==""){
			currentPage=Integer.parseInt(request.getParameter("currentPage"));
		}else{
			currentPage=Integer.parseInt(request.getParameter("pageNumber"));
		}
		System.out.println("currentPage:"+currentPage);
			page.setCurrentPage(currentPage);
			page.setPageSize(10);
			page.setStar((page.getCurrentPage()-1)*page.getPageSize());
			int count=list.size();
			page.setTotalPage(count%10==0?count/10:count/10+1);
			page.setDataList(list.subList(page.getStar(), count-page.getStar()>page.getPageSize()?page.getStar()+page.getPageSize():count));
			model.addAttribute("users", page.getDataList());
			model.addAttribute("paging", page);
			model.addAttribute("user", user);
		return "userTable";
	}

}
