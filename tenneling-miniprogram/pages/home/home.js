//home.js
var util = require("../../utils/util.js");

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
    curBegin: 0,
    curFinish: 1,
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
    let timeArr = util.setTimeHalf();
    this.setData({
      curIpt: e.detail.value,
      curRange: timeArr
    })
  },
  //加载列表数据
 async listdata(){
  const  that = this
     // 加载列表数据
     wx.request({
      url: 'http://192.168.0.112:8080/getToDoList', //测试api
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
    console.log(e);
    console.log(e.detail.value);
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
      begin = this.data.curRange[this.data.curBegin],
      finish = this.data.curRange[this.data.curFinish];
    if (cnt) {
      wx.request({
        url: 'http://192.168.0.112:8080/insertToDoList', //测试api
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
      curFinish: Number(e.detail.value) + 1
    })
  },
  finishChange(e) {
    this.setData({
      curFinish: e.detail.value
    })
  },
  //修改备忘录
  toChange(e) {
    let i = e.target.dataset.id;
    this.setData({
      lists: editArr(this.data.lists, i, { editing: true })
    })
  },
  iptEdit(e) {
    let i = e.target.dataset.id;
    this.setData({
      lists: editArr(this.data.lists, i, { curVal: e.detail.value })
    })
  },
  saveEdit(e) {
    let i = e.target.dataset.id;
    this.setData({
      lists: editArr(this.data.lists, i, { content: this.data.lists[i].curVal, editing: false })
    })
  },
  setDone(e) {
    let i = e.target.dataset.id;
    var that = this;
    wx.request({
      url: 'http://192.168.0.112:8080/updateToDoStatus', //测试api
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
      url: 'http://192.168.0.112:8080/deleteToDoList', 
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
      url: 'http://localhost:8080/getToDoList', //测试api
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
  },
  saveData() {
    let listsArr = this.data.lists;
    wx.setStorage({
      key: 'todolist',
      data: listsArr
    })
  }
})
