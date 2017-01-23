import org.encog.ConsoleStatusReportable;
import org.encog.Encog;
import org.encog.engine.network.activation.ActivationSigmoid;
import org.encog.ml.MLRegression;
import org.encog.ml.data.MLData;
import org.encog.ml.data.MLDataPair;
import org.encog.ml.data.MLDataSet;
import org.encog.ml.data.basic.BasicMLDataSet;

import org.encog.ml.data.versatile.NormalizationHelper;
import org.encog.ml.data.versatile.VersatileMLDataSet;
import org.encog.ml.data.versatile.columns.ColumnDefinition;
import org.encog.ml.data.versatile.columns.ColumnType;
import org.encog.ml.factory.MLMethodFactory;
import org.encog.ml.model.EncogModel;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.networks.training.propagation.resilient.ResilientPropagation;
import org.encog.util.csv.CSVFormat;
import org.encog.ml.data.versatile.sources.CSVDataSource;
import org.encog.ml.data.versatile.sources.VersatileDataSource;
import org.encog.util.csv.ReadCSV;
import org.encog.util.simple.EncogUtility;


import java.io.File;
import java.util.Arrays;

/**
 * Created by Sylwek on 2016-12-09.
 */
public class Source {

    public static void main(final String args[]){
//        File file = new File("plik.txt");
        File file = new File("Data.txt");
        VersatileDataSource source = new CSVDataSource(file, false, new CSVFormat('.',' '));
        VersatileMLDataSet data = new VersatileMLDataSet(source);

        data.defineSourceColumn("a",0, ColumnType.continuous);
        data.defineSourceColumn("b",1, ColumnType.continuous);
        data.defineSourceColumn("c",2, ColumnType.continuous);
        data.defineSourceColumn("d",3, ColumnType.continuous);
        data.defineSourceColumn("e",4, ColumnType.continuous);
        data.defineSourceColumn("f",5, ColumnType.continuous);
        data.defineSourceColumn("g",6, ColumnType.continuous);
        data.defineSourceColumn("h",7, ColumnType.continuous);
        data.defineSourceColumn("i",8, ColumnType.continuous);
        data.defineSourceColumn("j",9, ColumnType.continuous);
        data.defineSourceColumn("k",10, ColumnType.continuous);
        data.defineSourceColumn("l",11, ColumnType.continuous);
        data.defineSourceColumn("m",12, ColumnType.continuous);
        data.defineSourceColumn("n",13, ColumnType.continuous);
        data.defineSourceColumn("o",14, ColumnType.continuous);
        data.defineSourceColumn("p",15, ColumnType.continuous);
        data.defineSourceColumn("q",16, ColumnType.continuous);
        data.defineSourceColumn("r",17, ColumnType.continuous);
        data.defineSourceColumn("s",18, ColumnType.continuous);
        data.defineSourceColumn("t",19, ColumnType.continuous);
        data.defineSourceColumn("u",20, ColumnType.continuous);
        data.defineSourceColumn("v",21, ColumnType.continuous);
        data.defineSourceColumn("w",22, ColumnType.continuous);
        data.defineSourceColumn("x",23, ColumnType.continuous);
        data.defineSourceColumn("y",24, ColumnType.continuous);
        data.defineSourceColumn("z",25, ColumnType.continuous);
        ColumnDefinition outputColumn = data.defineSourceColumn("output", 26, ColumnType.continuous);
        data.analyze();

        data.defineSingleOutputOthersInput(outputColumn);
        EncogModel model = new EncogModel(data);
        model.selectMethod(data, MLMethodFactory.TYPE_FEEDFORWARD);
        model.setReport(new ConsoleStatusReportable());
        data.normalize();

        model.holdBackValidation(0.3, true, 1001);
        model.selectTrainingType(data);
        MLRegression bestMethod = (MLRegression) model.crossvalidate(5,true);

        System.out.println("Training error: " + EncogUtility.calculateRegressionError(bestMethod,
                model.getTrainingDataset()));
        System.out.println("Validation error: " + EncogUtility.calculateRegressionError(
                bestMethod, model.getValidationDataset()));
        NormalizationHelper helper = data.getNormHelper();
        System.out.println(helper.toString());
        System.out.println("Final model: " + bestMethod);

        ReadCSV csv = new ReadCSV(file, false, new CSVFormat('.',' '));
        String [] line = new String[26];
        MLData input = helper.allocateInputVector();
        while(csv.next()){
            StringBuilder result = new StringBuilder();
            for (int i = 0; i<26; ++i){
                line[i] = csv.get(i);
            }
            String correct = csv.get(26);
            helper.normalizeInputVector(line, input.getData(), false);
            MLData output = bestMethod.compute(input);
            String chosen =
                    helper.denormalizeOutputVectorToString(output)[0];
                    result.append(Arrays.toString(line));
                    result.append(" -> predicted: ");
                    result.append(chosen);
                    result.append("(correct: ");
                    result.append(correct);
                    result.append(")");
                    System.out.println(result.toString());
        }
        file.delete();
        Encog.getInstance().shutdown();
    }
}
