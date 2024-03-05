package elearning.controller;

import elearning.dto.UserClipboardDto;
import elearning.exception.CustomException;
import elearning.service.IUserClipboadService;
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

    @PostMapping()
    public ResponseEntity<String> save(@RequestBody @Valid UserClipboardDto userClipboardDto) throws CustomException {
        userClipboadService.save(userClipboardDto);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UserClipboardDto>> getAll(){
        return new ResponseEntity<>(userClipboadService.getAll(), HttpStatus.OK);
    }
}
