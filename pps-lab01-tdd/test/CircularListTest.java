import lab01.tdd.CircularList;
import lab01.tdd.CircularListImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {

    private CircularList circularList;

    private void addSequenceOfIntegers(int number)
    {
        for (int i=1;i<=number;i++)
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
        addSequenceOfIntegers(100);
        assertEquals(100, circularList.size());
    }

    @Test
    public void testNotEmpty() {
        addSequenceOfIntegers(1);
        assertFalse(circularList.isEmpty());
    }

    @Test
    public void testSimpleNext() {
        addSequenceOfIntegers(100);
        assertEquals(Optional.of(1), circularList.next());
    }

    @Test
    public void testMultipleNext() {
        addSequenceOfIntegers(100);

        for (int i=0;i<200;i++)
            assertEquals(Optional.of(i%100+1), circularList.next());
    }




}
