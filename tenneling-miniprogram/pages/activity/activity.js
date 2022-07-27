// pages/activity/activity.js
Page({

    /**
     * 页面的初始数据
     */
    data: {
        active: 'continue'
    },

    // 获取活动列表
    getActivity(){
        wx.request({
            url:'https://escook.cn/slides',
            // url:"http://192.168.31.50:8080/insert",
            method:'GET',
            success:(res)=>{
                console.log(2222,res)
            }
        })
    },
    // 跳转活动详情
    toDetail(e){
        // 获取当前活动id
       console.log(777,e.target.dataset)
    },
    // tabs切换 
    onChange(){
    },
    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {
        this.getActivity()
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
            console.log('设置选中项 1')
            that.getTabBar().setData({
                selected: 1
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