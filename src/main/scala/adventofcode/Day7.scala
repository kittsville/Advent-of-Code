package adventofcode

object Day7 {
  private val robot = "^([a-z]+) \\(\\d+\\) -> (.+)$".r

  def getBottom(tower: List[String]): String = {
    val foo = tower.flatMap(parseRobotInfo).foldLeft((Set[String](), Set[String]()))((left, right) => (left._1 + right._1, left._2 ++ right._2))

    (foo._1 -- foo._2).last
  }

  private def parseRobotInfo(robotInfo: String): Option[(String, Set[String])] = {
    robotInfo match {
      case robot(bottomRobot, supportedRobots) => Some((bottomRobot, supportedRobots.split(", ").toSet))
      case _ => None
    }
  }
}
