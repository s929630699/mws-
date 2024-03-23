// pages/order/order.js
import {
    request
} from "../../request/request"
var app = getApp();
var util = require('../../utils/util.js');
Page({

    /**
     * 页面的初始数据
     */
    data: {
        cart: app.globalData.cart,
        addressList: [],
        addressInfo: null,
        oid: '',
        modalHidden: true
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        var time = util.formatTime(new Date());
        this.setData({
            time: time,
            cart: app.globalData.cart,
        })
    },

    Tapremarks: function (options) {
        wx.navigateTo({
            url: '../remarks/remarks',
        })
    },

    Tapcashtopay: function (options) {
        console.log('Tapcashtopay');
        if (!wx.getStorageSync('addressInfo')) {
            wx.showToast({
                title: '未选择收货地址',
                icon: 'error',
                duration: 2000
            })
        } else {
            this.setData({
                modalHidden: false
            })
        }
    },

    modalConfirm: function () {
        var cart = app.globalData.cart;
        const addressInfo = wx.getStorageSync('addressInfo');
        const user = wx.getStorageSync('user');
        var receiver = addressInfo.receiver;
        var mobile = addressInfo.mobile;
        var address = addressInfo.address;
        var userId = user.id;
        var order = {
            "money": app.globalData.cart.total,
            "receiver": receiver,
            "mobile": mobile,
            "address": address,
            "paystate": "2",
            "userId": userId,
            "remarks": app.globalData.cart.remarks,
        }
        console.log(order)
        request({
            url: "/api/insertOrder.action",
            data: order,
            method: "post"
        }).then(res => {
            for (const i in cart.list) {
                var oid = res[0];
                var productId = i;
                var productName = cart.pname[i];
                var buyNum = cart.list[i];
                var buyPrice = cart.price[i];
                var orderitem = {
                    "oid": oid,
                    "productId": productId,
                    "productName": productName,
                    "buyNum": buyNum,
                    "buyPrice": buyPrice,
                }
                console.log(orderitem);
                request({
                    url: "/api/insertOrderitem.action",
                    data: orderitem,
                    method: "post"
                })
            }
        })


        app.globalData.cart = {
            count: 0,
            total: 0,
            list: {},
            pname: {},
            price: {},
            remarks: '',
        };
        wx.switchTab({
            url: '../../pages/goods/goods',
        })
    },

    modalCandel: function () {
        var cart = app.globalData.cart;
        const addressInfo = wx.getStorageSync('addressInfo');
        const user = wx.getStorageSync('user');
        var receiver = addressInfo.receiver;
        var mobile = addressInfo.mobile;
        var address = addressInfo.address;
        var userId = user.id;
        var order = {
            "money": app.globalData.cart.total,
            "receiver": receiver,
            "mobile": mobile,
            "address": address,
            "paystate": "1",
            "userId": userId,
            "remarks": app.globalData.cart.remarks,
        }
        console.log(order)
        request({
            url: "/api/insertOrder.action",
            data: order,
            method: "post"
        }).then(res => {
            for (const i in cart.list) {
                var oid = res[0];
                var productId = i;
                var productName = cart.pname[i];
                var buyNum = cart.list[i];
                var buyPrice = cart.price[i];
                var orderitem = {
                    "oid": oid,
                    "productId": productId,
                    "productName": productName,
                    "buyNum": buyNum,
                    "buyPrice": buyPrice,
                }
                console.log(orderitem);
                request({
                    url: "/api/insertOrderitem.action",
                    data: orderitem,
                    method: "post"
                })
            }
        })


        app.globalData.cart = {
            count: 0,
            total: 0,
            list: {},
            pname: {},
            price: {},
            remarks: '',
        };
        wx.switchTab({
            url: '../../pages/goods/goods',
        })
    },

    /*修改地址*/
    editAddress: function (event) {
        wx.navigateTo({
            url: '../chooseAddress/chooseAddress',
        })
    },

    /**
     * 生命周期函数--监听页面初次渲染完成
     */
    onReady: function () {},

    /**
     * 生命周期函数--监听页面显示
     */
    onShow: function () {
        var appAddressInfo = app.globalData.addressInfo;
        console.log(appAddressInfo);
        if (!appAddressInfo.id) {
            console.log("else")
            var user = wx.getStorageSync('user');
            var userId = user.id;
            request({
                    url: "/api/address.action",
                    data: {
                        "userId": userId
                    },
                    method: "post"
                })
                .then(result => {
                    this.data.addressList = result
                    console.log(this.data.addressList);
                    for (const i of this.data.addressList) {
                        if (i.defaultState == 1) {
                            this.data.addressInfo = i
                        }
                    }
                    this.setData({
                        addressInfo: this.data.addressInfo
                    })
                    wx.setStorageSync('addressInfo', this.data.addressInfo);
                    app.globalData.addressInfo = this.data.addressInfo;
                    console.log(this.data.addressInfo);
                })

            console.log("if")
        } else {
            this.setData({
                addressInfo: appAddressInfo
            })
        }
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

    }
})