package fr.unice.polytech.biblio;

import io.cucumber.java8.Fr;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class EmpruntLivreStepdefs implements Fr { // implements En si vos scénarios sont écrits en anglais

    Bibliotheque biblio = new Bibliotheque();
    Etudiant etudiant;
    Livre livre;

    public EmpruntLivreStepdefs() { // implementation des steps dans le constructeur (aussi possible dans des méthodes)

        Etantdonné("un etudiant de nom {string} et de noEtudiant {int}",
                (String nomEtudiant, Integer noEtudiant) -> // besoin de refactorer int en Integer car utilisation de la généricité par Cucumber Java 8
                {
                    Etudiant etu = new Etudiant(biblio);
                    etu.setNom(nomEtudiant);
                    etu.setNoEtudiant(noEtudiant);
                    biblio.addEtudiant(etu);
                });

        Et("un livre de titre {string}", (String titreLivre) -> {
            Livre liv = new Livre(biblio);
            liv.setTitre(titreLivre);
            biblio.addLivre(liv);
            });


        Alors("Il y a {int} dans son nombre d'emprunts", (Integer nbEmprunts) -> {
            assertEquals(nbEmprunts.intValue(),etudiant.getNombreDEmprunt());
            });


        Quand("{string} demande son nombre d'emprunt", (String nomEtudiant) -> {
            etudiant = biblio.getEtudiantByName(nomEtudiant);
            });

        Quand("{string} emprunte le livre {string}", (String nomEtudiant, String titreLivre) -> {
            etudiant = biblio.getEtudiantByName(nomEtudiant);
            livre = biblio.getLivreByTitle(titreLivre);
            etudiant.emprunte(livre);
            });

        Et("Il y a le livre {string} dans un emprunt de la liste d'emprunts", (String titreLivre) -> {
            assertTrue(etudiant.getEmprunt().stream().
                    anyMatch(emp -> emp.getLivreEmprunte().getTitre().equals(titreLivre)));
            });

        Et("Le livre {string} est indisponible", (String titreLivre) -> {
            assertEquals(true, biblio.getLivreByTitle(titreLivre).getEmprunte());
            });
    }

}
