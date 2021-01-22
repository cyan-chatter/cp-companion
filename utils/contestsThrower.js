const codeforces = require('./cfContests')

const contestsThrower = async()=>{

    var contests = []
    const cfAPI = 'https://codeforces.com/api/contest.list'
    try{
        const response = await codeforces(cfAPI)
        for(var i=0; i<response.length;){
            var contestsPage = new Array()
            for(var j=0; j<10 && i<response.length; ++j){
                response[i].page = contests.length + 1;
                contestsPage.push(response[i])
                ++i;
            }
            contests.push([...contestsPage])
        }
    }
    catch(error){
        console.log(error)//****console log statement****
    }
    
    return contests
}

module.exports = contestsThrower
