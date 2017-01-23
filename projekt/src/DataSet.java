import java.io.File;
import java.io.FileNotFoundException;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by Sylwek on 2017-01-22.
 */
public class DataSet {
    private double[][] input;
    private double[][] output;
    private int size;
    private double[][] test_input;
    private double[][] test_output;
    private int test_size;
    private File file;
    private File test_file;
    Scanner scanner;

    public DataSet(File file, File test_file) throws FileNotFoundException {
        this.file = file;
        this.test_file = test_file;
        scanner = new Scanner(file);

        while (scanner.hasNextLine()){
            ++size;
            scanner.nextLine();
        }
        scanner.close();
        scanner = new Scanner(test_file);
        while(scanner.hasNextLine()){
            ++test_size;
            scanner.nextLine();
        }
        scanner.close();
        input = new double[size][26];
        output = new double[size][1];
        test_input = new double[test_size][26];
        test_output = new double[test_size][1];

        String string;
        scanner = new Scanner(file);
        int aa = 0;
        while (scanner.hasNextLine()){
            string = scanner.nextLine();
            Scanner sc = new Scanner(string).useLocale(Locale.ENGLISH);
            for (int i = 0; i<26; ++i){
                input[aa][i] = sc.nextDouble();
            }
            output[aa][0] = sc.nextDouble();
            ++aa;
        }
        scanner.close();

        scanner = new Scanner(test_file);
        aa = 0;

        while (scanner.hasNextLine()){
            string = scanner.nextLine();
            Scanner sc = new Scanner(string).useLocale(Locale.ENGLISH);
            for (int i = 0; i<26; ++i){
                test_input[aa][i] = sc.nextDouble();
            }
            test_output[aa][0] = sc.nextDouble();
            ++aa;
        }
        scanner.close();
    }

    public double[][] getTest_output() {
        return test_output;
    }

    public void setTest_output(double[][] test_output) {
        this.test_output = test_output;
    }

    public double[][] getTest_input() {
        return test_input;
    }

    public void setTest_input(double[][] test_input) {
        this.test_input = test_input;
    }

    public double[][] getOutput() {
        return output;
    }

    public void setOutput(double[][] output) {
        this.output = output;
    }

    public double[][] getInput() {
        return input;
    }

    public void setInput(double[][] input) {
        this.input = input;
    }

    public int getTest_size() {
        return test_size;
    }

    public void setTest_size(int test_size) {
        this.test_size = test_size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
