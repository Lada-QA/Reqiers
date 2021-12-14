package reqres_objects;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UnregisterUser {
    String email;
}
