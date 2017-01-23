import org.encog.Encog;
import org.encog.engine.network.activation.ActivationBiPolar;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLDataSet;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;
import org.encog.neural.networks.training.simple.TrainAdaline;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Scanner;

/**
 * Created by Sylwek on 2016-12-11.
 */
public class Testy {
    public static void main(final String args[]) throws FileNotFoundException{
        File file = new File("Data.txt");
//        File file = new File("plik.txt");
        File test_File = new File("Data_test.txt");
//        File test_File = new File("test.txt");
        System.out.println("Loading data");
        System.out.println("Loading data");
        DataSet dataSet = new DataSet(file, test_File);
        double input[][] = dataSet.getInput();
        double outputt[][] = dataSet.getOutput();
        double test_in[][] = dataSet.getTest_input();
        double test_out[][] = dataSet.getTest_output();
        System.out.println("Dataset loaded");


        BasicNetwork network = new BasicNetwork();
        network.addLayer(new BasicLayer(null, true, 26));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 20));
//        network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 15));
//        network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 5));
        network.addLayer(new BasicLayer(new ActivationSigmoid(), true, 1));
        network.getStructure().finalizeStructure();
        network.reset();

        MLDataSet trainingSet = new BasicMLDataSet(input, outputt);
        final ResilientPropagation train = new ResilientPropagation(network, trainingSet);
//        TrainAdaline train = new TrainAdaline(network, trainingSet, 0.001);

        MLDataSet testSet = new BasicMLDataSet(test_in, test_out);

        File out = new File("out.txt");
        PrintWriter save = new PrintWriter(out);
        save.println("wynik");

        int epoch = 1;
        long startTimeLong = System.nanoTime();
        do {
            train.iteration();

            epoch++;
            double q = 0;
            for (MLDataPair pair : testSet) {
                final MLData output = network.compute(pair.getInput());

//                    System.out.println("actual= " + output.getData(0) + ", ideal= " + pair.getIdeal().getData(0));

//                if(output.getData(0) != pair.getIdeal().getData(0)) ++q;
                q+= Math.pow(output.getData(0) - pair.getIdeal().getData(0), 2);
            }
            save.println("Epoch #" + epoch + " Error: " + train.getError() + " " + q/(double)dataSet.getTest_size());
            if(epoch % 10 == 0){
                System.out.println("Epoch #" + epoch + " Error: " + train.getError() + " " + q/(double)dataSet.getTest_size());
            }
        } while (train.getError() > 0.1);
        train.finishTraining();

        save.close();

        long endTimeLong = System.nanoTime();
        double durationInSec = (double) ((endTimeLong - startTimeLong) / Math
                .pow(10, 9));

        int q = 0;
        System.out.println("Neural network results: ");
        for (MLDataPair pair : testSet) {
            final MLData output = network.compute(pair.getInput());
//            System.out.println("actual= " + output.getData(0) + ", ideal= " + pair.getIdeal().getData(0));
            if(output.getData(0) != pair.getIdeal().getData(0)) ++q;
        }
        System.out.println(dataSet.getTest_size() + "  "  + q);
        System.out.println("Finished evaluating in: " + durationInSec);
        Encog.getInstance().shutdown();
    }
}
