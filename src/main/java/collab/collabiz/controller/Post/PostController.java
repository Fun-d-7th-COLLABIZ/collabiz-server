package collab.collabiz.controller.Post;

import collab.collabiz.controller.Post.Dto.PostRequestDto;
import collab.collabiz.controller.Post.Dto.PostResponseDto;
import collab.collabiz.entity.Post;
import collab.collabiz.service.FileService;
import collab.collabiz.service.KeywordService;
import collab.collabiz.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final FileService fileService;
    private final KeywordService keywordService;

    @PostMapping(value = "/post",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}) //콜라보레이션 등록
    public ResponseEntity<PostResponseDto> post(@RequestPart PostRequestDto postRequestDto,
                                                @RequestPart MultipartFile[] files){
        Post post=postService.save(postRequestDto);
        fileService.uploadFiles(files, post.getId()); //파일 업로드
        keywordService.save(post,postRequestDto.getKeyword()); //키워드 저장

        PostResponseDto postResponseDto=new PostResponseDto(post);
        return new ResponseEntity(postResponseDto, HttpStatus.CREATED);

    }


}
