// component/activity-form/index.js
Component({
    /**
     * 组件的属性列表
     */
    properties: {

    },

    /**
     * 组件的初始数据
     */
    data: {
        currentDate: new Date().getTime(),
        minDate: new Date().getTime(),
        formatter(type, value) {
        if (type === 'year') {
            return `${value}年`;
        }
        if (type === 'month') {
            return `${value}月`;
        }
        return value;
        },
        idShowDate:false
    },

    /**
     * 组件的方法列表
     */
    methods: {
        onInput(){},
        showDate() {
            this.setData({ idShowDate: true });
          },
        onCloseDate() {
            this.setData({ idShowDate: false });
        },
        onChangeNumber(){},
        toPlace(){}
    }
})
