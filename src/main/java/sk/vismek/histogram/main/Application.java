package sk.vismek.histogram.main;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import sk.vismek.histogram.counter.Counter;
import sk.vismek.histogram.parser.AbstractParser;
import sk.vismek.histogram.parser.ParserEnum;
import sk.vismek.histogram.parser.ParserFactory;

import java.io.File;

public class Application {

    private static final String INPUT_FILE = "input_file.txt";
    private static final String OUTPUT_FILE = "output_file.txt";

    public static void main(String[] args) {
        Options options = createOptions();
        CommandLine cmd = createCmd(args, options);
        Params params = parseCmd(cmd);
        params.listener = new Counter().withWriter(new File(params.output));
        AbstractParser parser = ParserFactory.getParser(params);
        if (parser == null) {
            System.exit(1);
        }
        parser.parse();
    }

    private static Params parseCmd(CommandLine cmd) {
        Params params = new Params(ParserEnum.FILE, INPUT_FILE, OUTPUT_FILE, AbstractParser.DEFAULT_NGRAM);
        if (cmd.hasOption("f")) {
            params.input = cmd.getOptionValue("f");
        } else if (cmd.hasOption("s")) {
            params.parserEnum = ParserEnum.STRING;
            params.input = cmd.getOptionValue("s");
        }
        if (cmd.hasOption("o")) {
            params.output = cmd.getOptionValue("o");
        }
        if (cmd.hasOption("n")) {
            try {
                params.ngram = Integer.parseInt(cmd.getOptionValue("n"));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return params;
    }

    private static Options createOptions() {
        Options options = new Options();

        Option input = new Option("f", "file", true, "input file path - default file name 'input_file.txt'");
        options.addOption(input);

        Option string = new Option("s", "string", true, "input string - example: \"test string for parser\"");
        options.addOption(string);

        Option ngram = new Option("n", "ngram", true, "ngram count - integer");
        options.addOption(ngram);

        Option output = new Option("o", "output", true, "output file path - default file name 'output_file.txt'");
        options.addOption(output);

        Option help = new Option("h", "help", false, "help");
        options.addOption(help);

        return options;
    }

    private static CommandLine createCmd(String[] args, Options options) {
        CommandLineParser cmdParser = new DefaultParser();
        CommandLine cmd = null;
        try {
            cmd = cmdParser.parse(options, args);
            if (cmd.hasOption("h")) {
                printHelpAndExit(options);
            }
        } catch (ParseException e) {
            e.printStackTrace();
            printHelpAndExit(options);
        }
        return cmd;
    }

    private static void printHelpAndExit(Options options) {
        new HelpFormatter().printHelp("ngram parser", options);
        System.exit(0);
    }
}
