package jpabook.jpashop.Service;

import jpabook.jpashop.Repository.MemberRepository;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor //final이 있는 필드만 가지고 생성자를 만들어 준다.
public class MemberService {

    /* [필드 주입]
    @Autowired
    MemberRepository memberRepository;
    */

    // [생성자 주입]
//    private final MemberRepository memberRepository;
//
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }


    private final MemberRepository memberRepository;


    //회원 가입
    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);//중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member){
        List<Member> findMembers =
                memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }


    //전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public  Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
