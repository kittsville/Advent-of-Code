package adventofcode

import scala.collection.mutable
import scalaz.Scalaz._
import scalaz._

object Day3 {
  def stepsRequired(dataSquare: Int): Int = {
    dataSquare match {
      case 1 => 0
      case _ => calculateInwardSteps(dataSquare, 2)
    }
  }

  def largerThan(threshold: Int): Int = {
    val pointSurroundings = List((1, 0), (1, -1), (0, -1), (-1, -1), (-1, 0), (-1, 1), (0, 1), (1, 1))
    val matrix = new mutable.HashMap[(Int, Int), Int]
    var value = 1
    var position = (0, 0)
    var direction = "right"
    matrix.put(position, value)

    while (value <= threshold) {
      position = direction match {
        case "up"    => position |+| (0, 1)
        case "right" => position |+| (1, 0)
        case "down"  => position |+| (0, -1)
        case "left"  => position |+| (-1, 0)
      }
      println("New", position)
      value = pointSurroundings.foldLeft(0)((total,offset) => {
        println(position |+| offset, matrix.getOrElse(position |+| offset, 0))
        total + matrix.getOrElse(position |+| offset, 0)
      })
      println("Set", position, value)
      matrix.put(position, value)

      direction = direction match {
        case "up"    if !matrix.contains(position |+| (-1, 0)) => "left"
        case "right" if !matrix.contains(position |+| (0, 1))  => "up"
        case "down"  if !matrix.contains(position |+| (1, 0))  => "right"
        case "left"  if !matrix.contains(position |+| (0, -1)) => "down"
        case any                                               => any
      }
    }

    value
  }

  private def calculateInwardSteps(dataSquare: Int, depth: Int): Int = {
    val multiplier = depth * 2 - 1

    if (dataSquare < multiplier * multiplier) {
      1 + calculateEdgeSteps(dataSquare, depth)
    } else {
      1 + calculateInwardSteps(dataSquare, depth + 1)
    }
  }

  private def calculateEdgeSteps(dataSquare: Int, depth: Int): Int = {
    val previousDepth = depth - 1
    val previousMultiplier = previousDepth * 2 - 1
    val edgeSquare = dataSquare + previousDepth - (previousMultiplier * previousMultiplier)

    edgeSquare % (previousDepth * 2)
  }
}
