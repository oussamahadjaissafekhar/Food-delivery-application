const http = require('http');


const server = http.createServer((req, res) => {
  if (req.method === 'GET') {
    console.log('Client connected');
    if (req.url === '/restaurent/getall') {
      console.log("get restaurent all")
      res.writeHead(200, { 'Content-Type': 'application/json' });
      
      const restaurantData = [{
        restaurentId: 30,
        restaurentName: "String",
        restaurentLogo: 1,
        restaurentType: "String",
        rating: 3.5,
        restaurentPhone: "String",
        restaurentLongitude: "String",
        restaurentLatitude: "String",
        restaurentInstagrame: "String",
        restaurentFacebook: "String",
        restaurentImage: 1,
        listMenu: null
      }];
      
      const jsonData = JSON.stringify(restaurantData);
      
      res.write(jsonData);} else if (req.url === '/about') {
        console.log("About us");
      res.writeHead(200, { 'Content-Type': 'text/html' });
      res.write('<h1>About Us</h1>');
      res.end();
    } else {
      res.writeHead(404, { 'Content-Type': 'text/html' });
      res.write('<h1>404 Not Found</h1>');
      res.end();
    }
  }
});

server.listen(8080, () => {
  console.log('Server running on port 8080');
});