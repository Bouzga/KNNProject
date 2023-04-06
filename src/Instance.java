import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.lang.System.exit;

public class Instance {
    private  double[] attributs;
    private  String valeurClasse;
  public  Instance(){};
    public Instance(double[] attributs, String valeurClasse) {
        this.attributs = attributs;
        this.valeurClasse = valeurClasse;
    }

    public double[] getAttributs() {
        return attributs;
    }

    public void setAttributs(double[] attributs) {
        this.attributs = attributs;
    }

    public void setValeurClasse(String valeurClasse) {
        this.valeurClasse = valeurClasse;
    }

    public String getValeurClasse() {
        return valeurClasse;
    }

    public static KNN getDistance(Instance i1, Instance i2) {

        if (i1.attributs.length == 0 || i2.attributs.length == 0) {
         //   throw new IllegalArgumentException("Au moins l'un des attributs est vide !");
            System.out.println("Au moins l'un des attributs est vide !");
            exit(0);
        }
        int dimension = i1.attributs.length;
        double distance = 0;

        for (int i = 0; i < dimension; i++) {
            distance += Math.pow(i1.attributs[i]-i2.attributs[i], 2);
        }
        double res=Math.sqrt(distance);
        return new KNN(res,i1.valeurClasse);
    }






}
