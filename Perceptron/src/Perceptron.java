import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Perceptron {
    private double[] weights;
    private double learningRate;

    private double theta; // progi

   static List<double[]> X_train = new ArrayList<>();
    static List<Integer> y_train = new ArrayList<>();

    public Perceptron(int numFeatures, double learningRate) {
        this.weights = new double[numFeatures];
        this.learningRate = learningRate;
        this.theta = Math.random(); // losowa inicjalizacja progów
        for (int i = 0; i < numFeatures; i++) {
            this.weights[i] = Math.random();
        }
    }

    public int predict(double[] x) {
        double activation = 0;
        for (int i = 0; i < x.length; i++) {
            activation += this.weights[i] * x[i];
        }
        activation += theta; // dodanie progu
        return activation >= 0 ? 1 : -1;
    }

    public void train(List<double[]> X, List<Integer> y, int numEpochs) {
        for (int epoch = 0; epoch < numEpochs; epoch++) {
            for (int i = 0; i < X.size(); i++) {
                double[] x = X.get(i);
                int predicted = this.predict(x);
                int target = y.get(i);
                if (predicted != target) {
                    double error = target - predicted;
                    theta += learningRate * error; // aktualizacja progu
                    for (int j = 0; j < x.length; j++) {
                        this.weights[j] += this.learningRate * error * x[j]; //aktualizacja wag
                    }
                }
            }
        }
    }

    public static void run() {
        String trainFile = "src/perceptron.data";
        String testFile = "src/perceptron.test.data";
        int numFeatures = 4;
        double learningRate = 0.01;
        int numEpochs = 1000;

        X_train = new ArrayList<>();
        y_train = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(trainFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                double[] x = new double[numFeatures];
                for (int i = 0; i < numFeatures; i++) {
                    x[i] = Double.parseDouble(values[i]);
                }
                String a = values[numFeatures];
                int y = 0;
                if(a=="Iris-versicolor"){
                    y = 0;
                } else {
                    y = 1;
                }

                X_train.add(x);
                y_train.add(y);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        Perceptron perceptron = new Perceptron(numFeatures, learningRate);
        perceptron.train(X_train, y_train, numEpochs);

        List<double[]> X_test = new ArrayList<>();
        List<Integer> y_test = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(testFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                double[] x = new double[numFeatures];
                for (int i = 0; i < numFeatures; i++) {
                    x[i] = Double.parseDouble(values[i]);
                }
                String a = values[numFeatures];
                int y = 0;
                if(a=="Iris-versicolor"){
                    y = 0;
                } else {
                    y = 1;
                }

                X_test.add(x);
                y_test.add(y);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int numCorrect = 0;
        for (int i = 0; i < X_test.size(); i++) {
            double[] x = X_test.get(i);
            int predicted = perceptron.predict(x);
            int target = y_test.get(i);
            if (predicted == target) {
                numCorrect++;
            }
        }
        double accuracy = (double) numCorrect / X_test.size();
        System.out.println("Dokładność : " + accuracy);

    }

        public static List<Data> getNodeList(String fileAddress) throws IOException {
        String line;
        List<Data> nodeSet = new ArrayList<>();

        FileReader fileReader = new FileReader(fileAddress);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        while ((line = bufferedReader.readLine()) != null && (!line.equals(""))) {
            int index = 15;
            char ch = ';';
            StringBuilder string = new StringBuilder(line);
            string.setCharAt(index, ch);
            line = string.toString();

            List<Double> attributesColumn = new ArrayList<>();
            String[] tmp1 = line.split(";");
            String[] tmp = new String[line.length()];
            ArrayList<Double> att = new ArrayList<>();
            for (int i = 0; i < tmp1.length - 1; i++) {
                tmp = tmp1[i].split(",");

                for (int j = 0; j <= 3; j++) {
                    //System.out.println(tmp[j]);
                    att.add(Double.parseDouble(tmp[j]));
                    attributesColumn.add(Double.parseDouble(tmp[j]));
                }
            }
            nodeSet.add(new Data(attributesColumn, tmp1[tmp1.length - 1]));
            //System.out.println(att);
        }
        return nodeSet;
    }

    public void testVector(double[] vector) {
        int prediction = predict(vector);
        System.out.println("Zaklasyfikowano wektor jako:");
        System.out.println((prediction == 1) ? "Iris-virginica" : "Iris-versicolor");
    }


    public int getColumnCount(){
        return X_train.size();
    }

    public void getGet() throws IOException {
        getNodeList("/Users/juliachainska/Documents/Studia/4sem/nai/Perceptron/zad2.3/src/perceptron.data");

    }
}