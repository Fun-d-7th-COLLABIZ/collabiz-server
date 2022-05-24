package collab.collabiz.controller.mypage;

import collab.collabiz.entity.mypage.MyOfferResponseDto;
import collab.collabiz.service.mypage.MyPageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MyPageController {
    private final MyPageService myPageService;

    @PostMapping(value = "/myPage/myOffer")
    public ResponseEntity<MyOfferResponseDto> myOffer(){

        return ResponseEntity.ok().build();
    }
}
