package sk.vismek.histogram.parser;

import org.junit.Test;
import sk.vismek.histogram.helpers.ResourceLoader;
import sk.vismek.histogram.helpers.TestListener;

import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class FileParserTest {

    private static final String TEST_FILE = "test_file.txt";
    private static final String TEST_FILE_MULTIPLE_LINE = "test_file_multiple_line.txt";

    @Test()
    public void file2Test() {
        AbstractParser parser = null;
        TestListener listener = new TestListener();
        try {
            parser = new FileParser(ResourceLoader.getResourceFile(TEST_FILE), listener);
        } catch (FileNotFoundException | NullPointerException e) {;
            e.printStackTrace();
        }
        assertNotEquals(parser, null);
        parser.parse();
        List<String> parsed = listener.getList();
        assertEquals(5, parsed.size());
        assertEquals("test for", parsed.get(0));
        assertEquals("for parsed", parsed.get(1));
        assertEquals("parsed values", parsed.get(2));
        assertEquals("values from", parsed.get(3));
        assertEquals("from file", parsed.get(4));
    }

    @Test()
    public void fileMultipleLine2Test() {
        AbstractParser parser = null;
        TestListener listener = new TestListener();
        try {
            parser = new FileParser(ResourceLoader.getResourceFile(TEST_FILE_MULTIPLE_LINE), listener);
        } catch (FileNotFoundException | NullPointerException e) {;
            e.printStackTrace();
        }
        assertNotEquals(parser, null);
        parser.parse();
        List<String> parsed = listener.getList();
        assertEquals(5, parsed.size());
        assertEquals("test for", parsed.get(0));
        assertEquals("for parsed", parsed.get(1));
        assertEquals("parsed values", parsed.get(2));
        assertEquals("values from", parsed.get(3));
        assertEquals("from file", parsed.get(4));
    }

    @Test()
    public void fileMultipleLine6Test() {
        AbstractParser parser = null;
        TestListener listener = new TestListener();
        try {
            parser = new FileParser(ResourceLoader.getResourceFile(TEST_FILE_MULTIPLE_LINE), 6, listener);
        } catch (FileNotFoundException | NullPointerException e) {;
            e.printStackTrace();
        }
        assertNotEquals(parser, null);
        parser.parse();
        List<String> parsed = listener.getList();
        assertEquals(1, parsed.size());
        assertEquals("test for parsed values from file", parsed.get(0));
    }

    @Test()
    public void fileMultipleLine8Test() {
        AbstractParser parser = null;
        TestListener listener = new TestListener();
        try {
            parser = new FileParser(ResourceLoader.getResourceFile(TEST_FILE_MULTIPLE_LINE), 8, listener);
        } catch (FileNotFoundException | NullPointerException e) {;
            e.printStackTrace();
        }
        assertNotEquals(parser, null);
        parser.parse();
        List<String> parsed = listener.getList();
        assertEquals(0, parsed.size());
    }
}
