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
        const jsonString = JSON.stringify(jsonData[0]);
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
function insertUser(db,User){
  return new Promise((resolve, reject) => {
    db('muser')
      .insert(User)
      .then(([insertedUserId]) => { // Destructure the inserted order ID
        return db('muser').where('userId', insertedUserId).first(); // Retrieve the inserted order by ID
      })
      .then(insertedUser => {
        resolve(insertedUser); // Resolve the promise with the inserted order
      })
      .catch((error) => {
        reject(error); // Reject the promise with the error
      });
  });
}
async function addUser(res,User){
  const db = Connect();
  try {
    const jsonData = await insertUser(db,User);
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
// Get all the reataurents from database query
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
// Get all the reataurents from database
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
// Get the menu of a reataurent from database query
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
// Get the menu of a reataurent from database
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
// insert the order in database with it's item query
function insertOrder (db,order, orderItems) {
  return new Promise((resolve, reject) => {
    db.transaction((trx) => {
      trx('morder')
        .insert(order)
        .then(([orderId]) => {
          const orderItemsArray = Array.isArray(orderItems) ? orderItems : [orderItems];
          const orderItemsWithOrderId = [];
            orderItems.map((item) => {
              const orderItemWithOrderId = {
                ...item,
                order_id: orderId,
              };
              orderItemsWithOrderId.push(orderItemWithOrderId);
            });

          return trx('orderitem').insert(orderItemsWithOrderId);
        })
        .then(() => {
          trx.commit();
          resolve();
        })
        .catch((error) => {
          trx.rollback();
          reject(error);
        });
    });
  });
};

// insert the order in database with it's item
async function addOrder(res,order,orderItems){
  const db = Connect();
  try {
    const jsonData = await insertOrder(db,order,orderItems);
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


function insertRating(db,newRating){
  return new Promise((resolve,reject) => {
  db('rating')
    .insert(newRating)
    .then(() => {
      // Proceed to calculate the average rating and update the restaurant table
      return db('rating')
        .where({ restaurant_id: newRating.restaurant_id })
        .avg('rating as avgRating');
    })
    .then((result) => {
      const averageRating = result[0].avgRating;
      console.log('Average rating:', averageRating);

      // Proceed to update the average rating in the restaurant table
      return db('restaurant')
        .where({ restaurentId: newRating.restaurant_id })
        .update({ rating: averageRating });
    })
    .then((rowCount) => {
      resolve(rowCount); // Resolve the promise with the retrieved data
    })
    .catch((error) => {
      reject(error); // Reject the promise with the error
    });
    })
  }

  async function addRating(res,newRating){
    const db = Connect()
    try{
      const jsonData = await insertRating(db,newRating);
      if (jsonData == null) {
        res.write('{}');
      } else {
        const jsonString = JSON.stringify(jsonData);
        res.write(jsonString);
      }
    }catch(error){
      console.error('Error retrieving data:', error);
      res.write('{}');
    }finally{
      Disconnect(db)
      res.end()
    }
  }

module.exports = {
    Authentication,
    addUser,
    Restaurants,
    RestaurantMenu,
    addOrder,
    addRating
  };


