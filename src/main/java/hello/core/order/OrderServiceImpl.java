package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService {

    /*private final MemberRepository memberRepository = new MemoryMemberRepository();
    //DIP에 의하면 추상에만 의존해야하는데 구현체에 의존하게된다.
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //DIP를 지키기 위해 추상에만 의존하게 변경하였는데 구현체가 없어 Null Point Exception이 발생한다.
    private DiscountPolicy discountPolicy;*/

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
