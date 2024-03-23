package mws.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.security.sasl.AuthenticationException;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import mws.pojo.Activities;
import mws.pojo.Banner;
import mws.pojo.Category;
import mws.pojo.Catesitem;
import mws.pojo.JsonResult;
import mws.pojo.Order;
import mws.pojo.OrderItem;
import mws.pojo.Page;
import mws.pojo.Product;
import mws.pojo.RequestResult;
import mws.pojo.User;
import mws.pojo.UserAddr;
import mws.service.ApiService;
import mws.service.UserService;
import net.sf.json.JSONObject;
import utils.JwtTokenUtil;
import utils.WXUtil;

@CrossOrigin(origins = "*") // 跨域
@Controller
@RequestMapping("api")
public class ApiController {
	@Autowired
	private ApiService apiService;

	@Autowired
	private UserService userService;

	@Autowired
	private RequestResult<Banner> bannerResult;

	@Autowired
	private RequestResult<Catesitem> catesitemResult;

	@Autowired
	private RequestResult<Category> categoryResult;

	@Autowired
	private RequestResult<Product> productsResult;

	@Autowired
	private RequestResult<Activities> ActivitiesResult;

	@Autowired
	private RequestResult<UserAddr> userAddrResult;
	
	@Autowired
	private RequestResult<String> AddOrderResult;
	
	@Autowired
	private RequestResult<Order> orderResult;
	
	@Autowired
	private RequestResult<OrderItem> orderItemResult;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Value("${jwt.header}")
	private String tokenHeader;

	private String appid = "wx424f3a966702ad65";

	private String secretKey = "9e9bd261d684a9299aa8f9c8715ac439";

	Page page = new Page();
	
	// 前台调用该接口进行微信用户凭证校验
		// 1.接收前台并生成token返回给用户
		@RequestMapping(value = "/wxlogin.action", method = { RequestMethod.POST })
		@ResponseBody
		public JsonResult wxlogin(@RequestBody Map<String, Object> params) throws AuthenticationException {
			JsonResult result = new JsonResult();
			String code = params.get("code").toString();
			System.out.println("code" + code);
			String nickName = params.get("nickName").toString();
			System.out.println("nickName" + nickName);
			String telephone = params.get("telephone").toString();
			System.out.println("telephone" + telephone);
			String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + this.appid + "&secret=" + this.secretKey
					+ "&js_code=" + code + "&grant_type=authorization_code";
			System.out.println("url" + url);
			String results = WXUtil.sendGetReq(url);// 发送http请求
			System.out.println("results" + results);
			String openid = "";
			JSONObject jsonObj = JSONObject.fromObject(results); // 把字符串转成JSON对象
			System.out.println("jsonObj" + jsonObj);
			if (jsonObj.has("openid")) {
				openid = jsonObj.getString("openid");
				System.out.println("openid" + openid);
				User userInfo = this.userService.getUserByOpenid(openid);
				System.out.println("userInfo" + userInfo);
				if (userInfo == null) {
					userInfo = new User();
					userInfo.setUsername(nickName);
					userInfo.setPassword(openid);
					userInfo.setTelephone(telephone);
					userInfo.setWx_openid(openid);
					userInfo.setRole("普通用户");
					// 用户名和密码正确情况下，调用工具类进行token生成
					// final String token = JwtTokenUtil.generateToken(userInfo);
					final String token = jwtTokenUtil.generateToken(userInfo);
					System.out.println("token" + token);
					// 赋值到userInfo属性
					userInfo.setToken(token);
					User addedUser = apiService.register(userInfo);
				}

				result.setResult(true);
				result.setSuccessMsg("ok");
				result.setResultData(userInfo);

			} else {
				System.out.println("result" + result);
				result.setResult(false);
				result.setErrorMsg("get openid  failed");
			}
			return result;
		}
		
