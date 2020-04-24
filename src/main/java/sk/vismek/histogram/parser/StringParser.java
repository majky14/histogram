package sk.vismek.histogram.parser;

public class StringParser extends AbstractParser {

    private String value;

    StringParser(String value, ParserListener listener) {
        super(listener);
        this.value = value;
    }

    StringParser(String value, Integer ngram, ParserListener listener) {
        super(ngram, listener);
        this.value = value;
    }

    @Override
    public void parse() {
        this.processLine(this.value);
    }
}
