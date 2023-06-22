const http = require('http');
const url = require('url'); 
const querystring = require('querystring');
const {Authentication ,insertUser ,Restaurants,RestaurantMenu} = require('./AppRestaurentBack');

const server = http.createServer(async (req, res) => {
  if (req.method === 'POST') {
    console.log('Client connected');
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
      if (pathname === '/connect') {
        const username = formData.username;
        const password = formData.password;
        console.log('Authentication request with username:', username);
        console.log('Authentication request with password:', password);
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
    }else {
      res.writeHead(404, { 'Content-Type': 'text/html' });
      res.write('<h1>404 Not Found</h1>');
      res.end();
    }
    });
  } else if(req.method === 'GET'){
    console.log("Client connected")
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




