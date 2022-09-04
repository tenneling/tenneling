// pages/auth/auth.js
Page({
  data: {},
  onLoad: function () {
  },

  getPhoneNumber: function (e) {
    console.log(e)
    wx.request({
      url: 'https://www.lescouple.top:9092/getPhone',
      data: {
        'code': e.detail.code
      },
      method: 'GET', // OPTIONS, GET, HEAD, POST, PUT, DELETE, TRACE, CONNECT
      header: {
        'content-type': 'application/json'
      }, // 设置请求的 header
      success: function (data2) {
        wx.hideLoading()
        // console.log("data2", data2)
        if (data2.statusCode == 200 && data2.data.Message.phoneNumber) {
          self.setData({
            phone: data2.data.Message.phoneNumber
          })
          app.globalData.phonenumber = data2.data.Message.phoneNumber
          console.log(getApp().globalData.phonenumber);
          wx.setStorageSync('tel', app.globalData.phonenumber)
        }
        if (app.globalData.phonenumber.length >= 11) {
          wx.switchTab({
            url: '/pages/index/index',
          })
        } else {
          wx.redirectTo({
            url: '/pages/auth/auth',
          })
        }
      },
      fail: function (err) {
        console.log(err);
      }
    })
  }
})