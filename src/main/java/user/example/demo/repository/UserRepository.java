package user.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import user.example.demo.entity.Userdata;

public interface UserRepository extends JpaRepository<Userdata, Long> {
}
