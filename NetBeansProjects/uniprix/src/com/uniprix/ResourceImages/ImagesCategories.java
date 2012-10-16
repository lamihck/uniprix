package com.uniprix.ResourceImages;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ImagesCategories {

    public List<String> getImagesCategorie() {
        // Mettre seulement les noms des images, dans la jsp mettre le chemin

        List<String> list_image = new ArrayList<String>();

        list_image.add("Telephone.jpg");
        list_image.add("Ordinateur.jpg");
        list_image.add("Tablette.jpg");
        list_image.add("Tondeuse.jpg");
        list_image.add("Electromenager.jpg");
        list_image.add("Accessoires.jpg");
        list_image.add("Disque Dur.jpg");
        list_image.add("Ecran.jpg");
        list_image.add("Lecteur.jpg");

        return list_image;
    }
}
