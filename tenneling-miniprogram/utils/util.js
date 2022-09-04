const formatTime = date => {
  const year = date.getFullYear()
  const month = date.getMonth() + 1
  const day = date.getDate()
  const hour = date.getHours()
  const minute = date.getMinutes()
  const second = date.getSeconds()

  return `${[year, month, day].map(formatNumber).join('/')} ${[hour, minute, second].map(formatNumber).join(':')}`
}

function formatTime2(date) {
  var year = date.getFullYear()
  var month = date.getMonth() + 1
  var day = date.getDate()

  var hour = date.getHours()
  var minute = date.getMinutes()
  var second = date.getSeconds()

  return [hour,minute];

 // return [ month, day].map(formatNumber).join('/') + ' ' + [hour, minute, second].map(formatNumber).join(':')
} 

function formatNumber(n) {
  n = n.toString()
  return n[1] ? n : '0' + n
}

function halfHour(){
  let timeArr = [];
  for (let i =0; i<=48; i++){
    if(i % 2==0){
      timeArr.push(formatNumber(i/2) +':00');
    }else{
      timeArr.push(formatNumber(Math.floor(i/2))+":30")
    }
  }
  return timeArr;
}

let timeArr = halfHour();

// 起始时间默认当天，结束时间默认下一天
function setTimeHalf(){
  var thisDate =  new Date().getTime()
  var afterDate =  new Date().getTime() + 3600 * 1000 * 24 ;
  return [thisDate, afterDate]
}

module.exports = {
  formatTime: formatTime,
  setTimeHalf:setTimeHalf
}
