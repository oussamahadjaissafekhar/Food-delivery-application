const {Connect,Disconnect} = require('../server/AppRestaurentBack')
function insertOrder (db,order, orderItems) {
    return new Promise((resolve, reject) => {
      db.transaction((trx) => {
        trx('morder')
          .insert(order)
          .then(([orderId]) => {
            const orderItemsArray = Array.isArray(orderItems) ? orderItems : [orderItems];
            const orderItemsWithOrderId = [];
              orderItems.map((item) => {
                const orderItemWithOrderId = {
                  ...item,
                  order_id: orderId,
                };
                orderItemsWithOrderId.push(orderItemWithOrderId);
              });
  
            return trx('orderitem').insert(orderItemsWithOrderId);
          })
          .then(() => {
            trx.commit();
            resolve();
          })
          .catch((error) => {
            trx.rollback();
            reject(error);
          });
      });
    });
  };
  // insert the order in database with it's item
exports.addOrder = async(res,order,orderItems)=>{
    const db = Connect();
    try {
      const jsonData = await insertOrder(db,order,orderItems);
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