# MNIST-Digit-Recognition-Supervised-Model

# Description:
  This repository contains a supervised learning Neural Network that implements stochastic gradient descent to learn to recognize handwritten digits from the MNIST dataset. This project does not use any external Neural Network libraries such as Tensorflow. Instead, I wrote all the mathematical functions myself in order to gain a better understanding of how an artificially intelligent being thinks.  

# Organization:
  There are 3 main components in this project:
  
    1. res.zip
      This compressed zip folder contains the MNIST image and label files that were used to train and test the Neural Network.

    2. my_Mnist_Data:
      This folder contains files that translate Mnist data files into input matrices that are fed into the Neural Network.

    3. sgd_number_recognition:
      This is the folder where all the relevant Neural Network files are stored
      a) Matrix.java
        A Data Structure that is used to simplify the complex mathematical calculations that take place when a Neural Network runs.
      b) NeuralNetwork.java
        Uses Matrix.java to serve as a template for NeuralNetwork objects
      c) Train.java
        Trains the Neural Network objects with training data from MNIST
      d) Test.java
        Tests the Neural Network objects with testing data from MNISt
      e) Main.java
        Main class for this project

# Credits:
The files inside the res.zip were downloaded from the MNIST Database: http://yann.lecun.com/exdb/mnist/.

The following four files were taken from Finn Eggers's youtube video (https://www.youtube.com/watch?v=_O2rlikBHcA) description and were used to process the Mnist data files:

1. MnistDbFile.java 
2. MnistImageFile.java 
3. MnistImageLoader.java 
4. MnistLabelFile.java 

All other files were written solely by the author of this repository, Harshavardhan Jagannathan.
