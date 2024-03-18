package user.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import user.example.demo.entity.Userdata;
import user.example.demo.repository.UserRepository;
import user.example.demo.dto.UserdataDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api") // localhost:8080/api/users json형태로 데이터가 와야됨
public class ApiController {

  @Autowired
  private UserRepository userRepository;

  @PostMapping("/users")
  public String createUser(@RequestBody UserdataDTO userDTO) {
    Userdata user = new Userdata();
    user.setTitle(userDTO.getTitle());
    user.setContent(userDTO.getContent());
    userRepository.save(user);
    return "User created successfully";
  }

  @GetMapping("/users")
  public List<UserdataDTO> getAllUsers() {
    List<Userdata> users = userRepository.findAll();
    return users.stream()
        .map(user -> new UserdataDTO(user.getId(), user.getTitle(), user.getContent()))
        .collect(Collectors.toList());
  }

  // PUT 요청을 통해 사용자 정보 업데이트
  @PutMapping("/users/{id}")
  public String updateUser(@PathVariable Long id, @RequestBody UserdataDTO updatedUserDTO) {
    Optional<Userdata> optionalUser = userRepository.findById(id);
    if (optionalUser.isPresent()) {
      Userdata user = optionalUser.get();
      user.setTitle(updatedUserDTO.getTitle());
      user.setContent(updatedUserDTO.getContent());
      userRepository.save(user);
      return "User updated successfully";
    } else {
      return "User not found";
    }
  }

  // DELETE 요청을 통해 사용자 정보 삭제
  @DeleteMapping("/users/{id}")
  public String deleteUser(@PathVariable Long id) {
    Optional<Userdata> optionalUser = userRepository.findById(id);
    if (optionalUser.isPresent()) {
      userRepository.deleteById(id);
      return "User deleted successfully";
    } else {
      return "User not found";
    }
  }

}

