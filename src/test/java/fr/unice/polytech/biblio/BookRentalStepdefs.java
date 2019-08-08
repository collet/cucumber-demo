package fr.unice.polytech.biblio;

import io.cucumber.java8.En;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BookRentalStepdefs implements En {

    Bibliotheque biblio = new Bibliotheque();
    Etudiant etudiant;
    Livre livre;

    public BookRentalStepdefs() { // implementation des steps dans le constructeur (aussi possible dans des méthodes)

        Given("a student of name {string} and with student id {int}",
                (String nomEtudiant, Integer noEtudiant) -> // besoin de refactorer int en Integer car utilisation de la généricité par Cucumber Java 8
                {
                    Etudiant etu = new Etudiant(biblio);
                    etu.setNom(nomEtudiant);
                    etu.setNoEtudiant(noEtudiant);
                    biblio.addEtudiant(etu);
                });
        And("a book of title {string}", (String titreLivre) -> {
            Livre liv = new Livre(biblio);
            liv.setTitre(titreLivre);
            biblio.addLivre(liv);
        });


        Then("There is {int} in his number of rentals", (Integer nbEmprunts) -> {
            assertEquals(nbEmprunts.intValue(),etudiant.getNombreDEmprunt());
        });


        When("{string} requests his number of rentals", (String nomEtudiant) -> {
            etudiant = biblio.getEtudiantByName(nomEtudiant);
        });

        When("{string} rents the book {string}", (String nomEtudiant, String titreLivre) -> {
            etudiant = biblio.getEtudiantByName(nomEtudiant);
            livre = biblio.getLivreByTitle(titreLivre);
            etudiant.emprunte(livre);
        });

        And("The book {string} is in a rental in the list of rentals", (String titreLivre) -> {
            assertTrue(etudiant.getEmprunt().stream().
                    anyMatch(emp -> emp.getLivreEmprunte().getTitre().equals(titreLivre)));
        });

        And("The book {string} is unavailable", (String titreLivre) -> {
            assertEquals(true, biblio.getLivreByTitle(titreLivre).getEmprunte());
        });
    }

}
