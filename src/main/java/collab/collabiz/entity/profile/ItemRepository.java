package collab.collabiz.entity.profile;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ItemRepository {
    private final Map<Long, MemberImage> store = new HashMap<>();
    private long sequence = 0L;
    public MemberImage save(MemberImage item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }
    public MemberImage findById(Long id) {
        return store.get(id);
    }
}
