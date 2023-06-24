const {Connect,Disconnect} = require('../server/AppRestaurentBack')
exports.Restaurants=async (res)=>{
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
