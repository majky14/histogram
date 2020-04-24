package sk.vismek.histogram.counter;

import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class CounterTest {

    @Test
    public void counterTest() {
        Counter counter = new Counter();
        counter.process(Arrays.asList("this is", "is test", "test for", "for counter"));
        counter.process(Arrays.asList("is test", "for counter"));
        Map<String, Integer> map = counter.getNgramValues();
        assertEquals(map.get("this is"), (Integer) 1);
        assertEquals(map.get("is test"), (Integer) 2);
        assertEquals(map.get("test for"), (Integer) 1);
        assertEquals(map.get("for counter"), (Integer) 2);
    }
}
