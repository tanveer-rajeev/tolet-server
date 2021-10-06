package com.tolet.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Builder
public class UserDTO {

    private String username;
    private String password;
}
