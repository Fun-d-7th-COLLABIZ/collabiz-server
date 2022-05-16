package collab.collabiz.service;

import collab.collabiz.repository.CollaborationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CollaborationService {

    private final CollaborationRepository collaborationRepository;
}