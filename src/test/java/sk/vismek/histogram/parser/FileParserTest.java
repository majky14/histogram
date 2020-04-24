package sk.vismek.histogram.parser;

import org.junit.Test;
import sk.vismek.histogram.helpers.TestListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class FileParserTest {

    private static final String TEST_FILE = "test_file.txt";
    private static final String TEST_FILE_MULTIPLE_LINE = "test_file_multiple_line.txt";

    @Test()
    public void file2Test() {
        System.out.println("test");
        AbstractParser parser = null;
        TestListener listener = new TestListener();
        try {
            parser = new FileParser(new File(getClass().getClassLoader().getResource(TEST_FILE).getFile()), listener);
        } catch (FileNotFoundException | NullPointerException e) {;
            e.printStackTrace();
        }
        assertNotEquals(parser, null);
        parser.parse();
        List<String> parsed = listener.getList();
        assertEquals(parsed.size(), 5);
        assertEquals(parsed.get(0), "test for");
        assertEquals(parsed.get(1), "for parsed");
        assertEquals(parsed.get(2), "parsed values");
        assertEquals(parsed.get(3), "values from");
        assertEquals(parsed.get(4), "from file");
    }

    @Test()
    public void fileMultipleLine2Test() {
        System.out.println("test");
        AbstractParser parser = null;
        TestListener listener = new TestListener();
        try {
            parser = new FileParser(new File(getClass().getClassLoader().getResource(TEST_FILE_MULTIPLE_LINE).getFile()), listener);
        } catch (FileNotFoundException | NullPointerException e) {;
            e.printStackTrace();
        }
        assertNotEquals(parser, null);
        parser.parse();
        List<String> parsed = listener.getList();
        assertEquals(parsed.size(), 5);
        assertEquals(parsed.get(0), "test for");
        assertEquals(parsed.get(1), "for parsed");
        assertEquals(parsed.get(2), "parsed values");
        assertEquals(parsed.get(3), "values from");
        assertEquals(parsed.get(4), "from file");
    }
}
