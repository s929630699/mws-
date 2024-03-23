// pages/binding-phone/binding-phone.js
import {request} from "../../request/request2";
import {login} from "../../utils/asyncWx";
Page({

  /**
   * 页面的初始数据
   */
  data: {
    phonenum:'',
    code:'',
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

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

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },

  // 获取用户信息
  async handleGetUserInfo(e) {
    try {
      // 1 获取用户信息
    const UserInfo =wx.getStorageSync("userInfo");
    console.log("userInfo:");
    console.log(UserInfo);
    // 2 获取小程序登录成功后的code
    const { code } = await login();  
    console.log("code:"+code);
        //  3 发送请求 获取用户的token
      const loginParams = {
        "code": code,
        "nickName": UserInfo.nickName,
        "telephone":this.data.phonenum
      };
      const res = await request({
        url: "/api/wxlogin.action",
        data: loginParams,
        method: "post"
      });

      console.log(res)

      // 4 把token存入缓存中 同时跳转回上一个页面
      const token = res.resultData.token;
      wx.setStorageSync("token", token);
      const telephone=res.resultData.telephone;
      wx.setStorageSync('telephone', telephone);
      wx.setStorageSync('user', res.resultData);
      wx.showToast({
        title: '绑定成功',
        icon: 'success',
        duration: 2000//持续的时间
   
      });
      setTimeout(() => {
        wx.switchTab({
          url: '../../pages/index/index',
        })
      }, 2000);
      

    } catch (error) {
      console.log(error);
    }
  },

  getCodeNumber:function(){
    if (!this.data.phonenum) {
      wx.showToast({
        title: '请输入手机号',
        icon: "none"
      })
      return;
    }
  },
  onPhoneInput:function(e){
    this.setData({
      phonenum: e.detail.value
    });
    console.log(e.detail.value)
  },
  onCodeInput: function (e) {
    this.setData({
      code: e.detail.value
    });
  },
})
