package sk.vismek.histogram.counter;

import sk.vismek.histogram.parser.ParserListener;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Counter implements ParserListener {

    private PrintWriter fileWriter;

    private Map<String, Integer> ngramValues;

    public Counter() {
        this.ngramValues = new HashMap<>();
    }

    public Counter withWriter(File output) {
        try {
            if (output.createNewFile()) {
                System.out.println("File '" + output.getName() + "' created.");
            }
            this.fileWriter = new PrintWriter(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public Map<String, Integer> getNgramValues() {
        return ngramValues;
    }

    @Override
    public void process(List<String> parsed) {
        parsed.forEach(value -> {
            if (!this.ngramValues.containsKey(value)) {
                this.ngramValues.put(value, 1);
            } else {
                this.ngramValues.merge(value, 1, Integer::sum);
            }
        });
    }

    @Override
    public void complete() {
        if (this.fileWriter == null) return;
        this.ngramValues.forEach((s, integer) -> {
            this.fileWriter.println("\"" + s + "\" " + integer);
        });
        this.fileWriter.close();
    }
}
