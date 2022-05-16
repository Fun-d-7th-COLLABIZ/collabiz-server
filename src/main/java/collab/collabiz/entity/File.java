package collab.collabiz.entity;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class File { //파일 엔티티 클래스

    //파일id-PK
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long postId;

    @Column(nullable = false)
    private String origFilename; //원본 파일명

    @Column(nullable = false)
    private String filename; //서버에 저장될 파일명

    @Column(nullable = false)
    private String filepath;

    @Builder
    public File(Long postId,String origFilename,String filename,String filepath){
        this.postId=postId;
        this.origFilename=origFilename;
        this.filename=filename;
        this.filepath=filepath;
    }

}
