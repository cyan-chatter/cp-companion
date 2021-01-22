const axios = require('axios');
const getTheTime = require('./getTheTime')

const codeforces = async (apiAddress) => {
    var contests = []        
    
    try{
            let response = await axios.get(apiAddress)
            if(response.data.status == "OK"){
                const obj = response.data.result                
                for(var i=0; i<obj.length; ++i){
                    var timestamp = getTheTime(obj[i].startTimeSeconds) 
                    if(obj[i].phase == 'BEFORE'){
                        contests.push({
                            platform: 'codeforces',
                            title: obj[i].name,
                            url: 'http://codeforces.com/contests',
                            duration: obj[i].durationSeconds/60, //in minutes
                            date: timestamp.date,
                            start: timestamp.time,
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