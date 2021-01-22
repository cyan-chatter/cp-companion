const axios = require('axios');
const getTheTime = require('./getTheTime')

const leetcode = async (apiAddress) => {
    var contests = []        
    
    try{
        let body =  { 
            query: `
                query {
                    allContests{ 
                        titleSlug
                        title
                        startTime
                        duration
                        originStartTime
                    }
                }
            `, 
            variables: {}
        }
        let options = {
            headers: {
                'Content-Type': 'application/json'
            }
        }
           let response = await axios.post(apiAddress, body, options)
              
                const obj = response.data.data.allContests                
                for(var i=0; i<obj.length; ++i){
     
                    var timestamp = getTheTime(obj[i].startTime)
                    const currentTime = (Date.now() / 1000) 
                    
                    if(currentTime < obj[i].startTime){ 
                        contests.push({
                            platform: 'leetcode',
                            title: obj[i].title,
                            url: 'https://leetcode.com/contest/' + obj[i].titleSlug,
                            duration: obj[i].duration/60, //in minutes
                            date: timestamp.date,
                            start: timestamp.time,
                            phase: '',
                            page: 0
                        })
                    }

                }
            
    }catch(error){
        throw new Error(error)
    }

    return contests         
}

module.exports = leetcode