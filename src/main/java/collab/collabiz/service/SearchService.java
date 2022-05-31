package collab.collabiz.service;

import collab.collabiz.entity.Keyword;
import collab.collabiz.entity.Post;
import collab.collabiz.entity.QKeyword;
import collab.collabiz.entity.QPost;
import collab.collabiz.repository.PostRepository;
import collab.collabiz.repository.PostRepositoryCustom;
import collab.collabiz.repository.PostRepositoryImpl;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class SearchService {

    private final PostRepositoryCustom postRepository;

    public List<Post> search(List<String> regionCondition, List<String> categoryCondition, List<String> keywordCondition, Pageable pageable){
        return postRepository.search(regionCondition, categoryCondition, keywordCondition, pageable);
    }

    //동적쿼리 - where 다중 파라미터 사용
    //입력값은 List<String> 지역조건, 카테고리조건,키워드 조건 (3개 리스트)





}
