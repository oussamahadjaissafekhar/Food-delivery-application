const { Authentication } = require('../models/signInModel');

exports.AuthenticationController = async (req, res,User) => {
    try{
        await Authentication(res,User);
      }catch (error) {
        console.error('Error retrieving data:', error);
        res.write('{}');
    }
};