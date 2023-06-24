const {Connect,Disconnect} = require('../server/AppRestaurentBack')
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
  
exports.addRating = async(res,newRating)=>{
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