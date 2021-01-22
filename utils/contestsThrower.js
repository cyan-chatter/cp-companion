const codeforces = require('./cfContests')
const leetcode = require('./lcContests')
const contestsAdder = async (channel,api) => {
    var contests = []
    try{
        const response = await channel(api)
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

const contestsThrower = async ()=>{
    var contestsObj = {codeforces: null, leetcode: null}
    const cfAPI = 'https://codeforces.com/api/contest.list'
    const lcAPI = 'https://leetcode.com/graphql'
    var cdfp = await contestsAdder(codeforces,cfAPI)
    var lcfp = await contestsAdder(leetcode,lcAPI)
    contestsObj.codeforces = cdfp
    contestsObj.leetcode = lcfp
    return contestsObj
}

module.exports = contestsThrower
