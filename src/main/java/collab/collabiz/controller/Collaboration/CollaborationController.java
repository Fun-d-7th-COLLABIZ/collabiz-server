package collab.collabiz.controller.Collaboration;

import collab.collabiz.controller.Collaboration.dto.Ranking;
import collab.collabiz.service.CollaborationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CollaborationController {

    private final CollaborationService collaborationService;

    /**
     * 콜라보 인기 순위 조회
     */
    @GetMapping("/collaborations/ranking")
    public List<Ranking> ranking() {
        return collaborationService.rankings();
    }
}
