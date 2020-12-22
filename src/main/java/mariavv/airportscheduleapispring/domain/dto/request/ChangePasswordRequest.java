package mariavv.airportscheduleapispring.domain.dto.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.Size;

@Getter
public class ChangePasswordRequest {
    @Size(min = 2, max = 50, message = "Length must be between 2..50 characters")
    private final String name;
    @Size(min = 4, message = "Length must be 4 characters at least")
    private final String password;
    @Size(min = 4, message = "Length must be 4 characters at least")
    private final String newPassword;

    @JsonCreator
    public ChangePasswordRequest(@JsonProperty(value = "name") String name,
                                 @JsonProperty(value = "password") String password,
                                 @JsonProperty(value = "newPassword") String newPassword) {
        this.name = name;
        this.password = password;
        this.newPassword = newPassword;
    }
}
