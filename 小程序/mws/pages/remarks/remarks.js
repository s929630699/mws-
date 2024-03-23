// pages/ramarks/remarks.js
var app = getApp();
Page({

    /**
     * 页面的初始数据
     */
    data: {
        iceCode: true,
        reduceCream: true,
        addCream: true,
        remark1: '',
        remark2: '',
        remark3: '',
        otherRemark:''

    },

    iceCode: function (e) {
        if (this.data.iceCode == true) {
            this.setData({
                iceCode: !this.data.iceCode
            })
            this.data.remark1 = "饮料要冰的，";
            console.log(this.data.remark1);
        } else {
            this.setData({
                iceCode: !this.data.iceCode
            })
            this.data.remark1 = "";
            console.log(this.data.remark1);
        }
    },

    addCream: function (e) {
        var reduceCream=this.data.reduceCream;
        var addCream=this.data.addCream;
        if(reduceCream==true&&addCream==true){
            this.setData({
                addCream:!addCream
            })
            this.data.remark2="多加奶油，";
            console.log(this.data.remark2);
        }else if(reduceCream==false){
            this.setData({
                reduceCream:true,
                addCream:false
            })
            this.data.remark3="";
            this.data.remark2="多加奶油，";
            console.log(this.data.remark2);
        }else{
            this.setData({
                addCream:!addCream
            })
            this.data.remark2="";
            console.log(this.data.remark2);
        }
    },

    reduceCream: function (e) {
        var reduceCream=this.data.reduceCream;
        var addCream=this.data.addCream;
        if(reduceCream==true&&addCream==true){
            this.setData({
                reduceCream:!reduceCream
            })
            this.data.remark3="少加奶油，";
            console.log(this.data.remark3);
        }else if(addCream==false){
            this.setData({
                reduceCream:false,
                addCream:true
            })
            this.data.remark2="";
            this.data.remark3="少加奶油，";
            console.log(this.data.remark3);
        }else{
            this.setData({
                reduceCream:!reduceCream
            })
            this.data.remark3="";
            console.log(this.data.remark3);
        }
    },

    input: function(e){
		if(e.detail&&e.detail.value.length>0){
			this.data.otherRemark = e.detail.value
		}
		console.log(e.detail.value);
    },
    
    confirm: function(){
        app.globalData.cart.remarks = this.data.remark1+this.data.remark2+this.data.remark3+this.data.otherRemark;
		console.log('订单备注为：',app.globalData.cart.remarks);
        wx.navigateBack({
          delta: 1,
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

    }
})