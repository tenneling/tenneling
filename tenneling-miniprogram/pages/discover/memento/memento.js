
var util = require("../../../utils/util.js");
var moment = require("../../../utils/memento.js");

//更改数组 第三个参数是对象
function editArr(arr, i, editCnt) {
  let newArr = arr, editingObj = newArr[i];
  for (var x in editCnt) {
    editingObj[x] = editCnt[x];
  }
  return newArr;
}

Page({
  data: {
    userInfo: {},
    curIpt: '',
    showAll: true,
    lists: [],
    curRange: [],
    curBegin: moment(util.setTimeHalf()[0]).format('YYYY-MM-DD'),
    curFinish: moment(util.setTimeHalf()[1]).format('YYYY-MM-DD'),
    remind: [],
    isAlert: false,
    openid: wx.getStorageSync('openid')
  },
  //事件处理函数
  bindViewTap: function () {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {
    this.listdata();
  },
  //可供选择的时间数组和已输入文本
  iptChange(e) {
    this.setData({
      curIpt: e.detail.value,
    })
  },
  //加载列表数据
 async listdata(){
  const  that = this;
     // 加载列表数据
     wx.request({
      url: 'https://www.lescouple.top:9092/getToDoList', //测试api
      method: 'GET',
      data: {
        openid: that.data.openid,
        status : 'A'
      },
      header: {
        'content-type': 'application/json', //请求头
      },
      success: function (result) {
        that.setData({
          lists: result.data,
          showAll:true,
          curIpt: '',
          curRange: [],
        })
      }
    })
  },
  //是否提醒
  switchInfo(e) {
    this.setData({
      isAlert: e.detail.value
    })
  },
  //初始化表单
  formReset() {
    this.setData({
      curIpt: '',
      curRange: []
    })
  },
  formSubmit() {
    const  that = this
    let cnt = this.data.curIpt,
      begin = this.data.curBegin,
      finish = this.data.curFinish;
    if (cnt) {
      wx.request({
        url: 'https://www.lescouple.top:9092/insertToDoList', //测试api
        method: 'POST',
        data: {
          content: cnt,
          startTime: begin,
          endTime: finish,
          openid: that.data.openid,
          isAlert: that.data.isAlert,
          status: 'A'
        },
        header: {
          'content-type': 'application/json', //请求头
        },
        success: function (result) {
          if(result.code = 200){
            that.listdata();
          }
        }
      })
    }
  },
  beginChange(e) {
    this.setData({
      curBegin: e.detail.value,
      curFinish: moment(moment(new Date(e.detail.value)) + 3600 * 1000 *24).format('YYYY-MM-DD'),
    })
  },
  finishChange(e) {
    this.setData({
      curFinish: e.detail.value
    })
  },
  setDone(e) {
    let i = e.target.dataset.id;
    var that = this;
    wx.request({
      url: 'https://www.lescouple.top:9092/updateToDoStatus', //测试api
      method: 'POST',
      data: {
        openid: that.data.openid,
        id: i,
        status: 'I'
      },
      header: {
        'content-type': 'application/json', //请求头
      },
      success: function (result) {
        if(result.code = 200){
          that.listdata();
        }
      }
    })
  },
  toDelete(e) {
    let i = e.target.dataset.id;
    const that = this
    wx.request({
      url: 'https://www.lescouple.top:9092/deleteToDoList', 
      method: 'POST',
      data: {
        id: i
      },
      header: {
        'content-type': 'application/json', //请求头
      },
      success: function (result) {
        if(result.code = 200){
          that.listdata()
        }
      }
    })
  },
  showUnfinished() {
    const that = this;
    that.listdata();
  },
  showAll() {
    //显示全部事项
    const  that = this
     // 加载列表数据
     wx.request({
      url: 'https://www.lescouple.top:9092/getToDoList', //测试api
      method: 'GET',
      data: {
        openid: that.data.openid
      },
      header: {
        'content-type': 'application/json', //请求头
      },
      success: function (result) {
        that.setData({
          lists: result.data,
          showAll: false
        })
      }
    })
  }
})
