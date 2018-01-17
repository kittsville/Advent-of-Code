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
    val matrix = new Matrix
    var value = 1
    var position = (0, 0)
    var direction = Direction("right")
    matrix.put(position, value)

    while (value <= threshold) {
      position = direction match {
        case Direction("up")    => position |+| (0, 1)
        case Direction("right") => position |+| (1, 0)
        case Direction("down")  => position |+| (0, -1)
        case Direction("left")  => position |+| (-1, 0)
      }

      value = pointSurroundings.foldLeft(0)((total,offset) => {
        total + matrix.getOrElse(position |+| offset, 0)
      })

      matrix.put(position, value)

      direction = direction match {
        case Direction("up")    if !matrix.contains(position |+| (-1, 0)) => Direction("left")
        case Direction("right") if !matrix.contains(position |+| (0, 1))  => Direction("up")
        case Direction("down")  if !matrix.contains(position |+| (1, 0))  => Direction("right")
        case Direction("left")  if !matrix.contains(position |+| (0, -1)) => Direction("down")
        case any                                                          => any
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

  private case class Direction(direction: String)

  private class Matrix extends mutable.HashMap[(Int, Int), Int]
}
