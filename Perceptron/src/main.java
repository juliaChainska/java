//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//
//public class main {
//    public static void main(String[] args) throws IOException {
//
//        List<Data> nodeTrainList;
//        List<Data> nodeTestList;
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("Podaj stała uczenia:");
//        double alpha = Double.parseDouble(bufferedReader.readLine());
//
//        System.out.println("Nowy wektor? [yes, no]");
//        String czy = bufferedReader.readLine();
//        String trainSetAddress = "/Users/juliachainska/Documents/Studia/inf/4SEM/nai/zad2/src/perceptron.data";
//        String testSetAddress = "/Users/juliachainska/Documents/Studia/inf/4SEM/nai/zad2/src/perceptron.test.data";
//
//        nodeTrainList = getNodeList(trainSetAddress);
//
//
//        int n=0;
//        HashMap<String,Integer> answerMap = new HashMap<>();
//        for (Data data : nodeTrainList) {
//            if (!answerMap.containsKey(data.getNodeClassName()))
//                answerMap.put(data.getNodeClassName(),n++);
//
//            if (answerMap.size()==2)
//                break;
//        }
//
////        for (Data data : nodeTrainList) {
////            System.out.println(data);
////        }
//
//        Collections.shuffle(nodeTrainList);
//        Perceptron perceptron = new Perceptron(nodeTrainList.get(0).getAttributesColumn().size(),alpha);
//        for (Data value : nodeTrainList)
//            perceptron.learn(value, answerMap.get(value.getNodeClassName()));
//
//        if (czy.equals("yes")) {
//            while (true) {
//                System.out.println("Wpisz wektor [split with \";\"]");
//
//                String line = bufferedReader.readLine();
//                line += ";";
//                List<Data> nodeSet = new ArrayList<>();
//                String[] tmp = line.split(";");
//                List<Double> attributesColumn = new ArrayList<>();
//
//                for (int i = 0; i < tmp.length - 1; i++)
//                    attributesColumn.add(Double.parseDouble(tmp[i]));
//
//                nodeSet.add(new Data(attributesColumn, tmp[tmp.length - 1]));
//                nodeTestList = nodeSet;
//
//
//
//                for (Data node : nodeTestList) {
//                    int y = perceptron.evaluate(node);
//                    for (String key :answerMap.keySet())
//                        if (answerMap.get(key)==y)
//                            System.out.println("Odp: "+ key+"\n");
//
//                }
//            }
//        }
//        else{
//            nodeTestList = getNodeList(testSetAddress);
//
//            n=0;
//            String[] keyArray = new String[2];
//            for (String key :answerMap.keySet())
//                keyArray[n++]=key;
//
//            int numberOfCorrectAnswerOfFirstClass = 0;
//            int numberOfCorrectAnswerOfSecondClass = 0;
//            int numberOfAppearancesOfFirstClass = 0;
//            int numberOfAppearancesOfSecondClass = 0;
//
//            for (Data node : nodeTestList) {
//                int y = perceptron.evaluate(node);
//
//                if (answerMap.get(node.getNodeClassName()) == 0)
//                    numberOfAppearancesOfFirstClass++;
//
//                if (answerMap.get(node.getNodeClassName()) == 1)
//                    numberOfAppearancesOfSecondClass++;
//
//                if (answerMap.get(keyArray[0]) == y && y == answerMap.get(node.getNodeClassName()))
//                    numberOfCorrectAnswerOfFirstClass++;
//
//                if (answerMap.get(keyArray[1]) == y && y == answerMap.get(node.getNodeClassName()))
//                    numberOfCorrectAnswerOfSecondClass++;
//            }
//
//            System.out.println("Accuracy for "+ keyArray[0]+ ": "+ ((double) numberOfCorrectAnswerOfFirstClass/numberOfAppearancesOfFirstClass)*100+"%");
//            System.out.println("Accuracy for "+ keyArray[1]+ ": "+ (double) numberOfCorrectAnswerOfSecondClass/numberOfAppearancesOfSecondClass*100+"%");
//            System.out.println("Accuracy: "+ (double) (numberOfCorrectAnswerOfFirstClass+numberOfCorrectAnswerOfSecondClass)/nodeTestList.size()*100+"%");
//            System.out.println("Final Vector: "+perceptron.getVectorW()+ " Theta threshold: "+ perceptron.getThetaThreshold());
//        }
//    }
//
//
//
//    public static List<Data> getNodeList(String fileAddress) throws IOException {
//        String line;
//        List<Data> nodeSet = new ArrayList<>();
//
//        FileReader fileReader = new FileReader(fileAddress);
//        BufferedReader bufferedReader = new BufferedReader(fileReader);
//
//        while ((line = bufferedReader.readLine()) != null && (!line.equals(""))) {
//            int index = 15;
//            char ch = ';';
//            StringBuilder string = new StringBuilder(line);
//            string.setCharAt(index, ch);
//            line = string.toString();
//
//            List<Double> attributesColumn = new ArrayList<>();
//            String[] tmp1 = line.split(";");
//            String[] tmp = new String[line.length()];
//            ArrayList<Double> att = new ArrayList<>();
//            for (int i = 0; i < tmp1.length - 1; i++) {
//                tmp = tmp1[i].split(",");
//
//                for (int j = 0; j <= 3; j++) {
//                    //System.out.println(tmp[j]);
//                    att.add(Double.parseDouble(tmp[j]));
//                    attributesColumn.add(Double.parseDouble(tmp[j]));
//                }
//            }
//            nodeSet.add(new Data(attributesColumn, tmp1[tmp1.length - 1]));
//            //System.out.println(att);
//        }
//        return nodeSet;
//    }
//}
//
//

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        System.out.println("Podaj stała uczenia:");
        double numOf = sc.nextDouble();

        System.out.println("Podaj ilosc iteracji:");
        int learningRate = sc.nextInt();

        Perceptron perceptron = new Perceptron(learningRate, numOf);
        Perceptron.run();

        while(true){
            System.out.println("Czy chcesz wpisac wlasny wektor? [y/n]");
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.next();

            if(answer.equals("n")) {
                break;
            }

            int columnCount = perceptron.getColumnCount();
            Scanner in3 = new Scanner(System.in);
            double[] vector = new double[6];
            for(int a = 0; a <= columnCount-1; a++) {
                System.out.print("Wpisz " + (a+1) + " liczbe: ");
                vector[a] = in3.nextDouble();
            }

            perceptron.testVector(vector);

        }



    }
}