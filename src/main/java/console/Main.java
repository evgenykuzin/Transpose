package console;

import logic.Transponator;
import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;
import java.io.File;
import java.util.List;

public class Main {
    private String info = "1) имя входного файла\n" +
            "2) -o [имя выходного файла](опционально)\n" +
            "3) -a сдвиг на [число](опционально)\n" +
            "4) -t обрезать(отсутствие параметра - не обрезать)\n" +
            "5) -r обрезать по правой стороне(отсутствие параметра - по левой стороне)\n";

    @Argument(required = true)
    private String iName;

    @Option(name = "-o")
    private String oName;

    @Option(name = "-a")
    private int num;

    @Option(name = "-t")
    private boolean isCut;

    @Option(name = "-r")
    private boolean isRightSide;

    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.info);
        //args = new String[] {"res/test3", "-o", "res/output", "-a", "4", "-t", "-r"};

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

        Transponator transponator = new Transponator(isRightSide, isCut, num, oName);
        List<List<String>> output = transponator.getTransposedMatrix(iName);
        List<List<String>> consoleInput = transponator.getInput(iName);

        if (!oName.equals("")) {
            //вывод в файл
            File oFile = new File(oName);
            if (oFile.exists() && oFile.canWrite() && oFile.isFile()) {
                transponator.sendToOutputFile(oFile, output);
            }
            else throw new IllegalArgumentException("Что-то не так с выходным файлом." +
                    " Укажите имя правильно или введите другое");
        } else {
            //консольный вывод
            System.out.println("\n____________________________\nначальная матрица." +
                    " \n____________________________\n");
            for (List<String> stringList : consoleInput) {
                StringBuilder sb = new StringBuilder();
                for (String s : stringList) {
                    sb.append(s).append(" ");
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
                    sb.append(string).append(" ");
                }
                System.out.println(sb);
            }
            System.out.println("____________________________\n");
        }
    }
}
