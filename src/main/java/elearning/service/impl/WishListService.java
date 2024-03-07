package elearning.service.impl;

import elearning.dto.CourseDto;
import elearning.dto.base.BaseObjectDto;
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

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Component
public class WishListService implements IWishListService {

    @Autowired
    private IUserService userService;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private IUserRepository userRepository;

    @Override
    public void addWishList(BaseObjectDto dto) throws CustomException {
        if(Objects.isNull(dto.getId())){
            throw new CustomException("Course not found");
        }
        Users currentUser = userService.getCurrentUser();
        if(currentUser.getFavourite() != null && !currentUser.getFavourite().isEmpty()){
           for (Course e: currentUser.getFavourite()){
                if(e.getId().equals(dto.getId())){
                    throw new CustomException("Course is already on the wishlist");
                }
           }
        }
        Course course = courseRepository.findById(dto.getId()).orElseThrow(()-> new CustomException("Course not found"));
        currentUser.getFavourite().add(course);
        userRepository.save(currentUser);
    }

    @Override
    public void deleteWishList(Long idCourse) throws CustomException {
        Users currentUser = userService.getCurrentUser();
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
    public Page<CourseDto> getWishListByUserId(Long userId, Pageable pageable){
        Page<Course> courses = userRepository.getWistListByUserId(userId, pageable);
        return courses.map(CourseDto::new);
    }

    @Override
    public void deleteList(List<Long> dto) {
        Users currentUser = userService.getCurrentUser();
        Set<Course> favorite = currentUser.getFavourite();
        Set<Course> newFavorite = new HashSet<>();
        for(Course c: favorite){
            if(!dto.contains(c.getId())){
                newFavorite.add(c);
            }
        }
        currentUser.getFavourite().clear();
        currentUser.getFavourite().addAll(newFavorite);
        userRepository.save(currentUser);
    }
}
