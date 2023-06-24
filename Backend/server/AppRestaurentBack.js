const knex = require('knex');
// connecter a la base de donnee
function Connect(){
    const db = knex({
      client: 'mysql',
      connection: {
        host: 'mysql-bossama.alwaysdata.net',
        user: 'bossama_projetmb',
        password: 'projetmb',
        database: 'bossama_projettdm',
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
    return db;
}
// Disconnect from the database
function Disconnect(db){
    db.destroy()
    .then(() => {
      console.log('Disconnected from the database');
    })
    .catch((err) => {
      console.error('Error disconnecting from the database:', err);
    });
}
module.exports = {
  Connect,
  Disconnect,
  };


