package collab.collabiz.controller.Profile.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadFile {
    //고객이 파일명
    private String uploadFileName;
    //서버 파일명
    private String storeFileName;
}
