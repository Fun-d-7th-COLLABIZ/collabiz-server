package collab.collabiz.repository;

import collab.collabiz.entity.Keyword;
import collab.collabiz.entity.Post;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostRepositoryCustom {

    public List<Post> search(List<String> regionCondition, List<String> categoryCondition, List<String> keywordCondition, Pageable pageable);
}
