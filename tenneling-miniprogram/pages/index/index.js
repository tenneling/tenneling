// index.js
// 获取应用实例
const app = getApp()

Page({
  data: {
    motto: 'Hello World',
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    canIUseGetUserProfile: true,
    canIUseOpenData: false
  },
  // 事件处理函数
  bindViewTap() {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  // 跳转到首页
  ToIndexPage() {
    wx.switchTab({
      "url": "/pages/home/home"
    })
  },
  onLoad() {
    if (wx.getUserProfile) {
      this.setData({
        canIUseGetUserProfile: true
      })
    }
  },
  getUserProfile(e) {
    console.log(e);
    // 推荐使用wx.getUserProfile获取用户信息，开发者每次通过该接口获取用户个人信息均需用户确认，开发者妥善保管用户快速填写的头像昵称，避免重复弹窗
    wx.getUserProfile({
      desc: '展示用户信息', // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
      success: (res) => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
        console.log("--------------获取到的用户信息------------------");
        console.log(res);
        const openid = wx.getStorageSync('openid');
        wx.request({
          url: 'http://localhost:8080/saveUser', //测试api
          method: 'post',
          data: {
            nickName : res.userInfo.nickName,
            avatarUrl : res.userInfo.avatarUrl,
            gender : res.userInfo.gender,
            city : res.userInfo.city,
            country : res.userInfo.country,
            language : res.userInfo.language,
            province : res.userInfo.province,
            openid: openid
          },
          header: {
            'content-type': 'application/json', //请求头
          },
          success: function (result) {
            console.log(result);
            console.log('----------成功保存用户信息----------');
          },
          fail: ui => {
            console.log("-------------------保存用户信息失败--------------------");
          }
        }
        )
      },
      fail: res => {
        console.log("-------------------授权失败--------------------");
        console.log(res)
        //拒绝授权
        wx.showToast({
          title: '您拒绝了请求,不能正常使用小程序',
          icon: 'error',
          duration: 2000
        });
        return;
      }
    })
  }
})
