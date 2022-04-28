package collab.collabiz.service;

import collab.collabiz.controller.Post.Dto.KeywordDto;
import collab.collabiz.entity.Post;
import collab.collabiz.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class KeywordService {

    private final KeywordRepository keywordRepository;

    public List<String> save(Post post, List<String> keywordList) {

        List<String> KeywordList = new ArrayList<>();

        for(String keyword: keywordList) {
            KeywordDto keywordDto=new KeywordDto();
            keywordDto.setKeyword(keyword);
            keywordDto.setPost(post);

            keywordRepository.save(keywordDto.toEntity());
            KeywordList.add(keyword);
        }

        return KeywordList;
    }
}
