import java.io.IOException;
import java.util.*;


public class Main {

    public static void main(String[] args) throws IOException {

        int [][] MatrixConfusion={{0,0,0},
                {0,0,0},
                {0,0,0}};
        String filePath = "C:\\Program Files\\Weka-3-9\\data\\iris.arff"; // chemin vers le fichier
        FichierVersListeInstances reader = new FichierVersListeInstances();
        List<Instance> instances = reader.File2list(filePath);

        for (Instance instance : instances) {
          //  System.out.println(Arrays.toString(instance.getAttributs()) + " " + instance.getValeurClasse());
        }

        Collections.shuffle(instances);
        for (Instance instance : instances) {
           // System.out.println(Arrays.toString(instance.getAttributs()) + " - " + instance.getValeurClasse());
        }

        // Calculer la taille de la liste pour l'apprentissage et pour les tests
        int size = instances.size();
        int trainingSize = (int) (size * 0.8);
        int testSize = size - trainingSize;

        // Diviser les éléments en deux listes : une pour l'apprentissage et une pour les tests
        List<Instance> trainingSet = instances.subList(0, trainingSize);
        List<Instance> testSet = instances.subList(trainingSize, size);

        // Afficher le nombre d'instances dans chaque liste
        System.out.println("Nombre d'instances pour l'apprentissage : " + trainingSet.size());
        System.out.println("Nombre d'instances pour les tests : " + testSet.size());

        ArrayList<KNN> tables;
        List<KNN> results;
        for(Instance instance: testSet){
            tables = new ArrayList<KNN>();
            for(Instance e: trainingSet){
                KNN a=e.getDistance(e,instance);
                tables.add(a);
            }
            results= KNN.trierInstance(tables,3);

            var occurence = new HashMap<String,Integer>();
            for (KNN m:results){
                if (occurence.containsKey(m.getNameClass())){
                    var ancien = occurence.get(m.getNameClass());
                    var nouveau = ancien+1;
                    occurence.put(m.getNameClass(),nouveau);
                }
                else occurence.put(m.getNameClass(),1);
            }

            String classMAjorante = occurence.entrySet()
                    .stream()
                    .max(Map.Entry.comparingByValue())
                    .get()
                    .getKey();
            System.out.println("classe predite: " + classMAjorante+" class reel : "+instance.getValeurClasse());

        if(instance.getValeurClasse().equals(classMAjorante)){
            if(classMAjorante.equals("Iris-setosa")){
                MatrixConfusion[0][0]++;
            }
            if(classMAjorante.equals("Iris-versicolor")){
                MatrixConfusion[1][1]++;
            }
            if(classMAjorante.equals("Iris-virginica")){
                MatrixConfusion[2][2]++;
            }}

            if(instance.getValeurClasse().equals("Iris-setosa")&&classMAjorante.equals("Iris-versicolor")){
                MatrixConfusion[0][1]++;
            }
            if(instance.getValeurClasse().equals("Iris-setosa")&&classMAjorante.equals("Iris-virginica")){
                MatrixConfusion[0][2]++;
            }
            if(instance.getValeurClasse().equals("Iris-versicolor")&&classMAjorante.equals("Iris-setosa")){
                MatrixConfusion[1][0]++;
            }
            if(instance.getValeurClasse().equals("Iris-versicolor")&&classMAjorante.equals("Iris-virginica")){
                MatrixConfusion[1][2]++;
            }
            if(instance.getValeurClasse().equals("Iris-virginica")&&classMAjorante.equals("Iris-setosa")){
                MatrixConfusion[2][0]++;
            }
            if(instance.getValeurClasse().equals("Iris-virginica")&&classMAjorante.equals("Iris-versicolor")){
                MatrixConfusion[2][1]++;
            }
        }
        for (int i = 0; i < MatrixConfusion.length; i++) {
            // iterate through each column
            for (int j = 0; j < MatrixConfusion[i].length; j++) {
                System.out.print(MatrixConfusion[i][j] + " ");
            }
            System.out.println(); // move to the next line after each row
        }
        System.out.println();
        double Taux_Err=KNN.TR(MatrixConfusion);
        double Exact=KNN.Exactitude(MatrixConfusion);
        double prec=KNN.Precision(2,MatrixConfusion);
        double recall=KNN.Recall(2,MatrixConfusion);
        double mesure=KNN.FMesure(2,MatrixConfusion);
        System.out.println("le taux d'erreur est:"+Taux_Err);
        System.out.println("L'exactitude est:"+Exact);
        System.out.println("La precision est:"+prec);
        System.out.println("Le rappel est:"+recall);
        System.out.println("Le F-Mesure est:"+mesure);
        }


}
