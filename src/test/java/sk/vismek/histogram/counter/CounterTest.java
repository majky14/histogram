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
        assertEquals((Integer) 1, map.get("this is"));
        assertEquals((Integer) 2, map.get("is test"));
        assertEquals((Integer) 1, map.get("test for"));
        assertEquals((Integer) 2, map.get("for counter"));
    }
}
