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
// recuperer un utilisateur avec son username et password
function getUser(db,username,password) {
    return new Promise((resolve, reject) => {
      db('muser')
        .select()
        .where('userName' , '=' ,username)
        .where('password','=',password)
        .then((data) => {
          resolve(data); // Resolve the promise with the retrieved data
        })
        .catch((error) => {
          reject(error); // Reject the promise with the error
        });
    });
  }
// Verifier l'authentification d'un utilisateur
async function Authentication(res,user){
    const db = Connect();
    try {
      const jsonData = await getUser(db, user.username, user.password);
      if (jsonData == null) {
        res.write('{}');
      } else {
        const jsonString = JSON.stringify(jsonData);
        res.write(jsonString);
      }
    } catch (error) {
      console.error('Error retrieving data:', error);
      res.write('{}');
    } finally {
      Disconnect(db);
      res.end();
    }
}
// Ajout un utilisateur lors de sign up
function addUser(newRow){
  db('muser')
    .insert(newRow)
    .then(() => {
      console.log('Row inserted successfully');
      knex.destroy(); // Disconnect from the database
    })
    .catch((error) => {
      console.error('Error inserting row:', error);
      knex.destroy(); // Disconnect from the database
    });
  }
function insertUser(){

}

function getAllRestaurents(db){
  return new Promise((resolve, reject) => {
  db('restaurant')
    .select()
    .then((data) => {
      resolve(data); // Resolve the promise with the retrieved data
    })
    .catch((error) => {
      reject(error); // Reject the promise with the error
    });
  });
}
async function Restaurants(res){
  const db = Connect();
    try {
      const jsonData = await getAllRestaurents(db);
      if (jsonData == null) {
        res.write('{}');
      } else {
        const jsonString = JSON.stringify(jsonData);
        res.write(jsonString);
      }
    } catch (error) {
      console.error('Error retrieving data:', error);
      res.write('{}');
    } finally {
      Disconnect(db);
      res.end();
    }
}

function getRestaurentMenu (db,restaurentId){
  return new Promise((resolve,reject) => {
    db('menuitem')
      .select()
      .where('restaurentId' , '=' ,restaurentId)
      .then((data) => {
        resolve(data); // Resolve the promise with the retrieved data
      })
      .catch((error) => {
        reject(error); // Reject the promise with the error
      });
  })
}
async function RestaurantMenu(res,restaurentId){
const db = Connect();
try {
  const jsonData = await getRestaurentMenu(db,restaurentId);
  if (jsonData == null) {
    res.write('{}');
  } else {
    const jsonString = JSON.stringify(jsonData);
    res.write(jsonString);
  }
} catch (error) {
  console.error('Error retrieving data:', error);
  res.write('{}');
} finally {
  Disconnect(db);
  res.end();
}
}
module.exports = {
    Authentication,
    insertUser,
    Restaurants,
    RestaurantMenu
  };


