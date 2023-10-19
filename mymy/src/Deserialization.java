import java.io.*;

public class Deserialization {
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("main.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);
            TSPBruteForce deserializedTSP = (TSPBruteForce) ois.readObject();
            ois.close();
            fis.close();

            // You have the deserializedTSP object.
            // You can perform operations on it or access its methods and fields as needed.

            // For example, you can call the main method of the deserializedTSP object to execute its logic again.
            deserializedTSP.main(args);

            System.out.println("TSPBruteForce object has been deserialized.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
