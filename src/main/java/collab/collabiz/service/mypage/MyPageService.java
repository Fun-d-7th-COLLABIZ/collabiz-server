package collab.collabiz.service.mypage;

import collab.collabiz.entity.Post;
import collab.collabiz.entity.mypage.MyOfferDto;
import collab.collabiz.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyPageService {
    private final PostRepository postRepository;

    public void myPost(MyOfferDto myOfferDto) {
        //포스트에서 현재 유저가 올린 포스트를 찾음
        Optional<Post> collaborations = postRepository.findByEmail(myOfferDto.getEmail());
        //해당 포스트의 정보를 리스트로 return
    }
}
