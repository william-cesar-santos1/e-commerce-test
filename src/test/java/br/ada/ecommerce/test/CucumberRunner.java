package br.ada.ecommerce.test;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/cucumber-reports/Cucumber.html", "pretty"},
        features = "src/test/resources/features"
)
public class CucumberRunner {

}