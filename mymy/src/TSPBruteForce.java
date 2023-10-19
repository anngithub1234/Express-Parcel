import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class TSPBruteForce implements Serializable {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numCities;

        do {
            System.out.print("Enter the number of Locations including the company location (not greater than 8): ");
            numCities = scanner.nextInt();
        } while (numCities < 1 || numCities > 8); // Ensure numCities is within the range [1, 8]

            int[][] tspGraph = new int[numCities][numCities];

            // Create arrays to store customer names and parcel IDs
            Customer[] customers = new Customer[numCities];
            Parcel[] parcels = new Parcel[numCities];

            // Input distances between locations and collect customer and parcel info
            for (int i = 0; i < numCities; i++) {
                System.out.print("Enter Customer ID for " + i + ": ");
                int cusId = scanner.nextInt();
                System.out.print("Enter the name of the customer at Location " + i + ": ");
                String cusName = scanner.next();
                System.out.print("Town  " + i + ": ");
                String distri = scanner.next();
                System.out.print("Enter lat for City " + i + ": ");
                float locX = Float.parseFloat(scanner.next());
                System.out.print("Enter lat for City " + i + ": ");
                float locY = Float.parseFloat(scanner.next());
                customers[i] = new Customer(cusId, cusName, distri, locX, locY);

                System.out.print("Enter the parcel ID for Location " + i + ": ");
                int parcelId = scanner.nextInt();
                System.out.print("description  " + i + ": ");
                String dec = scanner.next();
                parcels[i] = new Parcel(parcelId, dec);

                for (int j = 0; j < numCities; j++) {
                    System.out.print("Enter the distance from Location " + i + " to Location " + j + ": ");
                    tspGraph[i][j] = scanner.nextInt();
                }
            }


        // Print the list of customers
        System.out.println("=====================================");
        System.out.println("List of all the  Customers:");
        for (int i = 0; i < numCities; i++) {
            Customer customer = customers[i];
            Parcel parcel = parcels[i];
            System.out.println("Customer ID: " + customer.getCusId());
            System.out.println("Customer Name: " + customer.getCusName());
            System.out.println("Town: " + customer.getDistri());
            System.out.println("Latitude: " + customer.getLocX());
            System.out.println("Longitude: " + customer.getLocY());
            System.out.println("Parcel ID: " + parcel.getParcelId());
            System.out.println("Description: " + parcel.getDec());
            System.out.println();
            System.out.println("=====================================");
        }
        // Set distances from a location to itself to 0
        for (int i = 0; i < numCities; i++) {
            tspGraph[i][i] = 0;
        }

        int[] tour = new int[numCities + 1]; // Include an extra element for returning to location 0
        boolean[] visited = new boolean[numCities];
        tour[0] = 0; // Start with location 0 (the company)
        visited[0] = true;
        int[] minCost = { Integer.MAX_VALUE }; // Use an array to hold the minimum cost

        // Find the optimal tour
        tspBruteForce(1, numCities, tspGraph, tour, visited, 0, minCost);

        // Set the last location to 0 to ensure the tour ends at location 0
        tour[numCities] = 0;

        // Print the result
        System.out.println("=====================================");
        System.out.println("Optimal Tour: " + Arrays.toString(tour));
        System.out.println("Minimum Cost: " + minCost[0]);
        System.out.println("=====================================");
        System.out.println("=====================================");


        // Display the delivery route
        System.out.println("Delivery Route:");
        for (int i = 0; i < numCities; i++) {
            int fromCity = tour[i];
            int toCity = tour[i + 1];
            Customer customer = customers[toCity];
            Parcel parcel = parcels[toCity];

            System.out.println("From " + customers[fromCity].getDistri() + " to " + customer.getDistri());
            System.out.println("Customer ID: " + customer.getCusId());
            System.out.println("Customer Name: " + customer.getCusName());
            System.out.println("Town: " + customer.getDistri());
            System.out.println("Latitude: " + customer.getLocX());
            System.out.println("Longitude: " + customer.getLocY());
            System.out.println("Parcel ID: " + parcel.getParcelId());
            System.out.println("Description: " + parcel.getDec());
            System.out.println();
        }
        scanner.close();
        TSPBruteForce tspBruteForce = new TSPBruteForce();


        try {
            FileOutputStream fos = new FileOutputStream("main.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tspBruteForce); // Serialize the current TSPBruteForce instance
            oos.close();
            fos.close();
            System.out.println("TSPBruteForce object has been serialized.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Recursive function to find the optimal tour
    private static void tspBruteForce(int depth, int numCities, int[][] tspGraph, int[] tour, boolean[] visited, int currentCost, int[] minCost) {
        if (depth == numCities) {
            // All locations have been visited. Ensure we return to the starting location (location 0).
            if (tspGraph[tour[numCities - 1]][0] != 0) {
                int totalCost = currentCost + tspGraph[tour[numCities - 1]][0];
                if (totalCost < minCost[0]) {
                    minCost[0] = totalCost;
                }
            }
        } else {
            for (int i = 1; i < numCities; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    tour[depth] = i;
                    currentCost += tspGraph[tour[depth - 1]][i];

                    // Recursively explore the next location
                    tspBruteForce(depth + 1, numCities, tspGraph, tour, visited, currentCost, minCost);

                    // Backtrack
                    visited[i] = false;
                    currentCost -= tspGraph[tour[depth - 1]][i];
                }
            }
        }
    }





}
