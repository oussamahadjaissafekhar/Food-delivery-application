const http = require('http');
const url = require('url'); 
const querystring = require('querystring');
const {AuthenticationController} = require('../controllers/signInController')
const {addUserController} = require('../controllers/addUserController')
const {addOrderController} = require('../controllers/addOrderController')
const {addRatingController} = require('../controllers/addRatingController')
const {getAllRestaurantsController} = require('../controllers/restaurentController');
const {getRestaurantMenuController} = require('../controllers/restaurentMenuController')
const server = http.createServer(async (req, res) => {
  if (req.method === 'POST') {
    console.log('Client connected with POST');
    let body = '';
    req.on('data', (chunk) => {
      body += chunk;
    });  
    req.on('end', async () => {
      // recuperer les cordonnees de la requete POST
      const formData = querystring.parse(body);  
      // verifer le service desirer a consulter 
      const requestUrl = url.parse(req.url, true); // Parse the request URL
      const pathname = requestUrl.pathname;
      if (pathname === '/authenticate') {
        const username = formData.username;
        const password = formData.password;
        if(username == undefined){
          username = null;
        }
        if(password == undefined){
          password = null;
        }
        const User = {
            username:username,
            password:password
          }
        AuthenticationController(req,res,User);  
    }else if(pathname === '/addUser'){
      console.log("add user")
      const formData = querystring.parse(body); 
      const User = JSON.parse(formData.user);
      addUserController(req,res,User)
    }else if(pathname === '/addOrder'){
      const parsedData = JSON.parse(body);
      const Order = JSON.parse(parsedData.order);
      const OrderItems = JSON.parse(parsedData.orderItems);
      addOrderController(req,res,Order,OrderItems)
    }else if(pathname === '/addRating'){
      console.log("add rating")
      const formData = querystring.parse(body); 
      Rating = {
        restaurant_id:formData.restaurant_id,
        user_id:formData.user_id,
        rating:formData.rating,
        review:formData.review
      }
      addRatingController(req,res,Rating)
    }else {
      res.writeHead(404, { 'Content-Type': 'text/html' });
      res.write('<h1>404 Not Found</h1>');
      res.end();
    }
    });
  } else if(req.method === 'GET'){
    console.log("Client connected with GET")
    const requestUrl = url.parse(req.url, true); // Parse the request URL
    if (requestUrl.pathname  === '/restaurent/getall') {
      getAllRestaurantsController(req,res)
    }else if (requestUrl.pathname  === '/getMenu') {
        // Extract the restaurant ID from the request URL parameters
      const restaurentId = requestUrl.query.restaurentId;
      getRestaurantMenuController(req,res,restaurentId);
    }else{
      res.writeHead(404, { 'Content-Type': 'text/html' });
      res.write('<h1>404 Not Found</h1>');
      res.end();
    }
  }else{
    res.writeHead(404, { 'Content-Type': 'text/html' });
    res.write('<h1>404 Not Found</h1>');
    res.end();
  }
})

server.listen(8080, () => {
  console.log('Server running on port 8080');
});




