const { addUser } = require('../models/addUserModel');

exports.addUserController = async (req, res,User) => {
    try{
        await addUser(res,User);
      }catch (error) {
        console.error('Error retrieving data:', error);
        res.write('{}');
      }
};