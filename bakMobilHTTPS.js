const https = require('https');
const fs = require('fs');

const options = {
  key: fs.readFileSync('ca-key.pem'),
  cert: fs.readFileSync('ca-cert.pem')
};

const server = https.createServer(options, (req, res) => {
  if (req.method === 'GET') {
    console.log('Client connected');
    if (req.url === '/restaurent/getall') {
      res.writeHead(200, { 'Content-Type': 'application/json' });
      res.end(JSON.stringify([{ restaurentId: 1, restaurentName: 'Restaurant 1' }]));
    } else if (req.url === '/about') {
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
