import java.io.File;

public class Hello {
    public static void main(String[] args) {
        System.out.println("Hello, world!");

        var configFile = new File("sandox2/build.gradle");
        System.out.println(configFile.getAbsolutePath());
        System.out.println(configFile.exists());
    }
}
