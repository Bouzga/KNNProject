import java.lang.reflect.Array;
import java.util.*;



public class KNN {
    private double dis;
    private String nameClass;

    public double getDis() {
        return dis;
    }

    public void setDis(double dis) {
        this.dis = dis;
    }

    public String getNameClass() {
        return nameClass;
    }

    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }

    public KNN(double dis, String nameClass) {
        this.dis = dis;
        this.nameClass = nameClass;
    }

    public static List<KNN> trierInstance(ArrayList<KNN> c, int k){
        Collections.sort(c, Comparator.comparingDouble(KNN::getDis));
        if(k > c.size()) k= c.size();
        return c.subList(0,k);



    }
    public static double  SommeMatrice(int [][] matriceConfusion){
        double somme=0;
        for(int i=0;i<matriceConfusion.length;i++){
            for(int j=0;j<matriceConfusion[i].length;j++){
                somme +=matriceConfusion[i][j];
            }
        }
        return somme;
    }

    public static double TR(int [][] matrice){
         double somme=0;
        for(int i=0;i< matrice.length;i++){
            for(int j=0;j< matrice.length;j++){

                if (i != j){
                somme +=matrice[i][j];}
            }
        }
        return somme/SommeMatrice(matrice);
    }

    public static double Exactitude(int [][] matrice){
        double somme=0;
        for(int i=0;i< matrice.length;i++){
            for(int j=0;j< matrice.length;j++){

                if (i == j){
                    somme +=matrice[i][j];}
            }
        }
        return somme/SommeMatrice(matrice);
    }


    public static double SommeColonne(int[][] ConfusionMatrix, int colonneIndex) {
        double som = 0;
        for (int i = 0; i < ConfusionMatrix.length; i++) {
            som += ConfusionMatrix[i][colonneIndex];
        }
        return som;
    }

    public static double SommeLigne(int[][] ConfusionMatrix, int ligneIndex) {
        double som = 0;
        for (int i = 0; i < ConfusionMatrix[ligneIndex].length; i++) {
            som += ConfusionMatrix[ligneIndex][i];
        }
        return som;
    }

  public static double Precision(int i,int[][] ConfusionMatrix){
        return ConfusionMatrix[i][i]/SommeLigne(ConfusionMatrix,i);

    }

    public static double Recall(int i,int[][] ConfusionMatrix){
        return ConfusionMatrix[i][i]/SommeColonne(ConfusionMatrix,i);

    }

    public static double FMesure(int i, int[][] ConfusionMatrix){
        double precision = Precision(i, ConfusionMatrix);
        double recall = Recall(i, ConfusionMatrix);
        double fMesure = 2 * precision * recall / (precision + recall);
        return fMesure;
    }




}





