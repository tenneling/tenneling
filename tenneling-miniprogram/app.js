// app.js
App({
  onLaunch() {
    //wx.redirectTo({
    //  url: '/pages/auth/auth', // 该 url 就是第一条中新增的用户授权页地址
    // 需要企业认证
    // "request:fail errcode:-102 cronet_error_code:-102 error_msg:net::ERR_CONNECTION_REFUSED"
    //})
    // 展示本地存储能力
    //const logs = wx.getStorageSync('logs') || []
    //logs.unshift(Date.now())
    //wx.setStorageSync('logs', logs)
    wx.checkSession({
      success: function () {
        //session 未过期，并且在本生命周期一直有效
        console.log('登录未过期');
      },
      fail: function () {
        // 登录态过期
        // 登录 
        console.log('登录过期');
        wx.login({
          success: res => {
            // 发送 res.code 到后台换取 openId, sessionKey, unionId
            if (res.code) {
              console.log(res.code);
              wx.request({
                url: 'http://localhost:8080/userLogin', //测试api
                method: 'post',
                data: {
                  code: res.code, //用户登录凭证，有效期5分钟
                },
                header: { 
                  'content-type': 'application/json', //请求头
                },
                success: function (result) {
                  console.log(result);
                  wx.setStorageSync('sessionid', result.data);
                  console.log('成功登录')
                }

              }
              )
            }
          }
        })
      },
      globalData: {
      }
    })
  }
})
