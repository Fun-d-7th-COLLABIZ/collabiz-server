package collab.collabiz.controller.mypage;

import collab.collabiz.controller.Post.Dto.PostResponseDto;
import collab.collabiz.controller.mypage.dto.MyOfferResponseDto;
import collab.collabiz.service.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;

    @GetMapping(value = "/myPage/myOffer/{memberId}")
    public ResponseEntity<MyOfferResponseDto> myOffer(@PathVariable Long memberId){

        List<PostResponseDto> myPostList = myPageService.myPost(memberId);
        return new ResponseEntity(myPostList, HttpStatus.CREATED);
    }
}
