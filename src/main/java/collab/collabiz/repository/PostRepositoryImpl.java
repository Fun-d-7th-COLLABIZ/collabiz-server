package collab.collabiz.repository;

import collab.collabiz.entity.Post;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static collab.collabiz.entity.QKeyword.keyword1;
import static collab.collabiz.entity.QPost.post;

public class PostRepositoryImpl implements PostRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public PostRepositoryImpl(EntityManager em){
        this.queryFactory=new JPAQueryFactory(em);
    }

    @Override
    public List<Post> search(List<String> regionCondition, List<String> categoryCondition, List<String> keywordCondition, Pageable pageable){
        return queryFactory
                .select(keyword1.post)
                .from(keyword1)
                .join(keyword1.post,post)
                .where(isValid(regionCondition, categoryCondition, keywordCondition))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


    }

    private BooleanExpression isValid(List<String> regionCondition, List<String> categoryCondition, List<String> keywordCondition){
        return regionEq(regionCondition).and(CategoryEq(categoryCondition).and(keywordEq(keywordCondition)));
    }

    private BooleanExpression keywordEq(List<String> keywordCondition){
        if (keywordCondition==null){
            return null;

        }
        return keyword1.keyword.in(keywordCondition);
    }

    private BooleanExpression regionEq(List<String> regionCondition) {
        if (regionCondition==null){
            return null;

        }
        return post.region.in(regionCondition);

    }

    private BooleanExpression CategoryEq(List<String> categoryCondition) {
        if (categoryCondition==null){
            return null;
        }
        return post.possibleOffer.in(categoryCondition);
    }

}
