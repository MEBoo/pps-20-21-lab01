import lab01.tdd.CircularList;
import lab01.tdd.CircularListImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {

    static final int SEQUENCE_SIZE=100;
    private CircularList circularList;

    private void addSequenceOfIntegers(int number)
    {
        addSequenceOfIntegers(number,1);
    }
    private void addSequenceOfIntegers(int number, int startElement)
    {
        for (int i=startElement;i<=number;i++)
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
        addSequenceOfIntegers(1, SEQUENCE_SIZE + 1);
        assertEquals(1, circularList.size());
    }

    @Test
    public void testMultipleAdd() {
        addSequenceOfIntegers(SEQUENCE_SIZE, SEQUENCE_SIZE + 1);
        assertEquals(SEQUENCE_SIZE, circularList.size());
    }

    @Test
    public void testNotEmpty() {
        addSequenceOfIntegers(1, SEQUENCE_SIZE + 1);
        assertFalse(circularList.isEmpty());
    }

    @Test
    public void testSimpleNext() {
        addSequenceOfIntegers(SEQUENCE_SIZE, SEQUENCE_SIZE + 1);
        assertEquals(Optional.of(1), circularList.next());
    }

    @Test
    public void testMultipleNext() {
        addSequenceOfIntegers(SEQUENCE_SIZE, SEQUENCE_SIZE + 1);

        for (int i=0;i<2*SEQUENCE_SIZE;i++)
            assertEquals(Optional.of(i%SEQUENCE_SIZE+1), circularList.next());
    }

    @Test
    public void testSimplePrevious() {
        addSequenceOfIntegers(SEQUENCE_SIZE, SEQUENCE_SIZE + 1);
        assertEquals(Optional.of(SEQUENCE_SIZE), circularList.previous());
    }

    @Test
    public void testMultiplePrevious() {
        addSequenceOfIntegers(SEQUENCE_SIZE, SEQUENCE_SIZE + 1);

        for (int i=0;i<2*SEQUENCE_SIZE;i++)
            assertEquals(Optional.of(i>=SEQUENCE_SIZE?2*SEQUENCE_SIZE-i:SEQUENCE_SIZE-i), circularList.previous());
    }

    @Test
    public void testMixedUsage() {
        addSequenceOfIntegers(SEQUENCE_SIZE, SEQUENCE_SIZE + 1);

        assertEquals(Optional.of(SEQUENCE_SIZE), circularList.previous());
        assertEquals(Optional.of(1), circularList.next());
        assertEquals(Optional.of(2), circularList.next());

        addSequenceOfIntegers(SEQUENCE_SIZE,SEQUENCE_SIZE+1);
        assertEquals(Optional.of(3), circularList.next());
        assertEquals(Optional.of(2), circularList.previous());

        //Next() until the last element of the first sequence added.
        for (int i=3;i<=SEQUENCE_SIZE;i++)
            assertEquals(Optional.of(i), circularList.next());

        //Next() on the second sequence added
        assertEquals(Optional.of(1), circularList.next());
        assertEquals(Optional.of(2), circularList.next());
    }

    @Test
    public void testReset() {
        addSequenceOfIntegers(SEQUENCE_SIZE, SEQUENCE_SIZE + 1);

        for (int i=0;i<3;i++)
            assertEquals(Optional.of(i), circularList.next());
    }


}
