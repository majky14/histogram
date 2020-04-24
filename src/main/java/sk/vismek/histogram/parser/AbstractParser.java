package sk.vismek.histogram.parser;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractParser {
    public static final Integer DEFAULT_NGRAM = 2;
    private static final String PARSE_VALUE = "\\s+";
    private static final String JOIN_VALUE = " ";

    private Integer ngram;
    private List<String> lastWords;

    private ParserListener listener;

    public abstract void parse();

    AbstractParser(ParserListener listener) {
        this(DEFAULT_NGRAM, listener);
    }

    AbstractParser(Integer ngram, ParserListener listener) {
        if (ngram == null) {
            ngram = DEFAULT_NGRAM;
        }
        this.ngram = ngram;
        this.lastWords = new LinkedList<>();
        this.listener = listener;
    }

    void processLine(String str) {
        str = str.trim().replaceAll("\\p{Punct}", "").toLowerCase();
        this.lastWords.addAll(Arrays.asList(str.split(PARSE_VALUE)));
        List<String> list = new LinkedList<>();
        for (int i = 0; i <= this.lastWords.size() - this.ngram; i++) {
            list.add(String.join(JOIN_VALUE, this.lastWords.subList(i, i + this.ngram)));
        }
        this.listener.process(list);
        this.lastWords = this.lastWords.subList(this.lastWords.size() - this.ngram + 1, this.lastWords.size());
    }

    void complete() {
        this.listener.complete();
    }
}
