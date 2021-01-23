const express = require('express')
const router = new express.Router()

const getContests = require('../../utils/contestsThrower')

const routeHandler = {
    loadContestsList : async (req,res)=>{
        var page = req.query.page;
        console.log(page)
        const contests = await getContests(page)   
        res.send(contests)
    }
}

router.get('/contests?',routeHandler.loadContestsList)

module.exports = router
