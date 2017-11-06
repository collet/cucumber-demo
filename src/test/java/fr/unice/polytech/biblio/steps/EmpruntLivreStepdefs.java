package fr.unice.polytech.biblio.steps;

import cucumber.api.java.en.*;
import fr.unice.polytech.biblio.Bibliotheque;
import fr.unice.polytech.biblio.Etudiant;
import fr.unice.polytech.biblio.Livre;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class EmpruntLivreStepdefs {

    Bibliotheque biblio = new Bibliotheque();
    Etudiant etudiant;
    Livre livre;

    @Given("^un etudiant de nom \"([^\"]*)\" et de noEtudiant (\\d+)$")
    public void unEtudiantDeNomEtDeNoEtudiant(String arg0, int arg1) throws Throwable {
        Etudiant etu = new Etudiant(biblio);
        etu.setNom(arg0);
        etu.setNoEtudiant(arg1);
        biblio.addEtudiant(etu);
    }


    @And("^un livre de titre \"([^\"]*)\"$")
    public void unLivreDeTitre(String arg0) throws Throwable {
        Livre liv = new Livre(biblio);
        liv.setTitre(arg0);
        biblio.addLivre(liv);
    }

    @When("^\"([^\"]*)\" demande son nombre d'emprunt$")
    public void demandeSonNombreDEmprunt(String arg0) throws Throwable {
        etudiant = biblio.getEtudiantByName(arg0);
    }

    @Then("^Il y (\\d+) dans son nombre d'emprunts$")
    public void ilYDansSonNombreDEmprunts(int arg0) throws Throwable {
        assertEquals(0,etudiant.getNombreDEmprunt());
    }

    @When("^\"([^\"]*)\" emprunte le livre \"([^\"]*)\"$")
    public void emprunteLeLivre(String arg0, String arg1) throws Throwable {
        etudiant = biblio.getEtudiantByName(arg0);
        livre = biblio.getLivreByTitle(arg1);
        etudiant.emprunte(livre);
    }

    @Then("^Il y a (\\d+) dans son nombre d'emprunts$")
    public void ilYADansSonNombreDEmprunts(int arg0) throws Throwable {
        assertEquals(arg0,etudiant.getNombreDEmprunt());
    }

    @And("^Il y a le livre \"([^\"]*)\" dans un emprunt de la liste d'emprunts$")
    public void ilYALeLivreDansUnEmpruntDeLaListeDEmprunts(String arg0) throws Throwable {
        assertTrue(etudiant.getEmprunt().stream().
                   anyMatch(emp -> emp.getLivreEmprunte().getTitre().equals(arg0)));
    }

    @And("^Le livre \"([^\"]*)\" est indisponible$")
    public void leLivreEstIndisponible(String arg0) throws Throwable {
        assertEquals(true, biblio.getLivreByTitle(arg0).getEmprunte());
    }

}
