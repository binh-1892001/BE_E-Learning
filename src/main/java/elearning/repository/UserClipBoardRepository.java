package elearning.repository;

import elearning.model.UserClipboard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserClipBoardRepository extends JpaRepository<UserClipboard, Long> {
    boolean existsByPhone(String phone);
}
