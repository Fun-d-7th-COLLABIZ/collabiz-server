package collab.collabiz.controller.Search.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class SearchRequestDto {
    List<String> regionCondition;
    List<String> categoryCondition;
    List<String> keywordCondition;

}
