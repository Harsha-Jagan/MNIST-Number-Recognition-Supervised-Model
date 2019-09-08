package my_Mnist_Data;

import java.io.File;
import java.io.IOException;
public class My_Mnist_Main {
    
    public String path = new File("").getAbsolutePath();
    public MnistImageFile trainImages, testImages;
    public MnistLabelFile trainLabels, testLabels;
 
    public My_Mnist_Main() throws IOException{
        this.trainImages = new MnistImageFile(this.path + "\\res\\train-images.idx3-ubyte", "rw");
        this.trainLabels = new MnistLabelFile(this.path + "\\res\\train-labels.idx1-ubyte", "rw");
        
        this.testImages = new MnistImageFile(this.path + "\\res\\t10k-images.idx3-ubyte", "rw");
        this.testLabels = new MnistLabelFile(this.path + "\\res\\t10k-labels.idx1-ubyte", "rw");
    }
    
    public double[] createTrainInputs() throws IOException{
        double[] image = new double[28*28];
        for(int a=0; a<(28*28); a++){
            image[a] = trainImages.read() / (double)255;
        }
        return image;
    }
    
    public double[] createTestInputs() throws IOException{
        double[] image = new double[28*28];
        for(int a=0; a<(28*28); a++){
            image[a] = testImages.read() / (double)255;
        }
        
        return image;
    }
    
    public double[] createTrainExpOut() throws IOException{
        double[] label = new double[10];
        label[trainLabels.readLabel()] = 1;
  
        return label;
    }
    
    public double[] createTestExpOut() throws IOException{
        double[] label = new double[10];
        label[testLabels.readLabel()] = 1;
  
        return label;
    }
    
    public static void displayTrainSet(int num) throws IOException{
        String path = new File("").getAbsolutePath();
        MnistImageFile images = new MnistImageFile(path + "\\res\\train-images.idx3-ubyte", "rw");
        MnistLabelFile labels = new MnistLabelFile(path + "\\res\\train-labels.idx1-ubyte", "rw");
        
        for(int a=0; a<num; a++){
            images.next();
            labels.next();
        }
        
        System.out.println("Train #"+num+"\n");
        System.out.println("Label: "+labels.readLabel());
        
        for(int r=0; r<28; r++){
            for(int c=0; c<28; c++){
                System.out.printf("%3d ",images.read());
            }
            System.out.println();
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------");
    }
    
    public static void displayTestSet(int num) throws IOException{
        String path = new File("").getAbsolutePath();
        MnistImageFile images = new MnistImageFile(path + "\\res\\t10k-images.idx3-ubyte", "rw");
        MnistLabelFile labels = new MnistLabelFile(path + "\\res\\t10k-labels.idx1-ubyte", "rw");
        
        for(int a=0; a<num; a++){
            images.next();
            labels.next();
        }
        
        System.out.println("Test #"+num+"\n");
        System.out.println("Label: "+labels.readLabel());
        
        for(int r=0; r<28; r++){
            for(int c=0; c<28; c++){
                System.out.printf("%3d ",images.read());
            }
            System.out.println();
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------");
    }
    
    public static void displayAllTrain() throws IOException{
        String path = new File("").getAbsolutePath();
        MnistImageFile images = new MnistImageFile(path + "\\res\\train-images.idx3-ubyte", "rw");
        MnistLabelFile labels = new MnistLabelFile(path + "\\res\\train-labels.idx1-ubyte", "rw");
        
        for(int a=0; a<60000; a++){
            System.out.println("Train #"+a+"\n");
            System.out.println("Label: "+labels.readLabel());

            for(int r=0; r<28; r++){
                for(int c=0; c<28; c++){
                    System.out.printf("%3d ",images.read());
                }
                System.out.println();
            }
            System.out.println("----------------------------------------------------------------------------------------------------------------");
        }
    }
    
    public static void displayAllTest() throws IOException{
        String path = new File("").getAbsolutePath();
        MnistImageFile images = new MnistImageFile(path + "\\res\\t10k-images.idx3-ubyte", "rw");
        MnistLabelFile labels = new MnistLabelFile(path + "\\res\\t10k-labels.idx1-ubyte", "rw");

        for(int a=0; a<10000; a++){
            System.out.println("Test #"+a+"\n");
            System.out.println("Label: "+labels.readLabel());

            for(int r=0; r<28; r++){
                for(int c=0; c<28; c++){
                    System.out.printf("%3d ",images.read());
                }
                System.out.println();
            }
            System.out.println("----------------------------------------------------------------------------------------------------------------");
        }
    }
}