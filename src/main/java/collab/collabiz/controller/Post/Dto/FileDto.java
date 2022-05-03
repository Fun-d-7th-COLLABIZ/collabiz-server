package collab.collabiz.controller.Post.Dto;

import collab.collabiz.entity.File;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class FileDto {

    private Long postId;
    private String origFilename;
    private String filename;
    private String filepath;

    public File toEntity(){
        return File.builder()
                .postId(postId)
                .origFilename(origFilename)
                .filename(filename)
                .filepath(filepath)
                .build();
    }

    public FileDto(File entity){ //객체를 인자로 줘서 Dto로 변환하는 생성자
        this.postId = entity.getPostId();
        this.origFilename = entity.getOrigFilename();
        this.filename = entity.getFilename();
        this.filepath = entity.getFilepath();
    }
}
