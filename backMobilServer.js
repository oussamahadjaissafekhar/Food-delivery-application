const http = require('http');
const knex = require('knex');
const url = require('url');

const db = knex({
    client: 'mysql',
    connection: {
      host: 'localhost',
      user: 'root',
      password: '',
      database: 'projetmobil',
    },
  });
 
// check if the connection established  
  db.raw('SELECT 1')
  .then(() => {
    console.log('Connection to the database successful!');
    // Perform other operations
  })
  .catch((error) => {
    console.error('Error connecting to the database:', error);
  });
const server = http.createServer((req, res) => {
  if (req.method === 'GET') {
    console.log('Client connected');
    const requestUrl = url.parse(req.url, true); // Parse the request URL
    if (requestUrl.pathname  === '/restaurent/getall') {
      console.log("get restaurent all")
      res.writeHead(200, { 'Content-Type': 'application/json' });
      db('restaurant')
      .select()
      .then((results) => {
        console.log('Retrieved data:', results);
        const jsonData = JSON.stringify(results);
        res.write(jsonData);
        res.end();
      })
      .catch((err) => {
        console.error('Error retrieving data:', err);
        res.write('<h1>404 Not Found</h1>');
        res.end();
      });
}
    else if (requestUrl.pathname  === '/getMenu') {
      console.log("get menu");
      const restaurentId = requestUrl.query.restaurentId; // Extract the restaurant ID from the request URL parameters
      console.log('Get menu for restaurant ID:', restaurentId);
      console.log(typeof(restaurentId))
      db('menuitem')
      .select()
      .where('restaurentId' , '=' ,restaurentId)
      .then((results) => {
        console.log('Retrieved data:', results);
        const jsonData = JSON.stringify(results);
        res.write(jsonData);
        res.end();
      })
      .catch((err) => {
        console.error('Error retrieving data:', err);
        res.write('<h1>404 Not Found</h1>');
        res.end();
      });
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