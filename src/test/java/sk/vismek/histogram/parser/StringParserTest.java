package sk.vismek.histogram.parser;

import org.junit.Test;
import sk.vismek.histogram.helpers.TestListener;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class StringParserTest {

    private static final String TEST_STRING = "test\n for, parsed? values. from! string";

    @Test()
    public void string2Test() {
        TestListener listener = new TestListener();
        AbstractParser parser = new StringParser(TEST_STRING, listener);
        parser.parse();
        List<String> parsed = listener.getList();
        assertEquals(parsed.size(), 5);
        assertEquals(parsed.get(0), "test for");
        assertEquals(parsed.get(1), "for parsed");
        assertEquals(parsed.get(2), "parsed values");
        assertEquals(parsed.get(3), "values from");
        assertEquals(parsed.get(4), "from string");
    }

    @Test()
    public void string3Test() {
        TestListener listener = new TestListener();
        AbstractParser parser = new StringParser(TEST_STRING, 3, listener);
        parser.parse();
        List<String> parsed = listener.getList();
        assertEquals(parsed.size(), 4);
        assertEquals(parsed.get(0), "test for parsed");
        assertEquals(parsed.get(1), "for parsed values");
        assertEquals(parsed.get(2), "parsed values from");
        assertEquals(parsed.get(3), "values from string");
    }
}
