package br.com.klsites.catalog.dto;

import br.com.klsites.catalog.entities.Role;
import lombok.Data;

import java.io.Serializable;

@Data
public class RoleDTO implements Serializable {
    private Long id;
    private String authority;

    public RoleDTO(){
    }

    public RoleDTO(Long id, String authority) {
        this.id = id;
        this.authority = authority;
    }
    public RoleDTO(Role role) {
        id = role.getId();
        authority = role.getAuthority();
    }
}
