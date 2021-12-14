package reqres_objects;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Users {
    int id;
    String email;
    @SerializedName("first_name")
    String firstName;
    @SerializedName("last_name")
    String lastName;
    String avatar;
}
