const { addRating } = require('../models/addRatingModel');

exports.addRatingController = async (req, res,Rating) => {
    try{
        await addRating(res,Rating);
    }catch (error) {
        console.error('Error retrieving data:', error);
        res.write('{}');
    }
};