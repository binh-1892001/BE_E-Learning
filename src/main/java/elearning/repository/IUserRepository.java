package elearning.repository;

import elearning.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<Users,Long> {
    Optional<Users> findUsersByUsername(String username);

    boolean existsByUsername(String username);

    @Query("select  u from Users u where  LOWER(u.fullName) LIKE LOWER(CONCAT('%', :name, '%')) AND (u.phone = :phone Or :phone = '')")
    Page<Users> findUsersByFullNameAndPhone(String name, String phone, Pageable pageble);

}
