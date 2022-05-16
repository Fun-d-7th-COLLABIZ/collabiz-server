package collab.collabiz.service;

import collab.collabiz.controller.Post.Dto.PostRequestDto;
import collab.collabiz.controller.Post.Dto.PostResponseDto;
import collab.collabiz.entity.Post;
import collab.collabiz.repository.KeywordRepository;
import collab.collabiz.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;

    public Post save(PostRequestDto postRequestDto) {
        return postRepository.save(postRequestDto.toEntity());

    }
}
