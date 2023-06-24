const {Connect,Disconnect} = require('../server/AppRestaurentBack')
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
  exports.RestaurantMenu =async (res,restaurentId)=>{
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