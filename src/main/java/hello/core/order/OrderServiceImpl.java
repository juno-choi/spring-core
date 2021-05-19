package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    /*private final MemberRepository memberRepository = new MemoryMemberRepository();
    //DIP에 의하면 추상에만 의존해야하는데 구현체에 의존하게된다.
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //DIP를 지키기 위해 추상에만 의존하게 변경하였는데 구현체가 없어 Null Point Exception이 발생한다.
    private DiscountPolicy discountPolicy;*/

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;


    //lombok의 @RequiredArgsConstructor annotation이 final 붙은 필드 객체들을 모두 생성자 패턴으로 만들어준다.
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        //타입 매칭된 결과가 2개 이상일때는 필드명으로 추가 매칭이 된다.
        this.discountPolicy = discountPolicy;
    }



    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트용
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
