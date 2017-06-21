package itv.fulfilmentplanning.pageobjects

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith

object RunCucumberTest {}

@RunWith(classOf[Cucumber])
@CucumberOptions(
  features = Array("classpath:features"),
  glue = Array("classpath:steps"),
  plugin = Array("pretty", "json:target/cucumber-html-reports/first-json-report.json"),
  tags = Array("@login", "~@Ignore")
)
class FirstFeatureTestRunner {}

//@RunWith(classOf[Cucumber])
//@CucumberOptions(
//  features = Array("classpath:features"),
//  glue = Array("classpath:steps"),
//  plugin = Array("pretty", "json:target/cucumber-html-reports/second-json-report.json"),
//  tags = Array("@assetsstatus", "~@Ignore")
//)
//class SecondFeatureTestRunner {}
//
//@RunWith(classOf[Cucumber])
//@CucumberOptions(
//  features = Array("classpath:features"),
//  glue = Array("classpath:steps"),
//  plugin = Array("pretty", "json:target/cucumber-html-reports/third-json-report.json"),
//  tags = Array("@licenceStatus, @breadcrumbs", "~@Ignore")
//)
//class ThirdFeatureTestRunner {}
//
//@RunWith(classOf[Cucumber])
//@CucumberOptions(
//  features = Array("classpath:features"),
//  glue = Array("classpath:steps"),
//  plugin = Array("pretty", "json:target/cucumber-html-reports/fourth-json-report.json"),
//  tags = Array("@assetsdates", "~@Ignore")
//)
//class FourthFeatureTestRunner {}
//
//@RunWith(classOf[Cucumber])
//@CucumberOptions(
//  features = Array("classpath:features"),
//  glue = Array("classpath:steps"),
//  plugin = Array("pretty", "json:target/cucumber-html-reports/fifth-json-report.json"),
//  tags = Array("@currentRequests, @fulfilment", "~@Ignore")
//)
//class FifthFeatureTestRunner {}
//
//@RunWith(classOf[Cucumber])
//@CucumberOptions(
//  features = Array("classpath:features"),
//  glue = Array("classpath:steps"),
//  plugin = Array("pretty", "json:target/cucumber-html-reports/smoketest-json-report.json"),
//  tags = Array("@smokeTest", "~@Ignore")
//)
//class SmokeTestRunner {}
