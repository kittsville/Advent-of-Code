package adventofcode

import scala.collection.immutable.HashMap
import scala.util.control.NoStackTrace

object Day7 {
  private val parseSupportRobot = "^([a-z]+).*".r
  private val parseRobotWeight = """^([a-z]+) [(](\d+)[)].*""".r
  private val parseSupportedRobots = ".*-> ([a-z ,]+)$".r
  private val parseMapping = """^([a-z]+) [(]\d+[)] -> ([a-z ,]+)$""".r

  def getBottom(tower: List[String]): String = {
    val supports = tower.filterNot(_.endsWith(")"))
    val supportingRobots = supports.foldLeft(Set[String]())(getSupportingRobots)
    val supportedRobots  = supports.foldLeft(Set[String]())(getSupportedRobots)

    (supportingRobots -- supportedRobots).lastOption.get
  }

  def getCorrectWeight(tower: List[String]): Int = {
    val supports = tower.filterNot(_.endsWith(")"))
    val supportingRobots = supports.foldLeft(Set[String]())(getSupportingRobots)
    val supportedRobots  = supports.foldLeft(Set[String]())(getSupportedRobots)
    val topRobot = (supportingRobots -- supportedRobots).lastOption.get

    implicit val weights: Map[String, Int] = tower.map(getRobotWeights).toMap
    implicit val mapping: Map[String, List[String]] = tower.map(getRobotChildren).reduce(_ ++ _)

    try {
      getWeightFromTower(topRobot)._2
    } catch {
      case e: ImbalanceFound => e.correctWeight
    }
  }

  private def getWeightFromTower(robot: String)(implicit weights: Map[String, Int], mapping: Map[String, List[String]]): (Int, Int) = {
    val childWeights = mapping(robot).map(getWeightFromTower)

    if (childWeights.nonEmpty) {
      val weightFrequency = childWeights.groupBy(_._2)
      val mostCommonWeight = weightFrequency.maxBy(_._2.size)._1
      val leastCommonWeight = weightFrequency.minBy(_._2.size)._2.head
      val weightDifference = mostCommonWeight - leastCommonWeight._2

      if (weightDifference != 0) {
        throw ImbalanceFound(leastCommonWeight._1 + weightDifference)
      }
    }

    val robotWeight = weights(robot)
    (robotWeight, robotWeight + childWeights.foldLeft(0)((total, child) => total + child._2))
  }

  case class ImbalanceFound(correctWeight:Int) extends Exception with NoStackTrace

  private def getRobotWeights(rawRobot: String): (String, Int) = {
    rawRobot match {
      case parseRobotWeight(name, rawWeight) => (name, rawWeight.toInt)
      case _ => throw new Exception("Invalid robot weight format")
    }
  }

  private def getRobotChildren(rawMapping: String): Map[String, List[String]] = {
    rawMapping match {
      case parseMapping(robot, rawChildren) => HashMap((robot, rawChildren.split(", ").toList))
      case parseSupportRobot(robot) => HashMap((robot, List()))
      case _ => throw new Exception("Invalid robot format")
    }
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
