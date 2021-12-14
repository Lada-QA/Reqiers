package reqres_objects;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class ResourceUser {
    int id;
    String name;
    String year;
    String color;
    @SerializedName("pantone_value")
    String pantoneValue;
}
