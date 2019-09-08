package sgd_number_recognition_2;

import static java.lang.Math.exp;

public class NeuralNetwork {
    public int numIn, numOut;
    public int[] hidNodes;
    public Matrix[] nodeLayers, weightLayers, biasLayers, bwLayers;
    public int size;
    
//Constructor
    public NeuralNetwork(int numIn, int[] hidNodes, int numOut){
        this.numIn = numIn;
        this.hidNodes = hidNodes;
        this.numOut = numOut;
        this.size = hidNodes.length + 2;
        
        setNodeFramework();
        setWeightFramework();
        setBiasFramework();
    }
    
//Helpers
    public Matrix getOutputNodes(){
        return nodeLayers[nodeLayers.length-1];
    }
    
    public Matrix getNodeLayer(int idx){
        return nodeLayers[idx];
    }
    
    public Matrix getWeightLayer(int leftLayer){
        return weightLayers[leftLayer];
    }
    
    public Matrix getBiasLayer(int leftLayer){
        return biasLayers[leftLayer];
    }
    
    public Matrix getBiasWeightLayer(int leftLayer){
        return bwLayers[leftLayer];
    }
    
//Setup
    public void setNodeFramework(){
        nodeLayers = new Matrix[size];
        
        nodeLayers[0] = new Matrix(numIn,1);
        for(int a=1; a<nodeLayers.length-1; a++){
            nodeLayers[a] = new Matrix(hidNodes[a-1],1);
        }
        nodeLayers[nodeLayers.length-1] = new Matrix(numOut,1);
    }
    
    public void setWeightFramework(){
        weightLayers = new Matrix[size-1];
        for(int a=0; a<weightLayers.length; a++){
            weightLayers[a] = new Matrix(nodeLayers[a+1].length, nodeLayers[a].length);
            weightLayers[a].randomize(-1,1);
        }
    }
    
    public void setBiasFramework(){
        biasLayers = new Matrix[size-1];
        bwLayers = new Matrix[size-1];
        for(int a=0; a<biasLayers.length; a++){
            biasLayers[a] = new Matrix(1,1);
            biasLayers[a].setAll(1);
            
            bwLayers[a] = new Matrix(nodeLayers[a+1].length,1);
            bwLayers[a].randomize(-1,1);
        }
    }

//Sigmoid
    public Matrix sigmoid(Matrix layer){
        Matrix ans = new Matrix(layer.length, layer.width);
        for(int r=0; r<layer.length; r++){
            for(int c=0; c<layer.width; c++){
                double val = 1.0/(1.0+exp(-1*layer.get(r,c)));
                ans.set(r,c,val); 
            }
        }
        return ans;
    }
    
    public Matrix dSig(Matrix sig){
        Matrix neg = sig.multiply(-1);
        Matrix sum = neg.add(1);
        Matrix ans = sig.hadamard(sum);
        return ans;
    }
    
    public double dSig(double sig){
        return (sig * (1-sig));
    }
    
    public Matrix invSig(Matrix layer){
        Matrix ans = new Matrix(layer.length, layer.width);
        for(int r=0; r<ans.length; r++){
            for(int c=0; c<ans.width; c++){
                double val = -1.0*Math.log((1.0/layer.get(r, c))-1.0);
                ans.set(r,c,val);
            }
        }
        return ans;
    }
    
//Display
    public void displayEverything(){
        System.out.println("Nodes: ");
        for(int r=0; r<size; r++){
            for(int c=0; c<nodeLayers[r].length; c++){
                System.out.print("| "+nodeLayers[r].get(c, 0)+" |");
            }
            System.out.println();
        }
        
        System.out.println("\nWeights: \n");
        for(int x=0; x<weightLayers.length; x++){
            System.out.println("Weights between "+x+" and "+(x+1));
            for(int r=0; r<weightLayers[x].length; r++){
                for(int c=0; c<weightLayers[x].width; c++){
                    System.out.print("| "+weightLayers[x].get(r, c)+" |");
                }
                System.out.println();
            }
            System.out.println("\n");
        }
        
        System.out.println("\nBiases: ");
        for(int x=0; x<biasLayers.length; x++){
            System.out.print("| "+biasLayers[x].get(0, 0)+" |");
        }
        System.out.println();
        
        System.out.println("\nBias Weights: \n");
        for(int x=0; x<bwLayers.length; x++){
            System.out.println("bwLayer: "+x);
            for(int r=0; r<bwLayers[x].length; r++){
                System.out.println("| "+bwLayers[x].get(r, 0)+" |");
            }
            System.out.println("\n");
        }
        
        System.out.println("------------------------------------------------------------------------------------");
    }
}