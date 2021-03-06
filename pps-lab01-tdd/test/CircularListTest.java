import lab01.tdd.CircularList;
import lab01.tdd.CircularListImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {

    private CircularList circularList;
    
    @BeforeEach
    public void setup() {
        this.circularList = new CircularListImpl();
    }

    @Test
    public void testInitialSize() {
        assertEquals(0, circularList.size());
    }


}
