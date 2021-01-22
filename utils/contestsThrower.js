const codeforces = require('./cfContests')

const contestsThrower = async()=>{

    var contests = []
    
    const cfAPI = 'https://codeforces.com/api/contest.list'
    try{
        const response = await codeforces(cfAPI)
        for(var i=0; i<response.length; ++i){
            contests.push(response[i])
        }
    }
    catch(error){
        console.log(error)//****console log statement****
    }
    
    return contests
}

module.exports = contestsThrower
