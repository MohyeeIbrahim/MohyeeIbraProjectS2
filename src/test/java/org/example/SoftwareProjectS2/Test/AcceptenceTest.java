package org.example.SoftwareProjectS2.Test;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.CucumberOptions.SnippetType;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "Feature-1", monochrome = true, snippets = SnippetType.CAMELCASE,
        glue = {"org.example.SoftwareProjectS2.Test"})
public class AcceptenceTest {

}
