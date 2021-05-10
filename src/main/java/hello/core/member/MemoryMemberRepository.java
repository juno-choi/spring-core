package hello.core.member;

import java.util.HashMap;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {

    // 동시성 issue 때문에 실무에서는 ConcurrentHashMap을 사용하는게 적절하지만 개발용이니 간단한 HashMap으로 구현
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
