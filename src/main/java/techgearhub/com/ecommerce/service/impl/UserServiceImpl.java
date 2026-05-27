package techgearhub.com.ecommerce.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import techgearhub.com.ecommerce.dto.UserDTO;
import techgearhub.com.ecommerce.mapper.UserMapper;
import techgearhub.com.ecommerce.model.User;
import techgearhub.com.ecommerce.repository.UserRepository;
import techgearhub.com.ecommerce.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    
    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .filter(User::isActive)
                .map(userMapper::toDTO)
                .toList();
    }

    @Override
    public List<UserDTO> getDeactivatedUsers() {
        return userRepository.findAll().stream()
                .filter(user -> !user.isActive())
                .map(userMapper::toDTO)
                .toList();
    }
    
    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.email()).isPresent()) {
            throw new RuntimeException("Errore: Un utente con questa email esiste già!");
        }
        
        User user = userMapper.toEntity(userDTO);
        user.setActive(true); 
        
        User savedUser = userRepository.save(user);
        return userMapper.toDTO(savedUser);
    }
    
    @Override
    public UserDTO login(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Credenziali non valide: utente non trovato."));
        
        if (!user.isActive()) {
            throw new RuntimeException("Questo account è stato disattivato o rimosso. Contatta l'assistenza.");
        }
        
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Credenziali non valide: password errata.");
        }
        
        return userMapper.toDTO(user);
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Utente non trovato con ID: " + id));
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Utente non trovato con email: " + email));
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Impossibile aggiornare: Utente non trovato con ID: " + id));
        
        existingUser.setName(userDTO.name());
        existingUser.setEmail(userDTO.email());
        
        User updatedUser = userRepository.save(existingUser);
        return userMapper.toDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.findById(id).ifPresent(user -> {
            user.setActive(false);
            userRepository.save(user);
        });
    }
}