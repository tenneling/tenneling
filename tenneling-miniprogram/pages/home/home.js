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
    isAlert: '',
  },
  //事件处理函数
  bindViewTap: function () {
    wx.navigateTo({
      url: '../logs/logs'
    })
  },
  onLoad: function () {
    var that = this;
    console.log(that);
    const openid = wx.getStorageSync('openid');
    console.log(openid);
    // 加载列表数据
    wx.request({
      url: 'http://localhost:8080/getToDoList', //测试api
      method: 'GET',
      data: {
        openid:openid
      },
      header: {
        'content-type': 'application/json', //请求头
      },
      success: function (result) {
        console.log(result);
        if (result.data) {
          that.setData({
            lists: res.data
          })
        }
      }
    })
  },
  //可供选择的时间数组和已输入文本
  iptChange(e) {
    let timeArr = util.setTimeHalf();
    this.setData({
      curIpt: e.detail.value,
      curRange: timeArr
    })
  },
  //是否提醒
  switchInfo(e){
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
    let cnt = this.data.curIpt, 
    newLists = this.data.lists, 
    i = newLists.length, 
    begin = this.data.curRange[this.data.curBegin], 
    finish = this.data.curRange[this.data.curFinish];
    if (cnt) {
      newLists.push({ id: i, content: cnt, done: false, beginTime: begin, finishTime: finish, editing: false });
      this.setData({
        lists: newLists,
        curIpt: ''
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
    let i = e.target.dataset.id, originalDone = this.data.lists[i].done;
    this.setData({
      lists: editArr(this.data.lists, i, { done: !originalDone })
    })
  },
  toDelete(e) {
    let i = e.target.dataset.id, newLists = this.data.lists;
    newLists.map(function (l, index) {
      if (l.id == i) {
        newLists.splice(index, 1);
      }
    })
    this.setData({
      lists: newLists
    })
  },
  doneAll() {
    let newLists = this.data.lists;
    newLists.map(function (l) {
      l.done = true;
    })
    this.setData({
      lists: newLists
    })
  },
  deleteAll() {
    this.setData({
      lists: [],
      remind: []
    })
  },
  showUnfinished() {
    this.setData({
      showAll: false
    })
  },
  showAll() {
    //显示全部事项
    this.setData({
      showAll: true
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
