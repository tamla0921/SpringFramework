package spring;

import org.springframework.transaction.annotation.Transactional;

public class ChangePasswordService {

    private MemberDao memberDao;
    
    @Transactional
    public void changePassword(String email, String oldPwd, String newPwd) {
        Member member = memberDao.selectByEmail(email);
        if (member == null) {
            throw new MemberNotFoundException();
        }
        
        // oldPassword가 일치하지 않아 Exception 발생 시 아래의 메서드는 실행하지 않음.
        member.changePassword(oldPwd, newPwd);
        
        memberDao.update(member);
    }
    
    public void setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
    }
}
