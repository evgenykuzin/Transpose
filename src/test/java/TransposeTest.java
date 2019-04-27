import console.Main;
import junit.framework.Assert;
import logic.Transponator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

class TransposeTest {
    private static String test1 = "res/test1";
    private static String test2 = "res/test2";
    private static String test3 = "res/test3";
    private static String output = "res/output";
    private String expectedContent;
    private String actualContent;
    private String[] testArgs;

    private String getContent(String name) {
        List<String> fileList = new Transponator().readInputFile(name);
        StringBuilder fileContent = new StringBuilder();
        for (String s : fileList) {
            fileContent.append(s);
            fileContent.append("\n");
        }
        return fileContent.toString();
    }

    @Test
    public void bazeTest() {
        //обычное транспонирование
        testArgs = new String[]{test1, "-o", output};
        Main.main(testArgs);
        expectedContent = "A D G \n" +
                "B E H \n" +
                "C F I \n";
        actualContent = getContent(output);
        Assert.assertEquals(expectedContent, actualContent);

        //транспонирование матрицы с пробелами между элементами
        testArgs = new String[]{test2, "-o", output};
        Main.main(testArgs);
        expectedContent = "A D \n" +
                "B E \n" +
                "C F \n";
        actualContent = getContent(output);
        Assert.assertEquals(expectedContent, actualContent);
    }

    @Test
    public void testWithtCut() {
        //обрезание по левому краю на 3
        testArgs = new String[]{test3, "-o", output, "-a", "3", "-t"};
        Main.main(testArgs);
        expectedContent = "A   F   K   \n" +
                "B   G   L   \n" +
                "C   H   M   \n" +
                "D   I   N   \n" +
                "E   J   \n";
        actualContent = getContent(output);
        Assert.assertEquals(expectedContent, actualContent);

        //обрезание по правому краю на 5
        testArgs = new String[] {test3, "-o", output, "-a", "5", "-t", "-r"};
        Main.main(testArgs);
        expectedContent = "    A     F     K \n" +
                "    B     G     L \n" +
                "    C     H     M \n" +
                "    D     I     N \n" +
                "    E     J \n";
        actualContent = getContent(output);
        Assert.assertEquals(expectedContent, actualContent);

        //дефолтное обрезание
        testArgs = new String[] {test3, "-o", output, "-t", "-r"};
        Main.main(testArgs);
        expectedContent = "         A          F          K \n" +
                "         B          G          L \n" +
                "         C          H          M \n" +
                "         D          I          N \n" +
                "         E          J \n";
        actualContent = getContent(output);
        Assert.assertEquals(expectedContent, actualContent);
    }


}
