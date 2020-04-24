package sk.vismek.histogram.helpers;

import sk.vismek.histogram.parser.ParserListener;

import java.util.LinkedList;
import java.util.List;

public class TestListener implements ParserListener {

    private List<String> list = new LinkedList<>();

    @Override
    public void process(List<String> parsed) {
        list.addAll(parsed);
    }

    @Override
    public void complete() {

    }

    public List<String> getList() {
        return list;
    }
}
