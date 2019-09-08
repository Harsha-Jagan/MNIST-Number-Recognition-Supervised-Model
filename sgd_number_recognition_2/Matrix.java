package sgd_number_recognition_2;

import java.util.Arrays;

public class Matrix {
    private double[][] matrix;
    public int length, width;
    
    public Matrix(int rows, int cols){
        this.matrix = new double[rows][cols];
        this.length = matrix.length;
        this.width = matrix[0].length;
    }
    
    public void set(int row, int col, double val){
        matrix[row][col] = val;
    }
    
    public void setAll(double val){
        for(int r=0; r<length; r++){
            for(int c=0; c<width; c++){
                set(r,c,val);
            }
        }
    }
    
    public double get(int row, int col){
        return matrix[row][col];
    }

//add
    public Matrix add(Matrix x){
        if(isSameDimentions(x)){
            Matrix ans = new Matrix(length, width);
            for(int r=0; r<length; r++){
                for(int c=0; c<width; c++){
                    double sum = get(r,c) + x.get(r, c);
                    ans.set(r, c, sum);
                }
            }
            return ans;
        }else{
            System.out.println("Inputs for Matrix.add(Matrix x) were not of the same dimentions");
            System.exit(0);
            return null;
        }
    }
    
    public Matrix add(double num){
        Matrix ans = new Matrix(length, width);
        for(int r=0; r<length; r++){
            for(int c=0; c<width; c++){
                double sum = get(r,c) + num;
                ans.set(r, c, sum);
            }
        }
        return ans;
    }
 
//subtract
    public Matrix subtract(Matrix x){
        if(isSameDimentions(x)){
            Matrix ans = new Matrix(length, width);
            for(int r=0; r<length; r++){
                for(int c=0; c<width; c++){
                    double diff = get(r,c) - x.get(r, c);
                    ans.set(r, c, diff);
                }
            }
            return ans;
        }else{
            System.out.println("Inputs for Matrix.subtract(Matrix x) were not of the same dimentions");
            System.exit(0);
            return null;
        }
    }
    
    public Matrix subtract(double num){
        Matrix ans = new Matrix(length, width);
        for(int r=0; r<length; r++){
            for(int c=0; c<width; c++){
                double diff = get(r,c) - num;
                ans.set(r, c, diff);
            }
        }
        return ans;
    }
    
//multiply
    public Matrix hadamard(Matrix x){
        if(isSameDimentions(x)){
            Matrix ans = new Matrix(length, width);
            for(int r=0; r<length; r++){
                for(int c=0; c<width; c++){
                    double product = get(r,c) * x.get(r, c);
                    ans.set(r, c, product);
                }
            }
            return ans;
        }else{
            System.out.println("Hadamard has crashed");
            System.exit(0);
            return null;
        }
    }
    
    public Matrix multiply(Matrix x){
        if(width == x.length){
            Matrix ans = new Matrix(length, x.width);
            for(int r=0; r<length; r++){
                for(int b=0; b<x.width; b++){
                    double dot=0;
                    for(int c=0; c<width; c++){
                        dot += get(r,c) * x.get(c,b);
                    }
                    ans.set(r,b,dot);
                }
            }
            return ans;
        }else{
            System.out.println("Matrix.multiply(Matrix x) has crashed");
            System.exit(0);
            return null;
        }
    }
    
    public Matrix multiply(double num){
        Matrix ans = new Matrix(length, width);
        for(int r=0; r<length; r++){
            for(int c=0; c<width; c++){
                double product = get(r,c) * num;
                ans.set(r, c, product);
            }
        }
        return ans;
    }
    
//miscellaneous
    public void randomize(double start, double stop){
        double range = stop - start;
        for(int r=0; r<length; r++){
            for(int c=0; c<width; c++){
                matrix[r][c] = (Math.random()*range)+start;
            }
        }
    }
    
    public boolean isSameDimentions(Matrix x){
        if(length == x.length && width == x.width)
            return true;
        return false;
    }
    
    public Matrix transpose(){
        Matrix ans = new Matrix(width, length);
        for(int r=0; r<length; r++){
            for(int c=0; c<width; c++){
                double val = get(r,c);
                ans.set(c, r, val);
            }
        }
        return ans;
    }
    
    public Matrix round(){
        Matrix ans = new Matrix(length, width);
        for(int r=0; r<length; r++){
            for(int c=0; c<width; c++){
                double val = Math.round(get(r,c));
                ans.set(r,c,val);
            }
        }
        return ans;
    }
    
    public boolean isEqualTo(Matrix x){
        if(isSameDimentions(x)){
            for(int r=0; r<length; r++){
                for(int c=0; c<width; c++){
                    if(get(r,c) != x.get(r,c))
                        return false;
                }
            }
            return true;
        }
        else{
            System.out.println("isEqualTo() has crashed");
            System.exit(0);
            return false;
        }
    }
    
    public void display(){
        for(int r=0; r<length; r++){
            System.out.println(Arrays.toString(matrix[r]));
        }
        System.out.println();
    }
}