package br.com.klsites.catalog.dto;

import lombok.Data;

@Data
public class UserInsertDTO extends UserDTO{
    private String password;

    UserInsertDTO(){
        super();
    }
}
