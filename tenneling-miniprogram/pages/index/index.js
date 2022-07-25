Page ({
  data: {
    openid: null,
    session_key: null,
    unionid: null,
  },
  onLoad () {
    var that = this
    wx.login ({
      success: res => {
        if (res.code) {
          console.log(res.code)
          wx.getUserInfo({
            success: function (res_) {
              wx.request ({
                url: 'http://localhost:8080/userLogin', //测试api
                method: 'post',  
                data: {
                  code: res.code, //用户登录凭证，有效期5分钟
                  encryptedData: res_.encryptedData,
                  iv:res_.iv,
                  rawData:res_.rawData,
                  signature:res_.signature
                },
                header: {
                  'content-type': 'application/json', //请求头
                },
                success: function (result) {
                  console.log (result);
                  console.log (res_);
                },
              });
            }
          })
          
        }
      },
    })
  }
});