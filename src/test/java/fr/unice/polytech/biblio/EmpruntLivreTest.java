package fr.unice.polytech.biblio;

import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/features")
public class EmpruntLivreTest { // will run all features found on the classpath in the same package as this class
}
