import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.RestaurentApp.entity.Restaurant
import com.example.RestaurentApp.entity.user

@Entity(
    tableName = "Order",
    foreignKeys = [
        ForeignKey(entity = user::class, parentColumns = ["user_id"], childColumns = ["user_id"]),
        ForeignKey(entity = Restaurant::class, parentColumns = ["restaurant_id"], childColumns = ["restaurant_id"])
    ]
)
data class Order(
    @PrimaryKey val order_id: Int,
    val user_id: Int,
    val restaurant_id: Int,
    val delivery_address: String,
    val additional_notes: String,
    val total_price: Double,
    val status: String
)