		@RequestMapping(value = "/getOpenid.action", method = { RequestMethod.POST })
		@ResponseBody
		public JsonResult getToken(@RequestBody Map<String, Object> params) throws AuthenticationException {
			JsonResult result = new JsonResult();
			String code = params.get("code").toString();
			System.out.println("code" + code);
			String nickName = params.get("nickName").toString();
			System.out.println("nickName" + nickName);
			String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + this.appid + "&secret=" + this.secretKey
					+ "&js_code=" + code + "&grant_type=authorization_code";
			System.out.println("url" + url);
			String results = WXUtil.sendGetReq(url);// 发送http请求
			System.out.println("results" + results);
			String openid = "";
			JSONObject jsonObj = JSONObject.fromObject(results); // 把字符串转成JSON对象
			System.out.println("jsonObj" + jsonObj);
			if (jsonObj.has("openid")) {
				openid = jsonObj.getString("openid");
				System.out.println("openid" + openid);
				User userInfo = this.userService.getUserByOpenid(openid);
				System.out.println("userInfo" + userInfo);
				if (userInfo == null) {
					userInfo = new User();
					userInfo.setUsername(nickName);
					userInfo.setPassword(openid);
					userInfo.setWx_openid(openid);
					userInfo.setRole("普通用户");
					// 用户名和密码正确情况下，调用工具类进行token生成
					// final String token = JwtTokenUtil.generateToken(userInfo);
					final String token = jwtTokenUtil.generateToken(userInfo);
					System.out.println("token" + token);
					// 赋值到userInfo属性
					userInfo.setToken(token);
				}
				result.setResult(true);
				result.setSuccessMsg("ok");
				result.setResultData(userInfo);

			} else {
				System.out.println("result" + result);
				result.setResult(false);
				result.setErrorMsg("get openid  failed");
			}
			return result;
		}

