package logic;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Transponator {


    private boolean isRightSide;
    private boolean isCut;
    private String outputFile;
    private int num;

    public Transponator(boolean isRightSide, boolean isCut, int num, String outputFile) {
        this.isRightSide = isRightSide;
        this.isCut = isCut;
        this.outputFile = outputFile;
        this.num = num;
    }

    public Transponator(){

    }
    public List<List<String>> getTransposedMatrix(String inputFile) {
        return transpose(getFormatedMatrix(readInputFile(inputFile)));
    }

    public List<String> readInputFile(String inputFile) {
        List<String> list = new ArrayList<>();
        try {
            File file = new File(inputFile);
            FileReader fr = new FileReader(file);
            BufferedReader bReader = new BufferedReader(fr);
            String line = bReader.readLine();
            list.add(line);
            while (line != null) {
                line = bReader.readLine();
                list.add(line);
            }
            list.remove(list.size() - 1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<List<String>> getFormatedMatrix(List<String> text) {
        List<List<String>> output = new ArrayList<>();
        for (String line : text) {
            String[] words = line.split("[\\s]+");
            if ((num == 0) & ((isRightSide) | (isCut))) {
                num = 10;
            }
            if (num != 0) {
                for (int i = 0; i < words.length; i++) {

                    if (num > words[i].length()) {
                        int first = words[i].length();
                        for (int j = first; j < num; j++) {
                            if (isRightSide) {
                                words[i] = " " + words[i];
                            } else words[i] += " ";
                        }
                    } else if (isCut) {
                        words[i] = words[i].substring(0, num);
                    }
                }
            }
            List<String> list = new ArrayList<>();
            for (String word : words) {
                if (!word.equals("")) {
                    list.add(word);
                }
            }
            output.add(list);
        }
        return output;
    }

    public List<List<String>> transpose(List<List<String>> input) {
        List<List<String>> output = new ArrayList<List<String>>();
        for (List<String> l : input) {
            for (int i = 0; i < l.size(); i++) {
                if (i >= output.size()) {
                    output.add(new ArrayList<String>());
                }
                List<String> s = output.get(i);
                s.add(l.get(i));
                output.set(i, s);
            }
        }
        return output;
    }

    public List<List<String>> getInput(String inputFile) {
        return getFormatedMatrix(readInputFile(inputFile));
    }
    public void sendToOutputFile(File oFile, List<List<String>> output){
        try {
            FileWriter fw = new FileWriter(oFile);
            BufferedWriter bWriter = new BufferedWriter(fw);
            for (List<String> strings : output) {
                StringBuilder sb = new StringBuilder();
                for (String string : strings) {
                    sb.append(string).append(" ");
                }
                sb.append("\n");
                bWriter.write(sb.toString());
            }

            bWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
