import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by Sylwek on 2017-01-22.
 */
public class Main {

    public double[] generate(Scanner scanner, int o) throws FileNotFoundException{
        double[] frequencies = new double[26];
        int num_characters = 0;
        int[] letter_count = new int[26];
        char c = ' ';
        int u = 0;
        String s = "";

        while(scanner.hasNext() && u<o){
            s = scanner.next();
            s = s.toLowerCase();
            int q = s.length();
            for (int j = 0; j < q; ++j){
                c = s.charAt(j);
                if (c == 'ą') c = 'a';
                if (c == 'ć') c = 'c';
                if (c == 'ę') c = 'e';
                if (c == 'ł') c = 'l';
                if (c == 'ń') c = 'n';
                if (c == 'ó') c = 'o';
                if (c == 'ś') c = 's';
                if (c == 'ź') c = 'z';
                if (c == 'ż') c = 'z';
                if (c >= 'a'&&c <= 'z') {
                    letter_count[(int)c - (int)'a']++;
                    num_characters++;
                    u++;
                }

            }
        }
        for (int i=0; i<26; ++i){
            frequencies[i] = letter_count[i] / (double)num_characters;

        }
        return frequencies;
    }

    public static void main(final String args[]) throws FileNotFoundException{
        FileReader file_pol = new FileReader("tekst_pol.txt");
//        File file_pol = new File("tekst_pol.txt");
//        File file_eng = new File("tekst_eng.txt");
        FileReader file_eng = new FileReader("tekst_eng.txt");
        File data_file = new File("Data.txt");
        Scanner pol = new Scanner(file_pol);
        Scanner eng = new Scanner(file_eng);

        PrintWriter save = new PrintWriter(data_file);
        Main main = new Main();

        while (pol.hasNext()) {
            double[] tab = main.generate(pol, 50);
            double[] tab2 = main.generate(eng, 50);
            for (int i = 0; i < 26; ++i) {
                save.print(tab[i] + " ");
            }
            save.print(0);
            save.println();

            for (int i = 0; i < 26; ++i) {
                save.print(tab2[i] + " ");
            }
            save.print(1);
            save.println();
        }

        System.out.println("");
    }
}
