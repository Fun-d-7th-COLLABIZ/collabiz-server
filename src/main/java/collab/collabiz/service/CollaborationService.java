package collab.collabiz.service;

import collab.collabiz.controller.Collaboration.dto.Ranking;
import collab.collabiz.entity.Collaboration;
import collab.collabiz.entity.Keyword;
import collab.collabiz.repository.CollaborationRepository;
import collab.collabiz.repository.KeywordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CollaborationService {

    private final CollaborationRepository collaborationRepository;
    private final KeywordRepository keywordRepository;

    /**
     * 콜라보 인기 순위 조회
     *
     * point가 높은 콜라보레이션 순서로 반환한다.
     */
    public List<Ranking> rankings() {

        // point가 높은 순으로 collaboration 조회
        Sort sort = Sort.by(Sort.Direction.DESC, "point");
        List<Collaboration> collaborations = collaborationRepository.findAll(sort);
        List<Ranking> rankings = new ArrayList<>(); // 반환할 dto



        for (Collaboration collaboration : collaborations) {
            // heart 수 가져오기
            String heartCount = collaborationRepository.getHeartCount(collaboration.getId());

            Ranking ranking = Ranking.builder()
                    .id(collaboration.getId())
                    .title(collaboration.getPost().getTitle())
                    .content(collaboration.getPost().getContent())
                    .heart(heartCount)
                    .view(Integer.toString(collaboration.getPost().getViews()))
                    .share(Integer.toString((int)(Math.random() * 100))) // 임의의 랜덤값으로 대체 추후 리펙토링
                    .endDate(collaboration.getPost().getRecruitEndDate())
                    .build();

            // 콜라보레이션 게시글 키워드 조회
            List<Keyword> keywords = keywordRepository.findAllByPost(collaboration.getPost());

            // 조회한 키워드를 응답하기 위한 ranking 객체에 추가
            List<String> keywordDto = new ArrayList<>();
            for (Keyword keyword : keywords) {
                keywordDto.add(keyword.getKeyword());
            }
            ranking.setKeywords(keywordDto);

            // rankings에  ranking dto 추가
            rankings.add(ranking);
        }

        return rankings;
    }
}
