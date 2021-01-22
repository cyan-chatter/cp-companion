const axios = require('axios');
const getTheTime = require('./getTheTime')

const codeforces = async (apiAddress) => {
    var contests = []        
    
    try{
            let response = await axios.get(apiAddress)
            //console.log(response.data) //****console log statement****
            if(response.data.status == "OK"){
                const obj = response.data.result                
                for(var i=0; i<obj.length; ++i){
                    
                    //var timestamp = {date : null, start : null} 
                    var timestamp = getTheTime(obj[i].startTimeSeconds) //~bug
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