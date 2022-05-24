package collab.collabiz.service.mypage;

import collab.collabiz.controller.Post.Dto.KeywordDto;
import collab.collabiz.entity.Member;
import collab.collabiz.entity.Post;
import collab.collabiz.entity.mypage.MyOfferDto;
import collab.collabiz.repository.MemberRepository;
import collab.collabiz.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyPageService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    public void myPost(MyOfferDto myOfferDto) {
        //포스트에서 현재 유저가 올린 포스트를 찾음
        Optional<Member> findMember = memberRepository.findByEmail(myOfferDto.getEmail());
        List<Post> findPost = postRepository.findAll();

        Member member = findMember.get();
        List<Post> postList = new ArrayList<>();

        for(Post post: findPost) {
            if(post.getMember().equals(member)){


                postList.add(post); //해당 유저가 쓴 글이면 추가
            }
        }

        //해당 포스트의 정보를 리스트로 return
    }
}
