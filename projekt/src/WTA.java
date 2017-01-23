import org.encog.engine.network.activation.ActivationFunction;
import org.encog.ml.data.MLData;
import org.encog.ml.data.versatile.VersatileMLDataSet;
import org.encog.ml.data.versatile.sources.CSVDataSource;
import org.encog.ml.data.versatile.sources.VersatileDataSource;
import org.encog.neural.networks.BasicNetwork;
import org.encog.neural.networks.layers.BasicLayer;
import org.encog.neural.som.SOM;
import org.encog.neural.som.training.basic.BasicTrainSOM;
import org.encog.neural.som.training.basic.neighborhood.NeighborhoodFunction;
import org.encog.util.csv.CSVFormat;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by Sylwek on 2016-12-19.
 */
public class WTA {

    public static void main(final String args[]) throws FileNotFoundException{



        NeighborhoodFunction neighborhoodFunction;
        SOM network = new SOM(26,26);
        File file = new File("plik.txt");
        File file1 = new File("test.txt");

//        DataSet dataSet = new DataSet(file,file1);

        BasicNetwork basicNetwork = new BasicNetwork();

        basicNetwork.addLayer(new BasicLayer(new ActivationUniPolar(), true, 26));
        
        VersatileDataSource source = new CSVDataSource(file, false, new CSVFormat('.',' '));
        VersatileMLDataSet data = new VersatileMLDataSet(source);




       // BasicTrainSOM train = new BasicTrainSOM(network,0.01, data);

    }
}
