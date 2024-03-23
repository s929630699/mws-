// 0 引入 用来发送请求的 方法 一定要把路径补全
import {
  request
} from "../../request/request.js";

import {
  login
} from "../../utils/asyncWx.js";


const app = getApp();
Page({
  data: {
    // 轮播图数组
    swiperList: [],
    // 导航 数组    
    catesList: [],
    // 活动 数组
    activities: [],
  },
  userInfo: {},
  // 页面开始加载 就会触发
  onLoad: function (options) {
    this.getSwiperList();
    this.getCateList();
    this.getActivities();
    const UserInfo = wx.getStorageSync('userInfo');
    this.setData({
      userInfo: UserInfo
    })
  },

  onShow: function () {
    const UserInfo = wx.getStorageSync('userInfo');
    var time = new Date().getHours();
    console.log("当前时间:" + time + "点")
    if (0 < time < 6 || 18 < time < 21) {
      this.setData({
        userInfo: UserInfo,
        welcome: "晚上好，"
      })
    } else if (6 < time < 11) {
      this.setData({
        userInfo: UserInfo,
        welcome: "早上好，"
      })
    } else if (11 < time < 13) {
      this.setData({
        userInfo: UserInfo,
        welcome: "中午好，"
      })
    } else if (13 < time < 18) {
      this.setData({
        userInfo: UserInfo,
        welcome: "下午好，"
      })
    }

  },
  // 获取轮播图数据
  getSwiperList() {
    request({
        url: "/api/banner.action"
      })
      .then(result => {
        this.setData({
          swiperList: result,
        });
        console.log("banner:")
        console.log(result)
      })
  },
  // 获取 分类导航数据
  getCateList() {
    request({
        url: "/api/catesitem.action"
      })
      .then(result => {
        this.setData({
          catesList: result
        });
        console.log("catesitem:")
        console.log(result)
      })
  },
  //获取 活动图数据
  getActivities() {
    request({
        url: "/api/activities.action"
      })
      .then(result => {
        this.setData({
          activities: result
        });
        console.log("activities:")
        console.log(result)
      })
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    this.onLoad();
  },

  biand1:function(){
wx.switchTab({
  url:"../../pages/goods/goods?classifySeleted=c1",
})
  },

  biand2(){
    wx.switchTab({
      url:"../../pages/goods/goods?classifySeleted=c2",
    })
  },

  biand3(){
    wx.switchTab({
      url:"../../pages/goods/goods?classifySeleted=c3",
    })
  },

  biand4(){
    wx.switchTab({
      url:"../../pages/goods/goods?classifySeleted=c4",
    })
  },

  

})