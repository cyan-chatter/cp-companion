const path = require('path')
const express = require('express')
const app = express()
app.use(express.json())

const cookieParser= require('cookie-parser')
const bodyParser = require('body-parser')

port = 3000

const contests = require('./routers/contests')
app.use(contests)

app.get('/',(req,res)=>{
    res.send("Best of luck")
})

app.listen(port , ()=> {
    console.log('Starting Server on Port '+port )
})