// pages/delivery-address/delivery-address.js

import {
  request
} from "../../request/request";
var app = getApp();
Page({
  data: {
    defaultSize: 'mini',
    disabled: false,
    plain: true,
    loading: false,
    addressList: [],
    addAddress: '',
    order: -1
  },

  onLoad: function (option) {
    if (option.order) {
      this.setData({
        order: option.order
      });
    }
  },

  //新增收货地址
  onAddAddr: function (e) {
    wx.removeStorageSync('addr');
    wx.navigateTo({
      url: '/pages/editAddress/editAddress',
    })
  },

  //设置为默认地址
  onDefaultAddr: function (e) {
    console.log(e.currentTarget.dataset.item)
    var pages = getCurrentPages(); //当前页面
    var prevPage = pages[pages.length - 1]; //上一页面
    prevPage.setData({ //直接给上移页面赋值
      item: e.currentTarget.dataset.item,
    });
    wx.setStorageSync('addressInfo', e.currentTarget.dataset.item);
    console.log(e.currentTarget.dataset.item);
    app.globalData.addressInfo = e.currentTarget.dataset.item;
    console.log(app.globalData.addressInfo)
    wx.navigateBack({ //返回
      delta: 1
    })

    var addrId = e.currentTarget.dataset.item.id;
    console.log("addrId:" + addrId)
    var user = wx.getStorageSync('user');
    var userId = user.id;
    wx.showLoading();
    request({
        url: "/api/addrDefault.action",
        method: "post",
        data: {
          "userId": userId,
          "addrId": addrId
        }
      })
      .then(result => {
        this.setData({
          addressList: result
        });
        console.log("addressList:")
        console.log(result)

      })
  },

  //加载地址列表
  onShow: function () {
    var user = wx.getStorageSync('user');
    var userId = user.id;
    wx.showLoading();
    request({
        url: "/api/address.action",
        data: {
          "userId": userId
        },
        method: "post"
      })
      .then(result => {
        this.setData({
          addressList: result
        });
        console.log("addressList:")
        console.log(result)
      })
  },

  // 修改地址 
  toEditAddress: function (e) {
    var addrId = e.currentTarget.dataset.addrid;
    console.log("addrId:" + addrId)
    for (const i of this.data.addressList) {
      if (i.id == addrId) {
        this.data.address = i
      }
    }
    console.log(this.data.address)
    wx.setStorageSync('addr', this.data.address)
    wx.navigateTo({
      url: '/pages/editAddress/editAddress?addrId=' + addrId,
    })
  },

})