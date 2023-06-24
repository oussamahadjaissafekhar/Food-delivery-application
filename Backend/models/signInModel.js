const {Connect,Disconnect} = require('../server/AppRestaurentBack')
// Get the menu of a reataurent from database query
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
exports.Authentication = async(res,user)=>{
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