	/**
	 * 轮播图API接口
	 */
	@RequestMapping(value = "/banner.action", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public RequestResult<Banner> bannerList() {
		List<Banner> banners = this.apiService.getBannerListWhere(null);
		bannerResult.setCode(666);
		bannerResult.setMessage("成功查询到轮播图！");
		bannerResult.setSuccess(true);
		bannerResult.setData(banners);
		return bannerResult;
	}

	/**
	 * 分类导航API接口
	 */
	@RequestMapping(value = "/catesitem.action", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public RequestResult<Catesitem> catesitemList() {
		List<Catesitem> catesitems = this.apiService.getCatesitemListWhere(null);
		catesitemResult.setCode(666);
		catesitemResult.setMessage("成功查询到分类导航信息！");
		catesitemResult.setSuccess(true);
		catesitemResult.setData(catesitems);
		return catesitemResult;
	}

	/**
	 * 产品API接口
	 */
	@RequestMapping(value = "/goods.action", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public RequestResult<Category> goodsList() {
		List<Category> category = this.apiService.getCategoryList(null);
		System.out.println("分类：" + category);
		List<Product> product = this.apiService.getProductList(null);
		System.out.println("所有产品：" + product);
		for (int i = 0; i < category.size(); i++) {
			List<Product> pd = new ArrayList<Product>();
			for (int a = 0; a < product.size(); a++) {
				if (category.get(i).getId().equals(product.get(a).getCategoryId())) {
					pd.add(product.get(a));
				}
			}
			System.out.println("pd:" + pd);
			category.get(i).setProducts(pd);
		}
		System.out.println("最终分类信息：" + category);
		categoryResult.setCode(666);
		categoryResult.setMessage("成功查询到分类信息！");
		categoryResult.setSuccess(true);
		categoryResult.setData(category);
		return categoryResult;
	}

	/**
	 * 所有产品API接口
	 */
	@RequestMapping(value = "/products.action", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public RequestResult<Product> productsList() {
		List<Product> products = this.apiService.getProductList(null);
		System.out.println("所有产品：" + products);
		productsResult.setCode(666);
		productsResult.setMessage("成功查询到产品信息！");
		productsResult.setSuccess(true);
		productsResult.setData(products);
		return productsResult;
	}

	/**
	 * 活动API接口
	 */
	@RequestMapping(value = "/activities.action", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public RequestResult<Activities> activitiesList() {
		List<Activities> Activities = this.apiService.getActivitiesList(null);
		System.out.println("所有活动：" + Activities);
		ActivitiesResult.setCode(666);
		ActivitiesResult.setMessage("成功查询到活动信息！");
		ActivitiesResult.setSuccess(true);
		ActivitiesResult.setData(Activities);
		return ActivitiesResult;
	}

	/**
	 * 收货地址API接口
	 */
	@RequestMapping(value = "/address.action", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public RequestResult<UserAddr> addressList(@RequestBody Map<String, Object> params) {
		int userId=Integer.parseInt(params.get("userId").toString());
		List<UserAddr> userAddr = this.apiService.getUserAddrList(userId);
		System.out.println("id为"+userId+"的所有收货地址：" + userAddr);
		userAddrResult.setCode(666);
		userAddrResult.setMessage("成功查询到收货地址信息！");
		userAddrResult.setSuccess(true);
		userAddrResult.setData(userAddr);
		return userAddrResult;
	}
	
	/**
	 * 修改收货地址默认状态API接口
	 */
	@RequestMapping(value = "/addrDefault.action", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public RequestResult<UserAddr> addrDefault(@RequestBody Map<String, Object> params) {
		int userId=Integer.parseInt(params.get("userId").toString());
		int addrId=Integer.parseInt(params.get("addrId").toString());
		int all0=this.apiService.addrDefAll0(userId);
		if(all0>0){
			int result=this.apiService.updateAddrDef(addrId);
		}
		List<UserAddr> userAddr = this.apiService.getUserAddrList(userId);
		System.out.println("id为"+userId+"的所有收货地址：" + userAddr);
		userAddrResult.setCode(666);
		userAddrResult.setMessage("成功查询到收货地址信息！");
		userAddrResult.setSuccess(true);
		userAddrResult.setData(userAddr);
		return userAddrResult;
	}
	
	/**
	 * 删除收货地址API接口
	 */
	@RequestMapping(value = "/deleteAddr.action", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public RequestResult<UserAddr> deleteAddr(@RequestBody Map<String, Object> params) {
		int addrId=Integer.parseInt(params.get("addrId").toString());
		int delAddr=this.apiService.deleteAddr(addrId);
		if (delAddr>0){
			System.out.println("成功删除收货地址");
		}
		userAddrResult.setCode(666);
		userAddrResult.setMessage("成功删除收货地址！");
		userAddrResult.setSuccess(true);
		userAddrResult.setData(null);
		return userAddrResult;
	}
	
	/**
	 * 新增收货地址API接口
	 */
	@RequestMapping(value = "/insertAddr.action", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public RequestResult<UserAddr> insertAddr(@RequestBody Map<String, Object> params) {
		int userId=Integer.parseInt(params.get("userId").toString());
		String receiver=params.get("receiver").toString();
		String mobile=params.get("mobile").toString();
		String address=params.get("address").toString();
		List<UserAddr> userAddrList = this.apiService.getUserAddrList(userId);
		int defaultState=0;
		if(userAddrList.size()==0){
			defaultState=1;
		}else{
			defaultState=0;
		}
		
		UserAddr userAddr=new UserAddr();
		userAddr.setUserId(userId);
		userAddr.setReceiver(receiver);
		userAddr.setMobile(mobile);
		userAddr.setAddress(address);
		userAddr.setDefaultState(defaultState);
		
		int insertAddr=this.apiService.insertAddr(userAddr);
		if (insertAddr>0){
			System.out.println("成功添加收货地址");
		}
		
		userAddrResult.setCode(666);
		userAddrResult.setMessage("成功添加收货地址！");
		userAddrResult.setSuccess(true);
		userAddrResult.setData(null);
		return userAddrResult;
	}
	
	/**
	 * 修改收货地址API接口
	 */
	@RequestMapping(value = "/updateAddr.action", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public RequestResult<UserAddr> updateAddr(@RequestBody Map<String, Object> params) {
		int id=Integer.parseInt(params.get("addrId").toString());
		String receiver=params.get("receiver").toString();
		String mobile=params.get("mobile").toString();
		String address=params.get("address").toString();
		
		UserAddr userAddr=new UserAddr();
		userAddr.setId(id);
		userAddr.setReceiver(receiver);
		userAddr.setMobile(mobile);
		userAddr.setAddress(address);
		
		int updateAddr=this.apiService.updateAddr(userAddr);
		if (updateAddr>0){
			System.out.println("成功修改收货地址");
		}
		
		userAddrResult.setCode(666);
		userAddrResult.setMessage("成功修改收货地址信息！");
		userAddrResult.setSuccess(true);
		userAddrResult.setData(null);
		return userAddrResult;
	}
	
	/**
	 *  新增订单API接口
	 */
	@RequestMapping(value = "/insertOrder.action", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public RequestResult<String> insertOrder(@RequestBody Map<String, Object> params) {
		String oid=RandomStringUtils.randomAlphanumeric(10);
		double money= Double.parseDouble(params.get("money").toString());
		String address=params.get("address").toString();
		String receiver=params.get("receiver").toString();
		String paystate=params.get("paystate").toString();
		String mobile=params.get("mobile").toString();
		int userId=Integer.parseInt(params.get("userId").toString());
		String remarks=params.get("remarks").toString();
		String otime="";
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		otime=sdf.format(date);
		
		Order order=new Order();
		order.setId(oid);
		order.setMoney(money);
		order.setReceiverAddress(address);
		order.setReceiverName(receiver);
		order.setReceiverPhone(mobile);
		order.setPaystate(paystate);
		order.setOrdertime(otime);
		order.setUser_id(userId);
		order.setOrderState("1");
		order.setRemarks(remarks);
		
		int result=this.apiService.insertOrder(order);
		
		if (result>0){
			System.out.println("成功添加订单");
		}
		List<String> od = new ArrayList<String>();
		od.add(oid);
		AddOrderResult.setCode(666);
		AddOrderResult.setMessage("成功添加订单！");
		AddOrderResult.setSuccess(true);
		AddOrderResult.setData(od);
		return AddOrderResult;
	}
	
	/**
	 *  新增订单详情API接口
	 */
	@RequestMapping(value = "/insertOrderitem.action", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public RequestResult<String> insertOrderitem(@RequestBody Map<String, Object> params) {
		String oid=params.get("oid").toString();
		int productId=Integer.parseInt(params.get("productId").toString());
		String productName=params.get("productName").toString();
		int buyNum=Integer.parseInt(params.get("buyNum").toString());
		float buyPrice=Float.parseFloat(params.get("buyPrice").toString());
		
		OrderItem orderitem=new OrderItem();
		orderitem.setOrderId(oid);
		orderitem.setProductId(productId);
		orderitem.setProductName(productName);
		orderitem.setBuyNum(buyNum);
		orderitem.setBuyPrice(buyPrice);
		
		int result=this.apiService.insertOrderitem(orderitem);
		
		if (result>0){
			System.out.println("成功添加订单详情");
		}
		
		AddOrderResult.setCode(666);
		AddOrderResult.setMessage("成功添加订单详情！");
		AddOrderResult.setSuccess(true);
		AddOrderResult.setData(null);
		return AddOrderResult;
	}
	
	/**
	 *  查询全部订单API接口
	 */
	@RequestMapping(value = "/allOrderList.action", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public RequestResult<Order> allOrderList(@RequestBody Map<String, Object> params) {
		int userId=Integer.parseInt(params.get("userId").toString());
		
		List<Order> result=this.apiService.selectOrderByUserId(userId);
		
		orderResult.setCode(666);
		orderResult.setMessage("成功查询订单！");
		orderResult.setSuccess(true);
		orderResult.setData(result);
		return orderResult;
	}
	
	/**
	 *  查询未付款订单API接口
	 */
	@RequestMapping(value = "/noPayOrderList.action", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public RequestResult<Order> noPayOrderList(@RequestBody Map<String, Object> params) {
		int userId=Integer.parseInt(params.get("userId").toString());
		String paystate=params.get("paystate").toString();
		
		Order order=new Order();
		order.setUser_id(userId);
		order.setPaystate(paystate);
		
		List<Order> result=this.apiService.selectOrderByPaystate(order);
		
		orderResult.setCode(666);
		orderResult.setMessage("成功查询订单！");
		orderResult.setSuccess(true);
		orderResult.setData(result);
		return orderResult;
	}
	
	/**
	 *  查询未发货、配送中、已到达订单API接口
	 */
	@RequestMapping(value = "/OrderListByOrderState.action", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public RequestResult<Order> OrderListByOrderState(@RequestBody Map<String, Object> params) {
		int userId=Integer.parseInt(params.get("userId").toString());
		String orderState=params.get("orderState").toString();
		
		Order order=new Order();
		order.setUser_id(userId);
		order.setOrderState(orderState);
		
		List<Order> result=this.apiService.selectOrderByOrderState(order);
		
		orderResult.setCode(666);
		orderResult.setMessage("成功查询订单！");
		orderResult.setSuccess(true);
		orderResult.setData(result);
		return orderResult;
	}
	
	/**
	 *  查询订单详情API接口
	 */
	@RequestMapping(value = "/orderItemList.action", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public RequestResult<OrderItem> orderItemList(@RequestBody Map<String, Object> params) {
		String orderId=params.get("orderId").toString();
		
		List<OrderItem> result=this.apiService.selectOrderItemByOrderId(orderId);
		
		orderItemResult.setCode(666);
		orderItemResult.setMessage("成功查询订单详情！");
		orderItemResult.setSuccess(true);
		orderItemResult.setData(result);
		return orderItemResult;
	}
	
	/**
	 *  订单付款API接口
	 */
	@RequestMapping(value = "/orderPay.action", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public RequestResult<Order> orderPay(@RequestBody Map<String, Object> params) {
		String orderId=params.get("orderId").toString();
		int result = this.apiService.orderPay(orderId);
		if(result>0){
			System.out.println("成功付款");
		}
		orderResult.setCode(666);
		orderResult.setMessage("成功付款订单！");
		orderResult.setSuccess(true);
		orderResult.setData(null);
		return orderResult;
	}
	
	/**
	 *  取消订单API接口
	 */
	@RequestMapping(value = "/orderCancel.action", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public RequestResult<Order> orderCancel(@RequestBody Map<String, Object> params) {
		String orderId=params.get("orderId").toString();
		int result=this.apiService.orderCancel(orderId);
		if(result>0){
			System.out.println("成功取消订单");
		}
		orderResult.setCode(666);
		orderResult.setMessage("成功取消订单！");
		orderResult.setSuccess(true);
		orderResult.setData(null);
		return orderResult;
	}
	
	/**
	 *  订单确认收货API接口
	 */
	@RequestMapping(value = "/orderFinish.action", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public RequestResult<Order> orderFinish(@RequestBody Map<String, Object> params) {
		String orderId=params.get("orderId").toString();
		int result=this.apiService.orderFinish(orderId);
		if(result>0){
			System.out.println("成功订单完成");
		}
		orderResult.setCode(666);
		orderResult.setMessage("成功订单完成！");
		orderResult.setSuccess(true);
		orderResult.setData(null);
		return orderResult;
	}
	
	/**
	 *  删除订单API接口
	 */
	@RequestMapping(value = "/orderRemove.action", method = { RequestMethod.POST, RequestMethod.GET })
	@ResponseBody
	public RequestResult<Order> orderRemove(@RequestBody Map<String, Object> params) {
		String orderId=params.get("orderId").toString();
		int result=this.apiService.orderRemove(orderId);
		if(result>0){
			System.out.println("成功删除订单");
		}
		orderResult.setCode(666);
		orderResult.setMessage("成功删除订单！");
		orderResult.setSuccess(true);
		orderResult.setData(null);
		return orderResult;
	}
	
	
	

	

}
