package adventofcode

object Day7 {
  private val parseSupportRobot = "^([a-z]+).*".r
  private val parseSupportedRobots = ".*-> ([a-z ,]+)$".r

  def getBottom(tower: List[String]): String = {
    val supports = tower.filterNot(_.endsWith(")"))
    val supportingRobots = supports.foldLeft(Set[String]())(getSupportingRobots)
    val supportedRobots  = supports.foldLeft(Set[String]())(getSupportedRobots)

    (supportingRobots -- supportedRobots).lastOption.get
  }

  private def getSupportingRobots(supportingRobots: Set[String], rawSupport: String): Set[String] = {
    rawSupport match {
      case parseSupportRobot(robot) => supportingRobots + robot
      case _ => supportingRobots
    }
  }

  private def getSupportedRobots(supportedRobots: Set[String], rawSupport: String): Set[String] = {
    rawSupport match {
      case parseSupportedRobots(robots) => supportedRobots ++ robots.split(", ").toSet
      case _ => supportedRobots
    }
  }
}
