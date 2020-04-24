package sk.vismek.histogram.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileParser extends AbstractParser {

    private Scanner scanner;

    FileParser(File file, ParserListener listener) throws FileNotFoundException {
        super(listener);
        this.loadInputStream(file);
    }

    FileParser(File file, Integer ngram, ParserListener listener) throws FileNotFoundException {
        super(ngram, listener);
        this.loadInputStream(file);
    }

    private void loadInputStream(File file) throws FileNotFoundException {
        this.scanner = new Scanner(new FileInputStream(file));
    }

    @Override
    public void parse() {
        while (this.scanner.hasNextLine()) {
            String line = this.scanner.nextLine();
            if (line.length() > 0) {
                this.processLine(line);
            }
        }
        this.complete();
    }
}
