package collab.collabiz.service;

import collab.collabiz.controller.Post.Dto.FileDto;
import collab.collabiz.entity.File;
import collab.collabiz.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileService {

    private final String uploadPath = System.getProperty("user.dir") ;
    private final FileRepository fileRepository;

    public void uploadFiles(MultipartFile[] files, Long id) {


        //저장할 경로
        String path="src/main/resources/files";

        //path에 디렉토리가 존재하지 않으면, 부모 디렉토리 포함 모든 디렉토리 생성
        java.io.File dir=new java.io.File(path);
        if (!dir.exists()){
            dir.mkdir(); //상위 디렉토리가 존재하지 않으면 그것까지 생성.
        }

        //파일각각에 대해서 forEach 실행
        for(MultipartFile file: files){
            try{
                //파일 확장자
                final String extension= StringUtils.getFilenameExtension(file.getOriginalFilename());

                //서버에 저장될 파일명
                final String saveName=getRandomString()+"."+extension;

                //업로드 경로와 파일명이 담긴 파일객체 생성
                java.io.File target= new java.io.File(uploadPath+"/"+path,saveName);

                //target에 해당하는 파일을 서버에 물리적으로 생성.
                file.transferTo(target);

                //테이블에 파일정보 저장하기 위해서 filedto객체에 정보담기
                FileDto fileDto=new FileDto();

                // fileDto에 게시글번호, 원본파일명, 서버파일명 저장후 DB에 저장! -> 빌더로 바꾸기
                fileDto.setPostId(id);
                fileDto.setOrigFilename(file.getOriginalFilename());
                fileDto.setFilename(saveName);
                fileDto.setFilepath(uploadPath+"/"+path);

                // Dto객체 -> entity로 변환후 저장 -> 저장된 엔티티로 생성된 id값을 포함하는 reponseDto를 리턴
                File entity = fileRepository.save(fileDto.toEntity());



            }catch (Exception e) {
                throw new RuntimeException(file.getOriginalFilename() + "failed save file");
            }
        }

    }

    private String getRandomString() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
