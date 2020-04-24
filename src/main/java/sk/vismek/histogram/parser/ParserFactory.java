package sk.vismek.histogram.parser;

import sk.vismek.histogram.main.Params;

import java.io.File;
import java.io.FileNotFoundException;

public class ParserFactory {
    public static AbstractParser getParser(Params params) {
        switch (params.parserEnum) {
            case FILE:
                try {
                    return new FileParser(new File(params.input), params.ngram, params.listener);
                } catch (FileNotFoundException | NullPointerException e) {
                    System.err.println("File '" + params.input + "' not found!");;
                    return null;
                }
            case STRING:
                return new StringParser(params.input, params.ngram, params.listener);
            default:
                return null;
        }
    }
}
