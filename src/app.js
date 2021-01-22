const path = require('path')
const express = require('express')

const app = express()

const cookieParser= require('cookie-parser')

port = 3000


const bodyParser = require('body-parser')

app.use(express.json())

app.get('/',(req,res)=>{
    res.send("Best of luck")
})

app.listen(port , ()=> {
    console.log('Starting Server on Port '+port )
})