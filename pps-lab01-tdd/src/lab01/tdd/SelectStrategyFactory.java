package lab01.tdd;

public interface SelectStrategyFactory {

    SelectStrategy createEachStrategy();
    
    SelectStrategy createEvenStrategy();

    SelectStrategy createMultipleOfStrategy(final int number);

    SelectStrategy createEqualsStrategy(final int number);
}