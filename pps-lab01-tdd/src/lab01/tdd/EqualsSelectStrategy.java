package lab01.tdd;

public class EqualsSelectStrategy implements SelectStrategy {
    private final int element;

    public EqualsSelectStrategy(int element) {
        this.element = element;
    }

    @Override
    public boolean apply(int element) {
        return this.element == element;
    }
}
