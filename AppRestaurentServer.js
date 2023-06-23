const http = require('http');
const url = require('url'); 
const querystring = require('querystring');
const {Authentication ,insertUser ,Restaurants,RestaurantMenu,addOrder} = require('./AppRestaurentBack');

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
        console.log('Authentication request with username:', username);
        console.log('Authentication request with password:', password);
        if(username == undefined){
          username = null;
        }
        if(password == undefined){
          password = null;
        }
        try{
          const user = {
            username:username,
            password:password
          }
          await Authentication(res,user);
        }catch (error) {
          console.error('Error retrieving data:', error);
          res.write('{}');
      }
    }else if(pathname === '/addUser'){
      // Add new user : Sign up
    }else if(pathname === '/addOrder'){
      const parsedData = JSON.parse(body);
      const Order = JSON.parse(parsedData.order);
      const OrderItems = JSON.parse(parsedData.orderItems);
      console.log("add new Order")
      // Add new order 
      console.log("parsedData : ",parsedData);
      console.log("Order : ",Order);
      console.log("Order items: ",OrderItems);
      try{
        await addOrder(res,Order);
      }catch (error) {
        console.error('Error retrieving data:', error);
        res.write('{}');
      }
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
      console.log("get restaurent all")
      try{
        await Restaurants(res);
      }catch (error) {
        console.error('Error retrieving data:', error);
        res.write('{}');
    }
    }else if (requestUrl.pathname  === '/getMenu') {
      console.log("get menu");
      const restaurentId = requestUrl.query.restaurentId; // Extract the restaurant ID from the request URL parameters
      console.log('Get menu for restaurant ID:', restaurentId);
      try{
        await RestaurantMenu(res,restaurentId);
      }catch (error) {
        console.error('Error retrieving data:', error);
        res.write('{}');
      }
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



