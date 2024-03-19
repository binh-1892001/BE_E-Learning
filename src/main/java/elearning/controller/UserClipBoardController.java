package elearning.controller;

import elearning.dto.UserClipboardDto;
import elearning.exception.CustomException;
import elearning.service.IUserClipboadService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/user-clipboard")
@RestController
public class UserClipBoardController {
    @Autowired
    private IUserClipboadService userClipboadService;

    @Operation(summary = "Add user type phone but not register")
    @PostMapping()
    public ResponseEntity<String> save(@RequestBody @Valid UserClipboardDto userClipboardDto) throws CustomException {
        userClipboadService.save(userClipboardDto);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    @Operation(summary = "Get all user type phone but not register")
    @GetMapping("/getAll")
    public ResponseEntity<List<UserClipboardDto>> getAll(){
        return new ResponseEntity<>(userClipboadService.getAll(), HttpStatus.OK);
    }
}
