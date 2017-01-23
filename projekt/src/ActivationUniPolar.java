import org.encog.engine.network.activation.ActivationFunction;
import org.encog.util.obj.ActivationUtil;

/**
 * Created by Sylwek on 2017-01-22.
 */
public class ActivationUniPolar implements ActivationFunction {
    private final double[] params = new double[0];
    @Override
    public void activationFunction(double[] x, int start, int size) {
        for(int i = start; i <start+size; ++i){
            if(x[i] > 0.0D){
                x[i] = 1.0D;
            } else {
                x[i] = 0.0D;
            }
        }
    }

    @Override
    public double derivativeFunction(double v, double v1) {
        return 1.0D;
    }

    @Override
    public boolean hasDerivative() {
        return true;
    }

    @Override
    public double[] getParams() {
        return this.params;
    }

    @Override
    public void setParam(int index, double value) {
        this.params[index] = value;
    }

    @Override
    public String[] getParamNames() {
        String[] result = new String[0];
        return result;
    }

    @Override
    public ActivationFunction clone() {
        return new ActivationUniPolar();
    }

    @Override
    public String getFactoryCode() {
        return ActivationUtil.generateActivationFactory("unipolar",this);
    }
}
