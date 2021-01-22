const axios = require('axios');

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

const codeforces = async (apiAddress) => {
    var contests = []        
    
    try{
            let response = await axios.get(apiAddress)
            console.log(response.data) //****console log statement****
            if(response.data.status == "OK"){
                const obj = response.data.result                
                for(var i=0; i<obj.length; ++i){
                    
                    var timestamp = {date : null, start:null} 
                    timestamp = getTheTime(obj[i].startTimeSeconds)
                    if(obj[i].phase == 'BEFORE'){
                        contests.push({
                            platform: 'codeforces',
                            title: obj[i].name,
                            url: 'http://codeforces.com/contests',
                            duration: obj[i].durationSeconds/60, //in minutes
                            date: timestamp.date,
                            start: timestamp.start,
                            phase: obj[i].phase,
                            page: 0
                        })
                    }
                }
            }else{
                throw new Error('Unable to fetch contests from Codeforces')
            }
    }catch(error){
        throw new Error(error)
    }

    return contests         
}

module.exports = codeforces