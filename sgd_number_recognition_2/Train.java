package sgd_number_recognition_2;

import java.io.FileNotFoundException;
import java.io.IOException;
import my_Mnist_Data.My_Mnist_Main;

public final class Train {
    private int numTrain;
    public NeuralNetwork brain;
    public double lr;
    private My_Mnist_Main readers;
    
    private Matrix[] error;
    private Matrix in, expOut;
    public double totMSE = 0;
    
//Constructor
    public Train(NeuralNetwork brain, int numTrain, double lr, My_Mnist_Main readers) throws FileNotFoundException, IOException {
        this.numTrain = numTrain;
        this.brain = brain;
        this.lr = lr;
        this.readers = readers;
        
        for(int a=0; a<this.numTrain; a++){
            in = getInputs();
            expOut = getExpOut();
            
            feedForward();
            calcError();
            backProp();
            
            int trials = 1000;
            if((a+1)%trials == 0){
                displayAvgMSE(trials);
            }
            
       //     displayCurrentSet(a);
        }
    }
    
//inputs and outputs
    public Matrix getInputs(){
        try{
            double[] arr = readers.createTrainInputs();
            
            Matrix ans = new Matrix(arr.length,1);
            for(int a=0; a<ans.length; a++){
                ans.set(a,0,arr[a]);
            }
            return ans;
            
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    
    public Matrix getExpOut(){
        try{
            double[] arr = readers.createTrainExpOut();
            Matrix ans = new Matrix(arr.length,1);
            for(int a=0; a<arr.length; a++){
                ans.set(a,0,arr[a]);
            }
            return ans;
            
        }catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
    
//Learning
    public void feedForward(){
        brain.nodeLayers[0] = in;
        for(int r=0; r<brain.nodeLayers.length-1; r++){
         
            Matrix dot = brain.weightLayers[r].multiply(brain.nodeLayers[r]);
            Matrix bias = brain.bwLayers[r].multiply(brain.biasLayers[r]);
            
            Matrix z = dot.add(bias);
            brain.nodeLayers[r+1] = brain.sigmoid(z);
        }   
    }
    
    public void calcError(){
        totMSE += meanSquaredError();
        createErrorFunc();
        propError();
    }
    
    public void createErrorFunc(){
        error = new Matrix[brain.size];
        for(int a=0; a<error.length; a++){
            error[a] = new Matrix(brain.nodeLayers[a].length, 1);
        }
    }
    
    public void propError(){
        Matrix dSigOut = brain.dSig(brain.getOutputNodes());
        Matrix diff = brain.getOutputNodes().subtract(expOut);
     
        error[error.length-1] = diff.hadamard(dSigOut);
        
        for(int a=error.length-2; a>=0; a--){
            Matrix prod = (brain.weightLayers[a].transpose()).multiply(error[a+1]);
            error[a] = brain.dSig(brain.nodeLayers[a]).hadamard(prod);
        }
    }
    
    public void backProp(){
        for(int a=0; a<brain.weightLayers.length; a++){
            Matrix change = brain.nodeLayers[a].multiply(error[a+1].transpose());
            change = change.transpose().multiply(lr);
            brain.weightLayers[a] = brain.weightLayers[a].subtract(change);
   
            Matrix bChange = error[a+1].multiply(lr);
            brain.bwLayers[a] = brain.bwLayers[a].subtract(bChange);
        }
    }
    
    public double meanSquaredError(){
        Matrix diff = expOut.subtract(brain.getOutputNodes());
        Matrix squared = diff.hadamard(diff);
        Matrix error = squared.multiply(0.5);
        
        double iMSE = 0;
        for(int r=0; r<error.length; r++){
            for(int c=0; c<error.width; c++){
                iMSE += error.get(r,c);
            }
        }
        return iMSE;
    }
    
//Display
    public void displayAvgMSE(int trials){
        double avgMSE = (double)totMSE/trials;
        totMSE = 0;
        
        System.out.println(avgMSE);
        
    }
    
    public void displayCurrentSet(int a){
        System.out.println("\nTrain #"+a+"\n");
        System.out.println("Label: ");
        displayTargets();
        System.out.println("Guess: ");
        displayGuesses();
        
        displayInputs();
        System.out.println("-----------------------------------------------------------------------------------------------------");
    }
    
    public void displayInputs(){
        for(int x=0; x<784; x++){
            if(brain.nodeLayers[0].get(x, 0)==0){
                System.out.print("0    ");
            }else{
                System.out.printf("%.2f ",brain.nodeLayers[0].get(x, 0));
            }
            if((x+1)%28==0){
                System.out.println();
            }
        }
    }
    
    public void displayTargets(){
        expOut.transpose().display();
    }
    
    public void displayGuesses(){
        brain.getOutputNodes().transpose().display();
    }
}