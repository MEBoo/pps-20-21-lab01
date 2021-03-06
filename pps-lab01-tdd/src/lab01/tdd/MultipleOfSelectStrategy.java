package lab01.tdd;

public class MultipleOfSelectStrategy implements SelectStrategy {
    private final int multiplier;
    
    public MultipleOfSelectStrategy(int multiplier) {
        this.multiplier=multiplier;
    }

    @Override
    public boolean apply(int element) {
        return element % this.multiplier == 0;
    }
}
