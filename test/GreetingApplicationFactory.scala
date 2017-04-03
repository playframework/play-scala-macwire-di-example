import org.scalatestplus.play.FakeApplicationFactory
import play.api._
import play.core.DefaultWebCommands

trait GreetingApplicationFactory extends FakeApplicationFactory {

  /**
   * An application builder for running an application in tests
   */
  class GreetingApplicationBuilder {

    def build(): Application = {
      val env = Environment.simple()
      val context = ApplicationLoader.Context(
        environment = env,
        sourceMapper = None,
        webCommands = new DefaultWebCommands(),
        initialConfiguration = Configuration.load(env)
      )
      val loader = new GreetingApplicationLoader()
      loader.load(context)
    }
  }

  override def fakeApplication(): Application = {
    new GreetingApplicationBuilder().build()
  }
}

import org.scalatest.TestSuite
import org.scalatestplus.play._

trait GreetingOneAppPerTest
  extends BaseOneAppPerTest
    with GreetingApplicationFactory {
  this: TestSuite with FakeApplicationFactory =>
}

trait GreetingOneAppPerSuiteWithComponents
  extends BaseOneAppPerSuite with GreetingApplicationFactory {
  this: TestSuite with FakeApplicationFactory  =>
}

trait GreetingOneServerPerTest
  extends BaseOneServerPerTest
    with GreetingApplicationFactory {
  this: TestSuite with FakeApplicationFactory =>
}

trait GreetingOneServerPerSuite
  extends BaseOneServerPerSuite
    with GreetingApplicationFactory {
  this: TestSuite with FakeApplicationFactory =>
}

