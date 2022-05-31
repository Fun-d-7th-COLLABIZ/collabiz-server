package collab.collabiz.controller.Search;

import collab.collabiz.controller.Search.Dto.SearchRequestDto;
import collab.collabiz.entity.Keyword;
import collab.collabiz.entity.Post;
import collab.collabiz.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping(value = "/search") //콜라보레이션 검색
    public ResponseEntity<List<Post>> search(@PageableDefault(size = 5) @RequestBody SearchRequestDto searchRequestDto, Pageable pageable){

        List<Post> postList= searchService.search(searchRequestDto.getRegionCondition(), searchRequestDto.getCategoryCondition(), searchRequestDto.getKeywordCondition(),pageable);
        return new ResponseEntity(postList,HttpStatus.OK);
    }
}
