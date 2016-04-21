/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dv;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
/**
 *
 * @author kefei
 */
public class DV {

    /**
     * @param args the command line arguments
     */
    int nodeNo;
    int edgeNo;
    int[][] edgetb;
    int[][] distancetb;
    int[][] hoptb;

    public void read_input (String path)throws FileNotFoundException{
        String[] edge;
        File input = new File(path);
        Scanner sc = new Scanner(input);
        int i=0, j =0;
        nodeNo = Integer.parseInt(sc.nextLine());
        edgeNo = Integer.parseInt(sc.nextLine());
        edgetb = new int[edgeNo][3];

        while(sc.hasNextLine()){
            edge = sc.nextLine().split(" ");
            for(j=0;j<3;j++){
                edgetb[i][j]= Integer.parseInt(edge[j]);
            }
            i++;
        }


    }

    public void find_sp(int dest, int[] hop, int[] distance){
        int[] bftb = new int[nodeNo];
        int toNode, fromNode, cost;

        for(int j=0; j<nodeNo;j++){
            if(j == dest) {
                bftb[j] =0;
            }else{
                bftb[j]= 99;
            }

        }


        for(int i=1; i< nodeNo; i++){
            for(int k=0;k< edgeNo; k++){
                fromNode = edgetb[k][0];
                toNode= edgetb[k][1];
                cost = edgetb[k][2];
                if((bftb[toNode]+ cost) < bftb[fromNode]){
                    bftb[fromNode]= bftb[toNode]+ cost;
                    hop[fromNode] = toNode;
                }
            }

        }
        for(int i=0;i< nodeNo; i++){
            distance[i]= bftb[i];
        }

    }

    public void update(){
        distancetb = new int[nodeNo][nodeNo];
        hoptb = new int[nodeNo][nodeNo];
        for(int i=0;i< nodeNo; i++){
           find_sp(i, hoptb[i], distancetb[i]);
        }
    }
    public void routingtb_print(){
        for(int i =0; i< nodeNo; i++){
            System.out.printf("node %d\n", i);
            for(int j=0; j< nodeNo;j++){
                System.out.printf("%d %d %d\n",j,hoptb[j][i], distancetb[j][i]);
            }

        }

    }
    public static void main(String[] args) {
        DV routing_tb = new DV();
        String file_path = args[0];
        try{
            routing_tb.read_input(file_path);
            routing_tb.update();
            routing_tb.routingtb_print();
        }
        catch(FileNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

}
