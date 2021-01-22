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
              
            //console.log(response.data.data) //****console log statement****
            
            
                const obj = response.data.data.allContests                
                for(var i=0; i<obj.length; ++i){
     
                    var timestamp = getTheTime(obj[i].startTime)
                    
                    const currentTime = Date.now()
                    console.log('current: ' + currentTime)
                    
                    console.log('start: ' + obj[i].startTime + ': ' + timestamp.start) //~bug
                    
                    if(1){ //condotion pending ~bug
                        contests.push({
                            platform: 'leetcode',
                            title: obj[i].title,
                            url: 'https://leetcode.com/contest/' + obj[i].titleSlug,
                            duration: obj[i].duration/60, //in minutes
                            date: timestamp.date,
                            start: timestamp.start,
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