package user.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import user.example.demo.entity.Userdata;
import user.example.demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api") // localhost:8080/api/users json형태로 데이터가 와야됨
public class ApiController {

  @Autowired
  private UserRepository userRepository;

  @PostMapping("/users")
  public String createUser(@RequestBody Userdata user) {
    userRepository.save(user);
    return "User created successfully";
  }

  @GetMapping("/users")
  public List<Userdata> getAllUsers() {
    return userRepository.findAll();
  }

  // PUT 요청을 통해 사용자 정보 업데이트
  @PutMapping("/users/{id}")
  public String updateUser(@PathVariable Long id, @RequestBody Userdata updatedUser) {
    Optional<Userdata> optionalUser = userRepository.findById(id);
    if (optionalUser.isPresent()) {
      Userdata user = optionalUser.get();
      // 업데이트할 사용자의 정보를 새로운 데이터로 교체
      user.setTitle(updatedUser.getTitle());
      user.setContent(updatedUser.getContent());
      // 업데이트된 사용자 정보 저장
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

