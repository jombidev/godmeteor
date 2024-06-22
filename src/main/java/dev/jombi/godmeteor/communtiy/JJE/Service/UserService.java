package dev.jombi.godmeteor.communtiy.JJE.Service;

import dev.jombi.godmeteor.communtiy.JJE.Entity.User;
import dev.jombi.godmeteor.communtiy.SYS.dto.UserDto;
import dev.jombi.godmeteor.communtiy.SYS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    }
    public UserDto getUserById(Long id) {
        return userRepository.findById(id).map(this::convertToDto).orElse(null);
    }
    public UserDto createUser(UserDto userDto){
        User user = convertToEntity(userDto);
        User savedUser = userRepository.save(user);
        return convertToDto(savedUser);
    }
    public UserDto updateUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userDto.getName());
        User updatedUser = userRepository.save(user);
        return convertToDto(updatedUser);
    }
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    private UserDto convertToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        return userDto;
    }
    private User convertToEntity(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        return user;
    }
}
