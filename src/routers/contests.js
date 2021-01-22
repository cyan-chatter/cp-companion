const express = require('express')
const router = new express.Router()

const getContests = require('../../utils/contestsThrower')

const routeHandler = {
    loadContestsList : async (req,res)=>{
        const contests = await getContests()   
        res.send(contests)
    }
}

router.get('/contests',routeHandler.loadContestsList)

module.exports = router
