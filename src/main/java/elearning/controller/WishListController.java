package elearning.controller;

import elearning.dto.CourseDto;
import elearning.exception.CustomException;
import elearning.model.Course;
import elearning.service.IWishListService;
import elearning.service.impl.WishListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/wish-list")
public class WishListController {
    @Autowired
    private IWishListService wishListService;
    @GetMapping()
    public ResponseEntity<String> addWishList(@RequestParam("idCourse") Long id) throws CustomException {
        wishListService.addWishList(id);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<Page<CourseDto>> getAll(@PageableDefault(size = 10,page = 0)Pageable pageable){
        return new ResponseEntity<>(wishListService.getWishListCurrentUser(pageable), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteWishList(@RequestParam("idCourse") Long id) throws CustomException {
        wishListService.deleteWishList(id);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

}
