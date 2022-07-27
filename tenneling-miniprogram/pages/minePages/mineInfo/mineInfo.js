// pages/minePages/mineInfo/mineInfo.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        statusBarHeight: wx.getSystemInfoSync()['statusBarHeight'], //状态栏高度
        navHeight:"", //导航栏高度
        navTop:"",
        endDate:"", //获取当前年月日
        Info:{
            userImg:"../../../image/userImg.jpg",
            nickName:"迪丽热巴", //昵称
            birthDate:new Date().getTime(), //日期
            height:"", //身高
            weight:"", //体重
            region:"", //地区
        },
        minDate: new Date(1900, 1, 1).getTime(),
        maxDate: new Date().getTime(),
        formatter(type, value) {
            if (type === 'year') {
              return `${value}年`;
            }
            if (type === 'month') {
              return `${value}月`;
            }
            return value;
        },
        birthDateShow:false, //日期选择器显示隐藏
        heightShow:false, //生选择器显示隐藏
        columnsHeight:[], //身高的选择器列表
        columnsWeight:[], //体重的选择器列表
        columnsRegion:[], //地区的选择器列表
    },
    //返回
    goBack() {
        wx.navigateBack({
          delta: 1,
        })
    },
    //打开日期选择器
    OpenDate(){
        this.setData({ birthDateShow: true })
    },
    onCloseDate(){
        this.setData({ birthDateShow: false });
    },
    onConfirmDate(e){
        console.log(e)
        let Info = this.data.Info;
        let valueDate = this.formatDate(e.detail);
        Info.birthDate = valueDate
        this.setData({ Info: Info,birthDateShow: false });
    },
    formatDate(value) {
        let date = value?new Date(value):new Date();
        let YY = date.getFullYear() + '-';
        let MM = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
        let DD = (date.getDate() < 10 ? '0' + (date.getDate()) : date.getDate());
        return YY + MM + DD;
    },
    onCancelDate(){
        this.setData({ birthDateShow: false });
    },
    //打开身高选择器
    OpenHeight(){
        this.setData({ heightShow: true })
    },
    onCloseHeight(){
        this.setData({ heightShow: false });
    },
    onConfirmHeight(e){
        let Info = this.data.Info;
        let value = e.detail.value;
        Info.height = value;
        this.setData({ Info: Info,heightShow: false });
    },
    onCancelHeight(){
        this.setData({ heightShow: false });
    },
    //获取身高的选择列表
    getHeight(){
        let list = [];
        for (let i = 141; i < 201; i++) {
            list.push(i+'cm')
        }
        list.push("≥201cm");
        list.unshift("≤140cm")
        this.setData({ columnsHeight: list });
    },
    //获取体重的选择列表
    getWeight(){
        let list = [];
        for (let i = 41; i < 80; i++) {
            list.push(i+'kg')
        }
        list.push("≥81kg");
        list.unshift("≤40kg")
        this.setData({ columnsWeight: list });
    },
    
    

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        this.getHeight();
        this.getWeight();
        let menuButtonObject = wx.getMenuButtonBoundingClientRect();
        wx.getSystemInfo({
          success: res => {
            let statusBarHeight = res.statusBarHeight,
              navTop = menuButtonObject.top,//胶囊按钮与顶部的距离
              navHeight = menuButtonObject.height + (menuButtonObject.top - statusBarHeight)*2;//导航高度
            this.setData({
                navHeight:navHeight,
                navTop:navTop
            })
          },
          fail(err) {
            console.log(err);
          }
        })
        let Info = this.data.Info;
        Info.birthDate = this.formatDate();
        Info.height = this.data.columnsHeight[0];
        Info.weight = this.data.columnsWeight[0];
        this.setData({
            Info:Info
        })
        
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