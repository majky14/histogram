package sk.vismek.histogram.parser;

import java.util.List;

public interface ParserListener {
    void process(List<String> parsed);
    void complete();
}
