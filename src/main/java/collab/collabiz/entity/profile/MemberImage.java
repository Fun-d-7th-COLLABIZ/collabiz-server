package collab.collabiz.entity.profile;

import lombok.Data;

import java.util.List;

@Data
public class MemberImage {
    private Long id;
    private String imageName;
    private UploadFile attachFile;
    private List<UploadFile> imageFiles;
}
