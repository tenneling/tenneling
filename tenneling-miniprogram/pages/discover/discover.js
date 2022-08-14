Page({
  data: {

  },

  viewCalendar() {
    wx.navigateTo({
      url: '/pages/discover/calendar/calendar',
    })
  },

  viewToDoList() {
    wx.navigateTo({
      url: '/pages/discover/memento/memento',
    })
  },

  viewFiveInsurance() {
    wx.navigateTo({
      url: '/pages/discover/fiveInsurance/fiveInsurance',
    })
  }
})