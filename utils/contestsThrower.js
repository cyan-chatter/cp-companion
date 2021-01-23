const codeforces = require('./cfContests')
const leetcode = require('./lcContests')
const contestsAdder = async (channel,api) => {
    var contests = []
    try{
        const response = await channel(api)
        for(var i=0; i<response.length;++i){
            contests.push(response[i])   
        }
    }
    catch(error){
        console.log(error)//****console log statement****
    }
    return contests
}

const contestsThrower = async (page)=>{
    var contestsAll = []
    const cfAPI = 'https://codeforces.com/api/contest.list'
    const lcAPI = 'https://leetcode.com/graphql'
    var cdfp = await contestsAdder(codeforces,cfAPI)
    var lcfp = await contestsAdder(leetcode,lcAPI)
    contestsAll.push(...cdfp) 
    contestsAll.push(...lcfp)
    for(var i=0; i<contestsAll.length; ++i){
        contestsAll[i].page = Math.floor(i/10) + 1
    }  
    var reqdContests = {data:[], total_size: contestsAll.length}
    console.log(page)
    for(var i=0; i<contestsAll.length; ++i){
        console.log(contestsAll[i].page)
        if(page == contestsAll[i].page){
            var obj = contestsAll[i]
            reqdContests.data.push(obj)
        }
    }
    console.log('reqd: ' + reqdContests)
    return reqdContests
}

module.exports = contestsThrower
