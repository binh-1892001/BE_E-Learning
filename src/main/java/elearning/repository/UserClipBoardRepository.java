package elearning.repository;

import elearning.model.UserClipboard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserClipBoardRepository extends JpaRepository<UserClipboard, Long> {
    boolean existsByPhone(String phone);

    void deleteByPhone(String phone);

}
