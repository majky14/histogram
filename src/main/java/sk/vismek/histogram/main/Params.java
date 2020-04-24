package sk.vismek.histogram.main;

import sk.vismek.histogram.parser.ParserEnum;
import sk.vismek.histogram.parser.ParserListener;

import java.util.List;

public class Params {
    public ParserEnum parserEnum;
    public Integer ngram;
    public String input;
    public String output;
    public ParserListener listener;

    Params(ParserEnum parserEnum, String input, String output, Integer ngram) {
        this.parserEnum = parserEnum;
        this.ngram = ngram;
        this.input = input;
        this.output = output;
        this.listener = new ParserListener() {
            @Override
            public void process(List<String> parsed) {
                System.out.println(parsed);
            }

            @Override
            public void complete() {
            }
        };
    }
}
