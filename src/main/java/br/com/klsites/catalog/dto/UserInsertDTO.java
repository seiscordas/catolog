package br.com.klsites.catalog.dto;

import br.com.klsites.catalog.services.validation.UserInsertValid;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@UserInsertValid
public class UserInsertDTO extends UserDTO{
    @Getter
    @Setter
    private String password;
    UserInsertDTO(){
        super();
    }
}