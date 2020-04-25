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
        assertEquals(5, parsed.size());
        assertEquals("test for", parsed.get(0));
        assertEquals("for parsed", parsed.get(1));
        assertEquals("parsed values", parsed.get(2));
        assertEquals("values from", parsed.get(3));
        assertEquals("from string", parsed.get(4));
    }

    @Test()
    public void string3Test() {
        TestListener listener = new TestListener();
        AbstractParser parser = new StringParser(TEST_STRING, 3, listener);
        parser.parse();
        List<String> parsed = listener.getList();
        assertEquals(4, parsed.size());
        assertEquals("test for parsed", parsed.get(0));
        assertEquals("for parsed values", parsed.get(1));
        assertEquals("parsed values from", parsed.get(2));
        assertEquals("values from string", parsed.get(3));
    }

    @Test()
    public void string6Test() {
        TestListener listener = new TestListener();
        AbstractParser parser = new StringParser(TEST_STRING, 6, listener);
        parser.parse();
        List<String> parsed = listener.getList();
        assertEquals(1, parsed.size());
        assertEquals("test for parsed values from string", parsed.get(0));
    }

    @Test()
    public void string8Test() {
        TestListener listener = new TestListener();
        AbstractParser parser = new StringParser(TEST_STRING, 8, listener);
        parser.parse();
        List<String> parsed = listener.getList();
        assertEquals(0, parsed.size());
    }
}
