package com.example.corebasic.discount;

import com.example.corebasic.annotation.MainDiscountPolicy;
import com.example.corebasic.member.Grade;
import com.example.corebasic.member.Member;
import org.springframework.stereotype.Component;

@Component
// ComponentScan 의 대상으로 지정하기 위해 @Component 를 추가
// @Qualifier("MainDiscountPolicy")
@MainDiscountPolicy
// @MainDiscountPolicy(Qualifier) 를 사용하여 @Autowired 한 경우, @Primary 빈 보다 우선 순위를 가짐
public class RateDiscountPolicy implements DiscountPolicy {
    private int discountRate = 10;

    @Override
    public int discount(Member member, int price) {
        return member.getGrade() == Grade.VIP ? (price * discountRate) / 100 : 0;
    }
}