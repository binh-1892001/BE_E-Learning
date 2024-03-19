package elearning.repository;

import elearning.model.Course;
import elearning.model.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<Users,Long> {
    Optional<Users> findUsersByPhone(String phone);

    boolean existsByPhone(String phone);

    @Query("select  u from Users u WHERE (LOWER(u.fullName) LIKE LOWER(CONCAT('%', :name, '%')) OR :name = '' OR :name is null ) AND (u.phone = :phone OR :phone = '' or :phone is null)")
    Page<Users> findUsersByFullNameAndPhone(String name, String phone, Pageable pageble);

    @Query("select u.favourite from Users u where u.id = :userId")
    Page<Course> getWistListByUserId(Long userId, Pageable pageable);

}
