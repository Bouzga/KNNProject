import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FichierVersListeInstances {

    public static List<Instance> File2list(String cheminFichier) throws IOException {
        List<Instance> instances = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(cheminFichier));
        String ligne;
        boolean found=false;
        while ((ligne = br.readLine()) != null) {
            if(ligne.contains("@DATA"))
            {
                found=true;
                ligne = br.readLine();
            }
            if(found==true)
            if (!ligne.contains("@") && !ligne.contains("%")) {
                String[] valeurs = ligne.split(",");
                double[] attributs = new double[valeurs.length - 1];
                for (int i = 0; i < attributs.length; i++) {
                    attributs[i] = Double.parseDouble(valeurs[i]);
                }
                String valeurClasse = valeurs[valeurs.length - 1];
                Instance instance = new Instance(attributs, valeurClasse);
                instances.add(instance);
            }
        }
        br.close();

        return instances;
    }



}
