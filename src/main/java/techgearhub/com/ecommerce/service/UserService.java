package techgearhub.com.ecommerce.service;

import java.util.List;
import techgearhub.com.ecommerce.dto.UserDTO;

public interface UserService {
	List<UserDTO> getAllUsers();
	List<UserDTO> getDeactivatedUsers(); 
    UserDTO registerUser(UserDTO userDTO);
    UserDTO login(String email, String password);
    UserDTO getUserById(Long id);
    UserDTO getUserByEmail(String email);
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
}