package spring;

import org.springframework.beans.factory.annotation.Autowired;

public class ChangePasswordService {

    /*
     * @Autowired 애너테이션을 붙이면 설정 클래스에서 의존을 주입하지 않아도 됌.
     *  - 스프링이 해당 타입의 Bean 객체를 찾아서 필드에 할당.
     */
    @Autowired
    private MemberDao memberDao;
    
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
