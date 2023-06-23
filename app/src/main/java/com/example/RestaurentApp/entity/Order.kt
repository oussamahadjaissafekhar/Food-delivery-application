import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.RestaurentApp.entity.Restaurant
import com.example.RestaurentApp.entity.user

@Entity(
    tableName = "Order",

)
data class Order(
    val user_id: Int,
    val restaurant_id: Int,
    val delivery_address: String,
    val additional_notes: String,
    val total_price: Double,
    val status: String
)