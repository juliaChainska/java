import java.util.List;

public class Data {

        private final List<Double> attributesColumn;
        private final String dataClassName;


        public Data(List<Double> attributesColumn, String irisClassName) {
            this.attributesColumn = attributesColumn;
            this.dataClassName = irisClassName;
        }


        @Override
        public String toString() {
            return "Node{" +
                    "attributesColumn=" + attributesColumn +
                    ", irisClassName='" + dataClassName + '\'' +
                    '}';
        }
    }

