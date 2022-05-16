package collab.collabiz.controller.Collaboration;

import collab.collabiz.service.CollaborationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CollaborationController {

    private final CollaborationService collaborationService;
}