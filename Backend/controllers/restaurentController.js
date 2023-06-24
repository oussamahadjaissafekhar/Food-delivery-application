const { Restaurants } = require('../models/restaurantModel');

exports.getAllRestaurantsController = async (req, res) => {
    try{
        await Restaurants(res);
      }catch (error) {
        console.error('Error retrieving data:', error);
        res.write('{}');
    }
};