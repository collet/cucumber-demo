package fr.unice.polytech.biblio;

import cucumber.api.java8.En;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class EmpruntLivreStepdefs implements En {

    Bibliotheque biblio = new Bibliotheque();
    Etudiant etudiant;
    Livre livre;

    public EmpruntLivreStepdefs() { // implementation des steps dans le constructeur (aussi possible dans des méthodes)

        Given("^un etudiant de nom \"([^\"]*)\" et de noEtudiant (\\d+)$",
                (String nomEtudiant, Integer noEtudiant) -> // besoin de refactorer int en Integer car utilisation de la généricité par Cucumber Java 8
                {
                    Etudiant etu = new Etudiant(biblio);
                    etu.setNom(nomEtudiant);
                    etu.setNoEtudiant(noEtudiant);
                    biblio.addEtudiant(etu);
                });

        And("^un livre de titre \"([^\"]*)\"$", (String titreLivre) -> {
            Livre liv = new Livre(biblio);
            liv.setTitre(titreLivre);
            biblio.addLivre(liv);
            });


        Then("^Il y a (\\d+) dans son nombre d'emprunts$", (Integer nbEmprunts) -> {
            assertEquals(nbEmprunts.intValue(),etudiant.getNombreDEmprunt());
            });


        When("^\"([^\"]*)\" demande son nombre d'emprunt$", (String nomEtudiant) -> {
            etudiant = biblio.getEtudiantByName(nomEtudiant);
            });

        When("^\"([^\"]*)\" emprunte le livre \"([^\"]*)\"$", (String nomEtudiant, String titreLivre) -> {
            etudiant = biblio.getEtudiantByName(nomEtudiant);
            livre = biblio.getLivreByTitle(titreLivre);
            etudiant.emprunte(livre);
            });

        And("^Il y a le livre \"([^\"]*)\" dans un emprunt de la liste d'emprunts$", (String titreLivre) -> {
            assertTrue(etudiant.getEmprunt().stream().
                    anyMatch(emp -> emp.getLivreEmprunte().getTitre().equals(titreLivre)));
            });

        And("^Le livre \"([^\"]*)\" est indisponible$", (String titreLivre) -> {
            assertEquals(true, biblio.getLivreByTitle(titreLivre).getEmprunte());
            });
    }

}
