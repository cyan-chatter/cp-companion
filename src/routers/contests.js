const express = require('express')
const router = new express.Router()

const getContests = require('../../utils/contestsThrower')

const routeHandler = {
    loadContestsList : async (req,res)=>{
        var page = req.params.page
        const contests = await getContests(page)   
        res.send(contests)
    }
}

router.get('/contests/:page',routeHandler.loadContestsList)

module.exports = router
