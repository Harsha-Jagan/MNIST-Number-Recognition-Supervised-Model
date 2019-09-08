package sgd_number_recognition_2;

import java.io.FileNotFoundException;
import my_Mnist_Data.My_Mnist_Main;

public final class Test {
    private NeuralNetwork brain;
    private int numTest;
    private int count = 0;
    private Matrix in, expOut, roundedOut;
    private My_Mnist_Main readers;
    
//Constructor
    public Test(NeuralNetwork bot, int numTest, My_Mnist_Main readers) throws FileNotFoundException{
        this.numTest = numTest;
        this.brain = bot;
        this.readers = readers;
        
        for(int a=0; a<this.numTest; a++){
            in = getInputs();
            expOut = getExpOut();
            
            feedForward();
            
          //  displaySet(a);
            
            roundedOut = brain.getOutputNodes().round();
            if(roundedOut.isEqualTo(expOut)){
                count++;
            }else{
                displaySet(a);
            }
        }
        
        displayPercentRight();
    }
    
//Inputs and Outputs
    public Matrix getInputs(){
        try{
            double[] arr = readers.createTestInputs();
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
            double[] arr = readers.createTestExpOut();
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
    
//Process
    public void feedForward(){
        brain.nodeLayers[0] = in;
        for(int r=0; r<brain.nodeLayers.length-1; r++){
            Matrix dot = brain.weightLayers[r].multiply(brain.nodeLayers[r]);
            Matrix bias = brain.bwLayers[r].multiply(brain.biasLayers[r]);
            
            Matrix z = dot.add(bias);
            brain.nodeLayers[r+1] = brain.sigmoid(z);
        }   
    }
    
//Display
    public void displaySet(int a){
        System.out.println("Test #"+a+"\n");
        
        System.out.print("Label: ");
        expOut.transpose().display();
        
        System.out.print("Guess: ");
        brain.getOutputNodes().transpose().display();
        
        displayInput();
        System.out.println("\n\n------------------------------------------------------------------------------------------------------------------------------------\n");
    }
    
    public void displayInput(){
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
    
    public void displayPercentRight(){
        System.out.println("\n"+((count*1.0/numTest)*100)+" percent correct of "+numTest+" test data");
    }
}