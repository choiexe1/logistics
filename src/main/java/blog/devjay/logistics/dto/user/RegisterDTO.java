package blog.devjay.logistics.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegisterDTO {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]+$")
    private String username;
    @NotBlank
    private String password;
}
