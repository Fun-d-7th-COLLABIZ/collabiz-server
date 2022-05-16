package collab.collabiz.entity;

import collab.collabiz.entity.enumtype.MemberImageType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.*;
import static lombok.AccessLevel.*;

/**
 * 회원 프로필 사진, 배너 사진
 *
 * by.seongcheol
 */
@Entity
@Getter
@NoArgsConstructor(access = PROTECTED)
public class MemberImage extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "member_image_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String uploadFileName; // 사용자가 올린 파일 이름
    private String storeFileName; // 서버 저장용 파일 이름
    private String storeFilePath; // 서버 내 파일 저장 위치
    private String storeFileExtension; // 파일 확장자

    @Enumerated(EnumType.STRING)
    private MemberImageType imageType; // 프로필 사진, 배너 사진
}