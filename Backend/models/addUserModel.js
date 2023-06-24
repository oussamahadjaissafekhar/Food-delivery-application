const {Connect,Disconnect} = require('../server/AppRestaurentBack')
// Get the menu of a reataurent from database query
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
  // Get the menu of a reataurent from database
  exports.addUser = async(res,User)=>{
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