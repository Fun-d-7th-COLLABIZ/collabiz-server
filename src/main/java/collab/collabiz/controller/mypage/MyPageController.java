package collab.collabiz.controller.mypage;

import collab.collabiz.controller.Post.Dto.KeywordDto;
import collab.collabiz.controller.Post.Dto.PostResponseDto;
import collab.collabiz.entity.Member;
import collab.collabiz.entity.Post;
import collab.collabiz.entity.mypage.MyOfferResponseDto;
import collab.collabiz.repository.MemberRepository;
import collab.collabiz.repository.PostRepository;
import collab.collabiz.service.mypage.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class MyPageController {
    private final PostRepository postRepository;

    @GetMapping(value = "/myPage/myOffer/{memberId}")
    public ResponseEntity<MyOfferResponseDto> myOffer(@PathVariable Long memberId){

        List<Post> postRepositoryAll = postRepository.findAll();
        List<PostResponseDto> myPostList = new ArrayList<>();

        for(Post post : postRepositoryAll){
            if(post.getMember().getId().equals(memberId)){ //해당 포스트가 현재 유저의 것이면 list에 추가
                PostResponseDto postResponseDto = new PostResponseDto(post);
                myPostList.add(postResponseDto);
            }
        }

        return new ResponseEntity(myPostList, HttpStatus.CREATED);
    }
}
