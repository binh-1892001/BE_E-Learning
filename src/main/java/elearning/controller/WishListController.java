package elearning.controller;

import elearning.dto.CourseDto;
import elearning.dto.base.BaseObjectDto;
import elearning.exception.CustomException;
import elearning.service.IWishListService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/wish-list")
public class WishListController {
    @Autowired
    private IWishListService wishListService;

    @Operation(summary = "Add one course to user wishlist")
    @PostMapping ()
    public ResponseEntity<String> addWishList(@RequestBody BaseObjectDto dto) throws CustomException {
        wishListService.addWishList(dto);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @Operation(summary = "Get wishlist current user")
    @GetMapping("/get-by-current-user")
    public ResponseEntity<Page<CourseDto>> getByCurrentId(@PageableDefault(size = 10,page = 0)Pageable pageable){
        return new ResponseEntity<>(wishListService.getWishListCurrentUser(pageable), HttpStatus.OK);
    }

    @Operation(summary = "Get wishlist user by userId")
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/get-by-userId/{id}")
    public ResponseEntity<Page<CourseDto>> getByUserId(@PathVariable Long id, @PageableDefault(size = 10,page = 0)Pageable pageable){
        return new ResponseEntity<>(wishListService.getWishListByUserId(id, pageable), HttpStatus.OK);
    }

    @Operation(summary = "remove course from current user wishlist")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteWishList(@PathVariable("id") Long id) throws CustomException {
        wishListService.deleteWishList(id);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

    @Operation(summary = "remove list course from current user wishlist")
    @DeleteMapping("/delete-list")
    public ResponseEntity<String> deleteList(@RequestParam List<Long> id){
        wishListService.deleteList(id);
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }

}
