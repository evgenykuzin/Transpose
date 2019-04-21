import console.Main;
import junit.framework.Assert;
import logic.Transponator;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.util.List;

public class TransposeTest {
    private String iName;
    private String oName;
    private boolean isRightSide;
    private boolean isCut;
    private int num;

    private String getContent(String name){
        List<String> fileList = new Transponator().readInputFile(name);
        StringBuilder fileContent = new StringBuilder();
        for (String s : fileList) {
            String[] spliter = s.split("[\\s]+");
            for (String word : spliter) {
                fileContent.append(word);
            }
        }
        return fileContent.toString();
    }

    @Test
    public void bazeTest() {
        String[] testArgs;
        testArgs = new String[]{"res/test1", "-o", "res/output1"};
        Main.main(testArgs);
        oName = testArgs[2];
        iName = testArgs[0];

        String expectedContent = new String(
                "ADG" +
                        "BEH" +
                        "CFI");

        String actualContent = getContent(oName);
        Assert.assertEquals(expectedContent, actualContent);

        testArgs = new String[]{"res/test2", "-o", "res/output1"};
        Main.main(testArgs);
        expectedContent = new String(
                "AD" +
                        "BE" +
                        "CF");
        iName = testArgs[0];
        Assert.assertEquals(expectedContent, getContent(oName));
    }



}
