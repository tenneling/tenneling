// component/tabBar/tabbar.js
Component({
    /**
     * 组件的属性列表
     */
    properties: {

    },
    options: {
      addGlobalClass: true,
    },
    /**
     * 组件的初始数据
     */
    data: {
        selected: 0,
        color: "#B2B2B2",
        selectedColor: "#34A342",
        list: [{
            pagePath: "/pages/home/home",
            iconPath: "/image/home.png",
            selectedIconPath: "/image/home-active.png",
            text: "首页",
            isSpecial: false
        }, {
            pagePath: "/pages/activity/activity",
            iconPath: "/image/activity.png",
            selectedIconPath: "/image/activity-active.png",
            text: "活动",
            isSpecial: false
        },{
            pagePath: "/pages/index/index",
            iconPath: "/image/plus-active.png",
            selectedIconPath: "/image/plus-active.png",
            text: "发布按钮",
            isSpecial: true
        }, {
            pagePath: "/pages/discover/discover",
            iconPath: "/image/discover.png",
            selectedIconPath: "/image/discover-active.png",
            text: "发现",
            isSpecial: false
        }, {
            pagePath: "/pages/mine/mine",
            iconPath: "/image/mine.png",
            selectedIconPath: "/image/mine-active.png",
            text: "我的",
            isSpecial: false
        }],
        show: false,
    },

    /**
     * 组件的方法列表
     */
    methods: {
        selTabbar(e) {
            console.log(e);
            if (e.currentTarget.dataset.index == 2) {
                console.log('发布按钮');
                
            } else {
                //正常的tabbar切换界面
                wx.switchTab({
                    url: e.currentTarget.dataset.item.pagePath
                })
            }
        },
        onClickShow() {
            this.setData({ show: true });
          },
        onClickHide() {
            this.setData({ show: false });
        },
        addActivity() {
            wx.navigateTo({
              url: '/component/activity-form/index',
            })
        }
    },
    lifetimes: {
        attached: function () {
            // 在组件实例进入页面节点树时执行

        },
        detached: function () {
            // 在组件实例被从页面节点树移除时执行
        },
    },
})
