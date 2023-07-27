package assembler;

import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberRegisterService;

public class Assembler {

    private MemberDao memberDao;
    private MemberRegisterService regSvc;
    private ChangePasswordService pwdSvc; // 이렇게 생성자 없어도, 자리만 만들어 놓는 게 다시봐도 낯설다...
    
    public Assembler() {
        memberDao = new MemberDao();
        
        // 생성자를 통해 의존 주입하기.
        regSvc = new MemberRegisterService(memberDao);
        
        // setter 메서드를 통해 의존 주입하기.
        pwdSvc = new ChangePasswordService();
        pwdSvc.setMemberDao(memberDao);
    }

    public MemberDao getMemberDao() {
        return memberDao;
    }

    public MemberRegisterService getMemberRegisterService() {
        return regSvc;
    }

    public ChangePasswordService getChangePasswordService() {
        return pwdSvc;
    }
    
    
}
