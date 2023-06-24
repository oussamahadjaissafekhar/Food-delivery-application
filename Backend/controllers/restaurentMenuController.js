const { RestaurantMenu } = require('../models/restaurentMenuModel');

exports.getRestaurantMenuController = async (req, res,restaurentId) => {
    try{
        await RestaurantMenu(res,restaurentId);
      }catch (error) {
        console.error('Error retrieving data:', error);
        res.write('{}');
      }
};