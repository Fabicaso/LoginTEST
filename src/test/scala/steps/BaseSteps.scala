package steps

import com.typesafe.scalalogging.StrictLogging
import cucumber.api.scala.{EN, ScalaDsl}
import itv.fulfilmentplanning.ext.{WebBrowserExt, WebDriverOps}
import itv.fulfilmentplanning.pageobjects.Config
//import itv.fulfilmentplanning.pageobjects.{CommonPageObject, Config}
import org.scalactic.TypeCheckedTripleEquals
import org.scalatest.concurrent.Eventually
import org.scalatest.selenium.WebBrowser
import org.scalatest.{Assertions, Inspectors, Matchers}

trait BaseSteps
  extends ScalaDsl
    with EN
    with WebBrowserExt
    with WebBrowser
    with WebDriverOps
    with Matchers
    with Inspectors
    with Eventually
    with Assertions
   // with CommonPageObject
    with TypeCheckedTripleEquals
    with StrictLogging {
  val config = Config.config

}
