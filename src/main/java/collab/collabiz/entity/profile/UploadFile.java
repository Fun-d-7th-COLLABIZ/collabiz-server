package collab.collabiz.entity.profile;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UploadFile {
    //고객이 업로드한 파일명
    private String uploadFileName;
    //서버 내부에서 관리하는 파일명 -> 각 고객이 올린 파일명이 같을 수도 있기 때문
    private String storeFileName;
}
