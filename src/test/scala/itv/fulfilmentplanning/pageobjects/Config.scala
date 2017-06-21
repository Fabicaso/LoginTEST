package itv.fulfilmentplanning.pageobjects

import java.util.{Timer, TimerTask}

import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.StrictLogging
import itv.fulfilmentplanning.webdriver.WebDriverPool

import scala.concurrent.duration._
import scala.util.Try

case class Config(homePageUrl: String) //, database: Option[DatabaseConfig])

object ConfigRunner extends App with StrictLogging {

  //logger.info(s"${Config.config} loaded!")

}

object Config extends StrictLogging {

  val environment = Option(System.getProperty("env"))
  val useSeleniumGrid = Option(System.getProperty("useSeleniumGrid"))

   logger.info(s"Loading config for $environment ...")
   val configLoaded = environment.fold(ConfigFactory.load()) { fileToLoad =>
    ConfigFactory.load(s"$fileToLoad.conf")
  }
   val config = Config(configLoaded.getString("homePageUrl"))//, configLoaded.as[Option[DatabaseConfig]]("db"))

  //  config.database.foreach { database =>
//    logger.info(s"Cleaning data ...")
//    InitData.cleanData(database)
  // }

  logger.info(s"Creating web driver pool")
  val webDriverPool = WebDriverPool.pool(useSeleniumGrid.isDefined)
  Runtime.getRuntime.addShutdownHook(new Thread {
    override def run(): Unit = {
      ExitAfter(status = 0, duration = 1.seconds) {
        Try {
          logger.debug("Closing pool of chrome drivers")
          webDriverPool.close()
          logger.debug("Pool of chrome drivers closed!")
        }.recover {
          case e: Exception =>
            logger.error(s"Couldn't close the driver: ${e.getMessage}", e)
        }
      }
    }
  })
  logger.info("Config done!")
}

object ExitAfter extends StrictLogging {

  def apply(status: Int, duration: FiniteDuration)(f: => Unit): Unit = {
    try { // setup a timer, so if nice exit fails, the nasty exit happens
      logger.info(
        s"Set up exit application with status $status after $duration")
      val timer = new Timer()
      timer.schedule(new TimerTask() {
        def run(): Unit = {
          Runtime.getRuntime.halt(status)
        }
      }, duration.toMillis)
      f
      logger.info("Everything was ok!")
      System.exit(status)
    } catch {
      case ex: Throwable =>
        logger.error(s"Exit app with an exception", ex)
        // exit nastily if we have a problem
        Runtime.getRuntime.halt(status)
    } finally {
      // should never get here
      logger.info(s"Force the exit!")
      Runtime.getRuntime.halt(status)
    }
  }
}
