package br.com.klsites.catalog.services;

import br.com.klsites.catalog.dto.RoleDTO;
import br.com.klsites.catalog.dto.UserDTO;
import br.com.klsites.catalog.entities.Role;
import br.com.klsites.catalog.entities.User;
import br.com.klsites.catalog.repositories.RoleRepository;
import br.com.klsites.catalog.repositories.UserRepository;
import br.com.klsites.catalog.services.exceptions.DatabaseException;
import br.com.klsites.catalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;
    //private Page<User> product;

    @Transactional(readOnly = true)
    public Page<UserDTO> findAllPaged(Pageable pageable) {
        Page<User> product = repository.findAll(pageable);
        return product.map(UserDTO::new);
    }

    @Transactional(readOnly = true)
    public UserDTO findById(Long id) {
        Optional<User> obj = repository.findById(id);
        User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new UserDTO(entity);
    }

    @Transactional
    public UserDTO insert(UserDTO userDTO) {
        User entity = new User();
        copyDtoToEntity(userDTO, entity);
        entity = repository.save(entity);
        return new UserDTO(entity);

    }

    @Transactional
    public UserDTO update(Long id, UserDTO productDTO) {
        try {
            User entity = repository.getReferenceById(id);
            copyDtoToEntity(productDTO, entity);
            entity = repository.save(entity);
            return new UserDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found " + id);
        }
    }

    public void delete(Long id) {
         try {
             repository.deleteById(id);
         }catch (EmptyResultDataAccessException e){
             throw new ResourceNotFoundException("Id not found " + id);
         }catch (DataIntegrityViolationException e){
             throw new DatabaseException("Integrity violation");
         }
    }
    private void copyDtoToEntity(UserDTO dto, User entity){
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());

        entity.getRoles().clear();
        for(RoleDTO roleDTO : dto.getRoles()){
            Role role = roleRepository.getReferenceById(roleDTO.getId());
            entity.getRoles().add(role);
        }
    }
}
