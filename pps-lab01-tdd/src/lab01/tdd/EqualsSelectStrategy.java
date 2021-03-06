package lab01.tdd;

public class EqualsSelectStrategy implements SelectStrategy {
    private final int element;

    public EqualsSelectStrategy(final int element) {
        this.element = element;
    }

    @Override
    public boolean apply(final int element) {
        return this.element == element;
    }
}
