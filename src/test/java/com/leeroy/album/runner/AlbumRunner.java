package com.leeroy.album.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features="src/test/resources/features",
        glue= {"com.leeroy.album.steps"},
        plugin = {"pretty","json:target/cucumber.json" },
        monochrome=true
)
public class AlbumRunner {


}
