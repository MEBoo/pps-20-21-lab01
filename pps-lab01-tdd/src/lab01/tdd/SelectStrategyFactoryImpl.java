package lab01.tdd;

public class SelectStrategyFactoryImpl implements SelectStrategyFactory {

    @Override
    public SelectStrategy createEvenStrategy() {
        return new EvenSelectStrategy();
    }
    @Override
    public SelectStrategy createMultipleOfStrategy(final int number) {
        return new MultipleOfSelectStrategy(number);
    }

    @Override
    public SelectStrategy createEqualsStrategy(final int number) {
        return new EqualsSelectStrategy(number);
    }
}
