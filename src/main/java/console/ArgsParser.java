package console;


import com.beust.jcommander.Parameter;

import java.util.ArrayList;

public class ArgsParser {
    @Parameter(names = "file", description = "name of file. if this parameter is empty - enter data in console")
    public String iFileName;

    @Parameter(names = "-o", description = "name of output file. if this parameter is empty - output in console")
    public String oFileName ;

    @Parameter(names = "-a", description = "number of symbols")
    public boolean a;
    public int num = 10;
    @Parameter(names = "-t", description = "cut the word if it is too long")
    public boolean t = true;

    @Parameter(names = "-r", description = "right alignment. if this parameter is empty - left alignment")
    public boolean r = true;



    @Parameter
    public ArrayList<String> consoleInput = new ArrayList<String>();


}
