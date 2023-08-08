package spring;

import java.time.LocalDateTime;

public class MemberRegisterService {

        private MemberDao memberDao;
        
        // DI (의존 주입) 패턴.
        // 생성자를 통해서 MemberRegisterService가 의존하고 있는 MemberDao 객체를 주입.
        public MemberRegisterService(MemberDao memberDao) {
            this.memberDao = memberDao;
        }
        
        public Long regist(RegisterRequest req) {
            Member member = memberDao.selectByEmail(req.getEmail());
            if (member != null) {
                throw new DuplicateMemberException("dup email" + req.getEmail());
            }
            
            Member newMember = new Member(req.getEmail(), req.getPassword(), req.getName(), LocalDateTime.now());
            memberDao.insert(newMember);
            return newMember.getId();
        }

}
