package elearning.service;

import elearning.dto.UserClipboardDto;
import elearning.exception.CustomException;
import elearning.model.UserClipboard;

import java.util.List;

public interface IUserClipboadService {
    void save(UserClipboardDto userClipboardDto) throws CustomException;
    List<UserClipboardDto> getAll();
}
