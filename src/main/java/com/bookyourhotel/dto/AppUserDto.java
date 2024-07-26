package com.bookyourhotel.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AppUserDto {
    private Long id;
    private String username;
    private String name;
    private String emailid;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String role;

}
