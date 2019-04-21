package console;

import com.beust.jcommander.JCommander;
import logic.Transponator;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.util.List;

public class Main {
    private String info;
    @Argument(required = true)
    private String inputFile;

    @Option(name = "-o")
    private String outputFile;

    @Option(name = "-a")
    private int num;

    @Option(name = "-t")
    private boolean isCut;

    @Option(name = "-r")
    private boolean isRightSide;

    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.info);
        main.launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
            return;
        }

        Transponator transponator = new Transponator(isRightSide, isCut, num, outputFile);
        List<List<String>> output = transponator.getTransposedMatrix(inputFile);
        List<List<String>> input = transponator.getInput(inputFile);

        System.out.println("\n____________________________\nначальная матрица." +
                " \n____________________________\n");
        for (List<String> stringList : input) {
            StringBuilder sb = new StringBuilder();
            for (String s : stringList) {
                sb.append(s);
            }
            System.out.println(sb);
        }
        System.out.println("____________________________" +
                "\n////////////////////////////" +
                "\n____________________________" +
                "\nтранспонированная матрица." +
                " \n____________________________\n");

        for (List<String> strings : output) {
            StringBuilder sb = new StringBuilder();
            for (String string : strings) {
                sb.append(string);
            }
            System.out.println(sb);
        }
        System.out.println("____________________________\n");
    }
    private Main(){
        info = new String("введите строки для транспонирования, либо введите имя файла");
    }

}
