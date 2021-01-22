const path = require('path')
const express = require('express')
const app = express()
app.use(express.json())

const cookieParser= require('cookie-parser')
const bodyParser = require('body-parser')

const contests = require('./routers/contests')
app.use(contests)

app.get('/',(req,res)=>{
    res.send("Code Companion")
})
const port = process.env.PORT || 3000
app.listen(port, ()=> {
    console.log('Starting Server on Port '+ port)
})