package reqres_objects;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserLoginSuccessful {
    String email;
    String password;
}
