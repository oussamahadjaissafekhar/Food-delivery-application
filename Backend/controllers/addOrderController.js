const { addOrder } = require('../models/addOrderModel');

exports.addOrderController = async (req, res,Order,OrderItems) => {
    try{
        await addOrder(res,Order,OrderItems);
      }catch (error) {
        console.error('Error retrieving data:', error);
        res.write('{}');
    }
};