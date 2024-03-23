import {
	request
} from "../../request/request"
var app = getApp();
Page({

	data: {
		cart: app.globalData.cart,
		goods: [],
		moneytopay: [],
		//菜单数据
		goodsList: [],
		howCartDetail: false,
		classifySeleted: 'c1',
		showIntroduceStatus: false,
		showId: 1,
		good: {},
	},
	Cates: [],
	Pds: [],
	onLoad: function (options) {
		//  1 获取本地存储中的数据  (小程序中也是存在本地存储 技术)
		const Cates = wx.getStorageSync("cates");
		const Pds = wx.getStorageSync("pds");
		// 2 判断
		if (!Cates) {
			// 不存在  发送请求获取数据
			this.getCates();
		} else {
			// 有旧的数据 定义过期时间  10s 改成 5分钟
			if (Date.now() - Cates.time > 1000 * 10) {
				// 重新发送请求
				this.getCates();
			} else {
				// 可以使用旧的数据
				this.Cates = Cates.data;
				this.Pds = Pds.data;
				let goodsList = this.Cates;
				let goods = this.Pds;
				this.setData({
					goodsList,
					goods
				})
			}
		}
	},

	//自定义的事件处理函数或功能函数：获取类别数据的函数
	async getCates() {
		// 获取分类数据
		const res = await request({
			url: "/api/goods.action"
		}); //使用await关键字来表示等待异步请求返回{
		this.Cates = res;
		const ps = await request({
			url: "/api/products.action"
		});
		this.Pds = ps;
		// 把接口的数据存入到本地存储中
		wx.setStorageSync("cates", {
			time: Date.now(),
			data: this.Cates
		});
		wx.setStorageSync("pds", {
			time: Date.now(),
			data: this.Pds
		});
		// 构造左侧的大菜单数据：对数组中的每个项目提取大类类别名称
		let goodsList = this.Cates;
		console.log("goodsList:");
		console.log(goodsList);
		// 构造右侧的商品数据
		let goods = this.Pds;
		console.log("goods:");
		console.log(goods);
		this.setData({
			goodsList,
			goods
		});
	},

	onShow: function () {
		this.setData({
			tableprt: app.globalData.tabletransfer,
			peop: app.globalData.peopnumber,
			cart: app.globalData.cart
		});

	},
	tapAddCart: function (e) {
		app.globalData.moneytopay += e.target.dataset.id.price;

		console.log(e.target.dataset.id.price);
		console.log("e.target.dataset.id:");
		console.log(e.target.dataset);
		this.addCart(e.target.dataset.id.id);

	},
	tapAddCart1: function (e) {
		app.globalData.moneytopay += e.target.dataset.id.price;

		console.log(e.target.dataset.id.price);
		console.log("e.target.dataset.id:");
		console.log(e.target.dataset.id);
		this.addCart(e.target.dataset.id);

	},
	tapReduceCart: function (e) {
		app.globalData.moneytopay -= e.target.dataset.id.price;
		console.log(e.target.dataset.id.price);
		console.log("e.target.dataset.id:");
		console.log(e.target.dataset.id);
		this.reduceCart(e.target.dataset.id);
	},
	tapReduceCart1: function (e) {
		app.globalData.moneytopay -= e.target.dataset.id.price;
		console.log(e.target.dataset.id.price);
		console.log("e.target.dataset.id:");
		console.log(e.target.dataset.id);
		this.reduceCart(e.target.dataset.id.id);
	},
	addCart: function (id) {
		console.log("id:" + id);
		var num = app.globalData.cart.list[id] || 0;
		console.log(num);
		app.globalData.cart.list[id] = num + 1;

		console.log("app.globalData.cart.list[id]:" + app.globalData.cart.list[id]);
		this.countCart();

	},
	reduceCart: function (id) {
		var num = app.globalData.cart.list[id] || 0;
		if (num <= 1) {
			delete app.globalData.cart.list[id];
		} else {
			app.globalData.cart.list[id] = num - 1;
		}
		this.countCart();

	},
	countCart: function () {
		var count = 0,
			total = 0;
		for (var id in app.globalData.cart.list) {
			console.log(this.data);
			for (var i of this.data.goods) {
				if (i.id == id) {
					console.log("i的id:" + i.id);
					var goods = i;
				}
			}
			console.log("goods:");
			console.log(goods);
			app.globalData.cart.pname[id] = goods.name;
			app.globalData.cart.price[id] = goods.price;
			console.log("app.globalData.cart.pname")
			console.log(app.globalData.cart.pname);
			count += app.globalData.cart.list[id];
			total += goods.price * app.globalData.cart.list[id];
		}
		app.globalData.cart.count = count;
		app.globalData.cart.total = total;
		this.setData({
			cart: app.globalData.cart
		});
	},
	follow: function () {
		this.setData({
			followed: !this.data.followed
		});
	},
	onGoodsScroll: function (e) {
		console.log("scroll",e.detail)
		console.log("classifySeleted",this.data.classifySeleted)
		// console.log("classifyViewed",classifyViewed)
		if (e.detail.scrollTop > 10 && !this.data.scrollDown) {
			this.setData({
				scrollDown: true
			});
		} else if (e.detail.scrollTop < 10 && this.data.scrollDown) {
			this.setData({
				scrollDown: false
			});
		}
		var scale = e.detail.scrollWidth / 570,
			scrollTop = e.detail.scrollTop / scale,
			h = 0,
			classifySeleted,
			len = this.data.goodsList.length;

		this.data.goodsList.forEach(function (goodsList, i) {
			var _h = 70 + goodsList.products.length * (46 * 3 + 20 * 2);
			if (scrollTop >= h - 100 / scale) {
				classifySeleted = 'c'+goodsList.id;
			}
			h += _h;
		});

		this.setData({
			classifySeleted: classifySeleted
		});
	},
	tapGoodsList: function (e) {
		var id = "c" + e.target.dataset.id;
		console.log("左侧菜单id：" + id);
		this.setData({
			classifyViewed: id,
		});
		var self = this;
		setTimeout(function () {
			self.setData({
				classifySeleted: id
			});
		}, 100);
	},
	showCartDetail: function () {
		this.setData({
			showCartDetail: !this.data.showCartDetail
		});
	},
	hideCartDetail: function () {
		this.setData({
			showCartDetail: false
		});
	},
	submit: function () {
		var user=wx.getStorageSync('user');
		var phone=user.telephone;
		if (!phone) {
			console.log("没绑定呀大哥")
			wx.showModal({
				title: "提示",
				content: "大哥，您还未绑定手机号码，请先到“我的-去绑定”绑定手机号码",
				success: function (res) {
					if (res.confirm) {
						console.log("用户点击确定，跳转到手机绑定界面")
						wx.navigateTo({
							url: '../../pages/binding-phone/binding-phone',
						})
					}else if (res.cancel){
						console.log("用户点击取消")
					}
				}
			})
		} else {
			console.log("有opinid，已注册")
			wx.navigateTo({
				url: '../order/order'
			})
		}
	},

	taptableid: function () {
		wx.navigateTo({
			url: '../tableid/tableid',
		})
	},

	introduce: function (event) {
		var showId = event.currentTarget.dataset.id;
		for (var i of this.data.goods) {
			console.log(i)
			if (i.id == showId) {
				console.log("i的id:" + i.id);
				var good = i;
			}
		}
		this.setData({
			showId: showId,
			showIntroduceStatus: true,
			good: good,
		});
		console.log("showId:");
		console.log(showId);

		console.log(good)
	},
	close: function () {
		this.setData({
			showIntroduceStatus: false
		})
	},
});