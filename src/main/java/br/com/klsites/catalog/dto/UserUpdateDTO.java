package br.com.klsites.catalog.dto;

import br.com.klsites.catalog.services.validation.UserUpdateValid;
import lombok.Data;

@UserUpdateValid
public class UserUpdateDTO extends UserDTO{
}