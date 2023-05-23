const http = require('http');
const menuData = [{
    idMenu : 1,
    nom : "Cheese Burger",
    image : 2131165314,
    prix : 150,
    descriptif : "Cheese Burger"
  }]
const restaurantData = [{
    restaurentId: 30,
    restaurentName: "String",
    restaurentLogo: "https://d1csarkz8obe9u.cloudfront.net/posterpreviews/restaurant-logo-design-template-8b254275713d14eedb860550dc59effa_screen.jpg?ts=1601244223",
    restaurentType: "String",
    rating: 3.5,
    restaurentPhone: "String",
    restaurentLongitude: "String",
    restaurentLatitude: "String",
    restaurentInstagrame: "String",
    restaurentFacebook: "String",
    restaurentImage: "https://th.bing.com/th/id/R.b9c3f51cfcdc1167d7c35ea0532387f8?rik=rvHW1O5s2nnz%2fg&riu=http%3a%2f%2fbitsofdays.com%2fwp-content%2fuploads%2f2018%2f08%2fDefine-the-concept-of-your-restaurant.jpg&ehk=jjq9ctwaN%2fmVfqikvBYHQvABkagJduNC%2bxzDaTa4ZlQ%3d&risl=&pid=ImgRaw&r=0",
    listMenu: menuData
  },
  {
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
    restaurentImage: "https://th.bing.com/th/id/OIP.4CbsLtHx_qBCtg4voy0jqAHaE8?pid=ImgDet&rs=1",
    listMenu: menuData
  }];

const server = http.createServer((req, res) => {
  if (req.method === 'GET') {
    console.log('Client connected');
    if (req.url === '/restaurent/getall') {
      console.log("get restaurent all")
      res.writeHead(200, { 'Content-Type': 'application/json' });
      const jsonData = JSON.stringify(restaurantData);
      res.write(jsonData);
      res.end();
}
    else if (req.url === '/about') {
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