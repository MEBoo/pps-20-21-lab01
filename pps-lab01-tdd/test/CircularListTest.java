import lab01.tdd.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {

    static final int SEQUENCE_SIZE=100; // sequence size >= 10
    private CircularList circularList;
    private final SelectStrategyFactory strategyFactory = new SelectStrategyFactoryImpl();

    private void addSequenceOfIntegers(int number)
    {
        addSequenceOfIntegers(number,1);
    }
    private void addSequenceOfIntegers(int number, int startElement)
    {
        for (int i=startElement;i<=number+startElement-1;i++)
            circularList.add(i);
    }

    @BeforeEach
    public void setup() {
        this.circularList = new CircularListImpl();
    }

    @Test
    public void testInitialSize() {
        assertEquals(0, circularList.size());
    }

    @Test
    public void testInitialEmpty() {
        assertTrue(circularList.isEmpty());
    }

    @Test
    public void testSingleAdd() {
        addSequenceOfIntegers(1);
        assertEquals(1, circularList.size());
    }

    @Test
    public void testMultipleAdd() {
        addSequenceOfIntegers(SEQUENCE_SIZE);
        assertEquals(SEQUENCE_SIZE, circularList.size());
    }

    @Test
    public void testNotEmpty() {
        addSequenceOfIntegers(1);
        assertFalse(circularList.isEmpty());
    }

    @Test
    public void testEmptyNext() {
        assertEquals(Optional.empty(), circularList.next());
    }

    @Test
    public void testSimpleNext() {
        addSequenceOfIntegers(SEQUENCE_SIZE);
        assertEquals(Optional.of(1), circularList.next());
    }

    @Test
    public void testMultipleNext() {
        addSequenceOfIntegers(SEQUENCE_SIZE);

        for (int i=0;i<2*SEQUENCE_SIZE;i++)
            assertEquals(Optional.of(i%SEQUENCE_SIZE+1), circularList.next());
    }

    @Test
    public void testNextWithEvenSelectStrategy() {
        addSequenceOfIntegers(SEQUENCE_SIZE);

        final SelectStrategy strategy=strategyFactory.createEvenStrategy();

        assertEquals(Optional.of(2), circularList.next(strategy));
        assertEquals(Optional.of(4), circularList.next(strategy));
    }

    @Test
    public void testNextWithMultipleOfSelectStrategy() {
        addSequenceOfIntegers(SEQUENCE_SIZE);

        final SelectStrategy strategy=strategyFactory.createMultipleOfStrategy(3);

        assertEquals(Optional.of(3), circularList.next(strategy));
        assertEquals(Optional.of(6), circularList.next(strategy));
    }

    @Test
    public void testNextWithEqualsSelectStrategy() {
        addSequenceOfIntegers(SEQUENCE_SIZE);

        final SelectStrategy strategy=strategyFactory.createEqualsStrategy(5);

        assertEquals(Optional.of(5), circularList.next(strategy));
    }

    @Test
    public void testCircularNextWithEqualsSelectStrategy() {
        addSequenceOfIntegers(SEQUENCE_SIZE);

        final SelectStrategy strategy=strategyFactory.createEqualsStrategy(SEQUENCE_SIZE);

        // Find 2 times same value
        assertEquals(Optional.of(SEQUENCE_SIZE), circularList.next(strategy));
        assertEquals(Optional.of(SEQUENCE_SIZE), circularList.next(strategy));

        // Find a near last element and then a near first one
        assertEquals(Optional.of(SEQUENCE_SIZE-2), circularList.next(strategyFactory.createEqualsStrategy(SEQUENCE_SIZE-2)));
        assertEquals(Optional.of(2), circularList.next(strategyFactory.createEqualsStrategy(2)));
    }

    @Test
    public void testEmptyPrevious() {
        assertEquals(Optional.empty(), circularList.previous());
    }

    @Test
    public void testSimplePrevious() {
        addSequenceOfIntegers(SEQUENCE_SIZE);
        assertEquals(Optional.of(SEQUENCE_SIZE), circularList.previous());
    }

    @Test
    public void testMultiplePrevious() {
        addSequenceOfIntegers(SEQUENCE_SIZE);

        for (int i=0;i<2*SEQUENCE_SIZE;i++)
            assertEquals(Optional.of(i>=SEQUENCE_SIZE?2*SEQUENCE_SIZE-i:SEQUENCE_SIZE-i), circularList.previous());
    }

    @Test
    public void testReset() {
        addSequenceOfIntegers(SEQUENCE_SIZE);

        // Skip some elements
        for (int i=0;i<3;i++)
            circularList.next();

        circularList.reset();
        assertEquals(Optional.of(1), circularList.next());
    }

    @Test
    public void testMixedUsage() {
        addSequenceOfIntegers(SEQUENCE_SIZE);

        assertEquals(Optional.of(SEQUENCE_SIZE), circularList.previous());
        assertEquals(Optional.of(1), circularList.next());
        assertEquals(Optional.of(2), circularList.next());

        addSequenceOfIntegers(SEQUENCE_SIZE,SEQUENCE_SIZE+1);
        assertEquals(Optional.of(3), circularList.next());
        assertEquals(Optional.of(2), circularList.previous());

        // Next() until the last element of the first sequence added.
        for (int i=3;i<=SEQUENCE_SIZE;i++)
            assertEquals(Optional.of(i), circularList.next());

        // Next() on the second sequence added
        assertEquals(Optional.of(SEQUENCE_SIZE+1), circularList.next());
        assertEquals(Optional.of(SEQUENCE_SIZE+2), circularList.next());

        circularList.reset();
        assertEquals(Optional.of(1), circularList.next());
    }
}
