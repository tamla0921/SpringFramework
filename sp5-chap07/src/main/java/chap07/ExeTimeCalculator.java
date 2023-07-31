package chap07;

public class ExeTimeCalculator implements Calculator {

    private Calculator delegate;
    
    /**
     * 생성자를 통해 다른 Calculator 객체를 전달받아 delegate 필드에 할당.
     * @param delegate
     */
    public ExeTimeCalculator(Calculator delegate) {
        this.delegate = delegate;
    }
    
    /**
     * 할당된 Calculator 객체에서 factorial() 메서드 실행.
     * 메서드 실행하기 전과 후에 현재 시간을 구해 실행 시간 출력.
     */
    @Override
    public long factorial(long num) {
        long start = System.nanoTime();
        long result = delegate.factorial(num);
        long end = System.nanoTime();
        
        System.out.printf("%s.factorial(%d) 실행 시간 = %d\n", delegate.getClass().getSimpleName(), num, (end - start));
        
        return result;
    }
}
