package collab.collabiz.service;

import collab.collabiz.controller.Post.Dto.PostResponseDto;
import collab.collabiz.entity.Post;
import collab.collabiz.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MyPageService {
    private final PostRepository postRepository;

    public List<PostResponseDto> myPost(Long memberId) {
        List<Post> postRepositoryAll = postRepository.findAll();
        List<PostResponseDto> myPostList = new ArrayList<>();

        for(Post post : postRepositoryAll){
            if(post.getMember().getId().equals(memberId)){ //해당 포스트가 현재 유저의 것이면 list에 추가
                PostResponseDto postResponseDto = new PostResponseDto(post);
                myPostList.add(postResponseDto);
            }
        }

        return myPostList;
    }
}
