// pages/user/user.js
import {
  request
} from "../../request/request2";
import {
  login
} from "../../utils/asyncWx.js";
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
    orderAmount: '',
    sts: '',
    collectionCount: 0,
    isShow: true,
    userInfo: {},
    hasUserInfo: false,
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    const UserInfo = wx.getStorageSync('userInfo');
    if (UserInfo) {
      console.log("userInfo:");
      console.log(UserInfo)
      this.setData({
        hasUserInfo: true,
        userInfo: UserInfo
      })
    } else {
      this.setData({
        hasUserInfo: false
      })
    };
  },
  getUserProfile(e) {
    // 推荐使用wx.getUserProfile获取用户信息，开发者每次通过该接口获取用户个人信息均需用户确认
    // 开发者妥善保管用户快速填写的头像昵称，避免重复弹窗
    wx.getUserProfile({
      desc: '用于完善会员资料', // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
      success: (res) => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
        console.log("userInfo:"),
          console.log(res.userInfo),
          wx.setStorageSync('userInfo', res.userInfo);
      }
    });
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {},

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    if (!wx.getStorageSync('userInfo')) {
      console.log("用户还没有登录！！")
    } else {
      var user = wx.getStorageSync('user');
      console.log("user:",user)
      var phone = user.telephone;
      if (!user) {
        this.getOpenid();
      } else {
        if (!phone) {
          this.setData({
            isShow: true
          })
        } else {
          this.setData({
            isShow: false
          })
        }
      };
    }

  },

  async getOpenid() {

    // 1 获取用户信息
    const UserInfo = wx.getStorageSync("userInfo");
    console.log("userInfo:");
    console.log(UserInfo);
    // 2 获取小程序登录成功后的code
    const {
      code
    } = await login();
    console.log("code:" + code);
    //  3 发送请求 获取用户的token
    const loginParams = {
      "code": code,
      "nickName": UserInfo.nickName
    };
    const res = await request({
      url: "/api/getOpenid.action",
      data: loginParams,
      method: "post"
    });

    console.log(res)

    // 4 把wx_openid存入缓存中
    const wx_openid = res.resultData.wx_openid;
    wx.setStorageSync("openid", wx_openid);
    wx.setStorageSync('user', res.resultData);
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    this.getOpenid();
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
    this.getOpenid();
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },

  toMyCouponPage: function () {
    wx.showToast({
      icon: "none",
      title: '该功能未完成'
    })
  },

  toAddressList: function () {
    wx.navigateTo({
      url: '/pages/receiverAddress/receiverAddress',
    })
  },

  // 跳转绑定手机号
  toBindingPhone: function () {
    wx.navigateTo({
      url: '/pages/binding-phone/binding-phone',
    })
  },

  toOrderListPage: function (e) {
    var sts = e.currentTarget.dataset.sts;
    wx.navigateTo({
      url: '/pages/orderList/orderList?sts=' + sts,
    })
  },

  exit: function () {
    wx.removeStorageSync('user');
    wx.removeStorageSync('userInfo');
    wx.removeStorageSync('openid');
    wx.showToast({
      title: '退出成功！',
      icon: 'success',
      duration: 2000
    });
    setTimeout(() => {
      this.onLoad();
    }, 2000);
  }



})