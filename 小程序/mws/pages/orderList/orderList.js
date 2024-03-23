import {
    request
} from "../../request/request";
var http = require('../../request/request');
Page({

    /**
     * 页面的初始数据
     */
    data: {
        list: [],
        num: {},
        current: 1,
        pages: 0,
        sts: 0,
        modalHidden: true,
        orderId: '',
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        if (options.sts) {
            this.setData({
                sts: options.sts
            });
            this.loadOrderData(options.sts, 1);
        } else {
            this.loadOrderData(0, 1);
        }
    },

    /**
     * 加载订单数据
     */
    async loadOrderData(sts, current) {
        var user = wx.getStorageSync('user');
        var userId = user.id;
        wx.showLoading();
        //加载订单列表
        if (sts == 0 || sts == null) {
            var params = {
                url: "/api/allOrderList.action",
                method: "post",
                data: {
                    "userId": userId,
                }
            };
        } else if (sts == 1) {
            var params = {
                url: "/api/noPayOrderList.action",
                method: "post",
                data: {
                    "userId": userId,
                    "paystate": 1
                }
            };
        } else if (sts == 2) {
            var params = {
                url: "/api/OrderListByOrderState.action",
                method: "post",
                data: {
                    "userId": userId,
                    "orderState": 1
                }
            };
        } else if (sts == 3) {
            var params = {
                url: "/api/OrderListByOrderState.action",
                method: "post",
                data: {
                    "userId": userId,
                    "orderState": 2
                }
            };
        } else if (sts == 4) {
            var params = {
                url: "/api/OrderListByOrderState.action",
                method: "post",
                data: {
                    "userId": userId,
                    "orderState": 3
                }
            };
        }

        var list = await http.request(params);
        console.log("orderList:", list);
        for (const i in list) {
            this.data.num[i] = 0;
            for (var orderitem of list[i].orderitem) {
                this.data.num[i] += orderitem.buyNum;
                console.log(this.data.num);
            }
        }
        this.setData({
            list: list,
            num: this.data.num
        })

    },

    /**
     * 状态点击事件
     */
    onStsTap: function (e) {
        var sts = e.currentTarget.dataset.sts;
        this.setData({
            sts: sts
        });
        this.loadOrderData(sts, 1);
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
        if (this.data.current < this.data.pages) {
            this.loadOrderData(this.data.sts, this.data.current + 1);
        }
    },

    /**
     * 用户点击右上角分享
     */
    onShareAppMessage: function () {

    },


    /**
     * 查看物流
     */
    toDeliveryPage: function (e) {
        wx.showToast({
            title: '抱歉~这个不会',
            icon: 'error'
        })
    },

    /**
     * 取消订单
     */
    onCancelOrder: function (e) {
        var orderId = e.currentTarget.dataset.id;
        console.log(e.currentTarget.dataset)
        var ths = this;
        wx.showModal({
            title: '',
            content: '是否要取消此订单？',
            confirmColor: "#3e62ad",
            cancelColor: "#3e62ad",
            cancelText: '噢不',
            confirmText: '确实',
            success(res) {
                if (res.confirm) {
                    wx.showLoading({
                        mask: true
                    });

                    var params = {
                        url: "/api/orderCancel.action",
                        method: "post",
                        data: {
                            orderId: orderId
                        },
                        callBack: function (res) {
                            //console.log(res);
                            ths.loadOrderData(ths.data.sts, 1);
                            wx.hideLoading();
                        }
                    };
                    http.request(params);

                    ths.loadOrderData(ths.data.sts, 1);
                    console.log("成功")

                } else if (res.cancel) {
                    console.log('用户点击取消')
                }
            }
        })

    },

    /**
     * 付款
     */
    onPayAgain: function (e) {
        this.setData({
            modalHidden: false,
            orderId: e.currentTarget.dataset.id
        })
    },


    /**
     * 查看订单详情
     */
    toOrderDetailPage: function (e) {
        wx.navigateTo({
            url: '/pages/order-detail/order-detail?orderNum=' + e.currentTarget.dataset.ordernum,
        })
    },

    /**
     * 确认收货
     */
    onConfirmReceive: function (e) {
        var orderId = e.currentTarget.dataset.id;
        console.log(orderId);
        var ths = this;
        wx.showModal({
            title: '',
            content: '您已确认收到咱精美的产品了吗？',
            confirmColor: "#eb2444",
            success(res) {
                if (res.confirm) {
                    wx.showLoading({
                        mask: true
                    });

                    var params = {
                        url: "/api/orderFinish.action",
                        method: "post",
                        data: {
                            orderId: orderId
                        },
                        callBack: function (res) {
                            //console.log(res);
                            ths.loadOrderData(ths.data.sts, 1);
                            wx.hideLoading();
                        }
                    };
                    http.request(params);

                    ths.loadOrderData(ths.data.sts, 1);
                } else if (res.cancel) {
                    console.log('用户点击取消')
                }
            }
        })
    },
    //删除已完成||已取消的订单
    delOrderList: function (e) {
        var ths = this;
        var orderId = e.currentTarget.dataset.id;
        console.log(orderId);
        wx.showModal({
            title: '警告',
            content: '确定要删除此订单吗？',
            confirmColor: "#eb2444",
            success(res) {
                if (res.confirm) {

                    wx.showLoading();
                    var params = {
                        url: "/api/orderRemove.action",
                        method: "post",
                        data: {
                            orderId: orderId
                        },
                        callBack: function (res) {
                            ths.loadOrderData(ths.data.sts, 1);
                            wx.hideLoading();
                        }
                    }
                    http.request(params);

                    ths.loadOrderData(ths.data.sts, 1);
                } else if (res.cancel) {
                    console.log('用户点击取消')
                }
            }
        })
    },

    modalConfirm: function () {
        console.log("用户已付款")
        var orderId = this.data.orderId;
        console.log(orderId)
        wx.showLoading({
            mask: true
        });

        var params = {
            url: "/api/orderPay.action",
            method: "post",
            data: {
                orderId: orderId
            },
            callBack: function (res) {
                //console.log(res);
                wx.hideLoading();
            }
        };
        http.request(params);
        this.loadOrderData(this.data.sts, 1);
        this.setData({
            modalHidden: true
        })
    },

    modalCandel: function () {
        console.log("用户点击取消");
        this.setData({
            modalHidden: true
        })
    },

    onShopAgain() {
        wx.switchTab({
            url: '../../pages/goods/goods',
        })
    }
})