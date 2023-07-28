package spring;

/**
 * 동일한 이메일을 갖고 있는 회원이 존재할 때 MemberRegisterService가 발생시키는 Exception 타입.
 * @author seo
 *
 */
public class DuplicateMemberException extends RuntimeException {

    public DuplicateMemberException(String message) {
        super(message);
    }
}
