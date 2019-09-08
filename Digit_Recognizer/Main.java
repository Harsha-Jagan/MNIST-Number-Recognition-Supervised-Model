package sgd_number_recognition_2;

import java.io.IOException;
import java.util.Scanner;
import my_Mnist_Data.My_Mnist_Main;

public class Main {
    public static int numTrain = 1000;
    public static int numTest = 100;
    public static double lr = 0.50;
    public static My_Mnist_Main readers;
    public static int numIn, numOut;
    public static int[] hidNodes;
    
    public static void main(String[] args) throws IOException {
    
        readers = new My_Mnist_Main();
      
   //   manualRun();
      
        autoRun();
    }
    
    public static void manualRun() throws IOException{
        setUpNetwork();
      
        NeuralNetwork network = new NeuralNetwork(numIn, hidNodes, numOut);
        NeuralNetwork network2 = network;
 
        Train training = new Train(network, numTrain, lr, readers);
 
        for(int b=0; b<network.weightLayers.length; b++){
            System.out.println(network2.weightLayers[b].isEqualTo(training.brain.weightLayers[b]));
        }
        
    //    Test testing = new Test(training.brain, numTest, readers);
    }
    
    public static void autoRun() throws IOException{
        int numIn = 784, numOut = 10;
        int[] hidNodes = {70,35};
        
        NeuralNetwork network = new NeuralNetwork(numIn, hidNodes, numOut);
        NeuralNetwork network2 = network;

        Train training = new Train(network, numTrain, lr, readers);
        
    
        Test testing = new Test(training.brain, numTest, readers);
    }
    
    public static void setUpNetwork(){
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Number of Inputs :: ");
        numIn = scan.nextInt();
        
        System.out.print("Number of Hidden Layers :: ");
        int numHid = scan.nextInt();
        hidNodes = new int[numHid];
        for(int a=0; a<numHid; a++)
        {
            System.out.print("\tNumber of nodes in Hidden Layer "+(a+1)+" :: ");
            hidNodes[a] = scan.nextInt();
        }
        
        System.out.print("Number of Outputs :: ");
        numOut = scan.nextInt();
    }
}
