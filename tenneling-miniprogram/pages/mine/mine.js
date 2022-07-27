// pages/mine/mine.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        statusBarHeight: wx.getSystemInfoSync()['statusBarHeight']+44,
        headImg:"../../image/userImg.jpg", //头像
        list:[
            {
                name:"我的活动",
                imgUrl:"../../image/local/1.jpg",
            },
            {
                name:"我的图册",
                imgUrl:"../../image/local/2.jpg",
            }
        ]
    },
    //跳转编辑资料页
    goToInfo(){
        wx.navigateTo({
            url: "../minePages/mineInfo/mineInfo"
        })
    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        console.log(this.data.statusBarHeight)
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
        let that = this;
        if (typeof that.getTabBar === 'function' &&
            that.getTabBar()) {
            console.log('设置选中项 4')
            that.getTabBar().setData({
                selected: 4
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