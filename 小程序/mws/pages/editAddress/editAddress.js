import { request } from "../../request/request";

// pages/editAddress/editAddress.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        value: [0, 0, 0],
        provArray: [],
        cityArray: [],
        areaArray: [],
        province: "",
        city: "",
        area: "",
        provinceId: 0,
        cityId: 0,
        areaId: 0,
        receiver: "",
        mobile: "",
        address: "",
        addrId: 0
    },

    onReceiverInput: function (e) {
        this.setData({
          receiver: e.detail.value
        });
        console.log("收货人："+e.detail.value)
      },
    
      onMobileInput: function (e) {
        this.setData({
          mobile: e.detail.value
        });
        console.log("手机号码："+e.detail.value)
      },

      onAddrInput: function (e) {
        this.setData({
          address: e.detail.value
        });
        console.log("收货地址："+e.detail.value)
      },

      /**
   * 保存地址
   */
  onSaveAddr: function () {
    var ths = this;
    var addrId=ths.data.addrId;
    var user=wx.getStorageSync('user');
    var userId=user.id;
    var receiver = ths.data.receiver;
    var mobile = ths.data.mobile;
    var address = ths.data.address;

    if (!receiver) {
      wx.showToast({
        title: '请输入收货人姓名',
        icon: "none"
      })
      return;
    }
    if (!mobile) {
      wx.showToast({
        title: '请输入手机号码',
        icon: "none"
      })
      return;
    }
    var regexp = /^[1]([3-9])[0-9]{9}$/;
    if (!regexp.test(mobile)) {
      wx.showToast({
        title: '请输入正确的手机号码',
        icon: "none"
      })
      return;
    }
    if (!address) {
      wx.showToast({
        title: '请输入详细地址',
        icon: "none"
      })
      return;
    }

    wx.showLoading();
    //添加或修改地址
    var params = {
        addrId:addrId,
        userId:userId,
        receiver: ths.data.receiver,
        mobile: ths.data.mobile,
        address: ths.data.address
      }
      console.log(params);
      
      if(this.data.addrId>0){
        console.log("修改")
        request({
            url:"/api/updateAddr.action",
            data:params,
            method:"post"
        })
      }else{
        console.log("添加")
        request({
            url:"/api/insertAddr.action",
            data:params,
            method:"post"
        })
      }
      wx.navigateBack({
        delta: 1,
      })
      
    },

      //删除配送地址
    onDeleteAddr: function (e) {
    var ths = this;
    wx.showModal({
      title: '提示',
      content: '确定要删除此收货地址吗？',
      confirmColor: "#eb2444",
      success(res) {
        if (res.confirm) {
          var addrId = ths.data.addrId;
            console.log("addrId:"+addrId);
          wx.showLoading();
          request({
              url:"/api/deleteAddr.action",
              method: "post",
              data:{
                  "addrId":addrId
              }
          })
          .then(result =>{
              wx.navigateBack({
                delta: 1,
              })
          })
        } else if (res.cancel) {
          console.log('用户点击取消')
        }
      }
    })

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
    onShow: function (e) {
        var addr=wx.getStorageSync('addr');
        this.data.receiver=addr.receiver;
        this.data.mobile=addr.mobile;
        this.data.address=addr.address;
        this.data.addrId=addr.id;
        console.log(addr)
        this.setData({
            receiver:this.data.receiver,
            mobile:this.data.mobile,
            address:this.data.address,
            addrId:this.data.addrId
        });
        
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