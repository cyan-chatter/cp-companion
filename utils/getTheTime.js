const getTheTime = (unixTime)=>{
    var ts = unixTime * 1000;    
    var curdate = new Date(ts)
    var year = curdate.getFullYear()
    var month = ("0" + (curdate.getMonth() + 1)).slice(-2)
    var day = ("0" + curdate.getDate()).slice(-2)
    var date = year + "-" + month + "-" + day
    var time = new Date(ts).toTimeString()
    return {
        date, time
    }
}

module.exports = getTheTime