package elearning.service.impl;

import elearning.dto.CourseDto;
import elearning.exception.CustomException;
import elearning.model.Course;
import elearning.model.Users;
import elearning.repository.CourseRepository;
import elearning.repository.IUserRepository;
import elearning.service.CourseService;
import elearning.service.IUserService;
import elearning.service.IWishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
public class WishListService implements IWishListService {

    @Autowired
    private IUserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public void addWishList(Long idCourse) throws CustomException {
        Users currentUser = userService.getCurrentUser();
        if(currentUser.getFavourite() != null && !currentUser.getFavourite().isEmpty()){
           for (Course e: currentUser.getFavourite()){
                if(e.getId().equals(idCourse)){
                    throw new CustomException("Cource is already on the wishlist");
                }
           }
        }
        Course course = courseRepository.findById(idCourse).orElseThrow(()-> new CustomException("Course not found"));
        currentUser.getFavourite().add(course);
        userRepository.save(currentUser);
    }

    @Override
    public void deleteWishList(Long idCourse) throws CustomException {
        Users currentUser = userService.getCurrentUser();
        Course course = courseRepository.findById(idCourse).orElseThrow(()-> new CustomException("Course not found"));
        int sizeFavorite = currentUser.getFavourite().size();
        for(Course c: currentUser.getFavourite()){
            if(c.getId().equals(idCourse)){
                currentUser.getFavourite().remove(c);
                break;
            }
        }
        if(sizeFavorite == currentUser.getFavourite().size()){
            throw new CustomException("Course not in User wishlist");
        }
        userRepository.save(currentUser);
    }

    @Override
    public Page<CourseDto> getWishListCurrentUser(Pageable pageable) {
        Users currentUser = userService.getCurrentUser();
        return getWishListByUserId(currentUser.getId(), pageable);
    }
    private Page<CourseDto> getWishListByUserId(Long userId, Pageable pageable){
        Page<Course> courses = userRepository.getWistListByUserId(userId, pageable);
        return courses.map(CourseDto::new);
    }
}
