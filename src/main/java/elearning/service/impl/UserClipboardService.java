package elearning.service.impl;

import elearning.dto.UserClipboardDto;
import elearning.exception.CustomException;
import elearning.model.UserClipboard;
import elearning.repository.IUserRepository;
import elearning.repository.UserClipBoardRepository;
import elearning.service.IUserClipboadService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserClipboardService implements IUserClipboadService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private UserClipBoardRepository userClipBoardRepository;
    @Override
    public void save(UserClipboardDto userClipboardDto) throws CustomException {
        if(userRepository.existsByPhone(userClipboardDto.getPhone())){
            throw new CustomException("User is register and phone is saved");
        }
        if(userClipBoardRepository.existsByPhone(userClipboardDto.getPhone())){
            throw new CustomException("Phone is saved before");
        }
        UserClipboard entity = new UserClipboard();
        BeanUtils.copyProperties(userClipboardDto, entity);
        userClipBoardRepository.save(entity);
    }

    @Override
    public List<UserClipboardDto> getAll() {
        return userClipBoardRepository.findAll().stream().map(UserClipboardDto::new).collect(Collectors.toList());
    }
}
