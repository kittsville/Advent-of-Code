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
    implicit val pointSurroundings = List((1, 0), (1, -1), (0, -1), (-1, -1), (-1, 0), (-1, 1), (0, 1), (1, 1))
    implicit val matrix = new Matrix
    val value = 1
    val position = (0, 0)
    val direction = Direction("right")
    matrix.put(position, value)

    getLargerValue(threshold, position, direction)
  }

  private def getLargerValue(threshold: Int, lastPosition: (Int, Int), direction: Direction)
                            (implicit matrix: Matrix, pointSurroundings: List[(Int, Int)]): Int = {
    val position = direction match {
      case Direction("up")    => lastPosition |+| (0, 1)
      case Direction("right") => lastPosition |+| (1, 0)
      case Direction("down")  => lastPosition |+| (0, -1)
      case Direction("left")  => lastPosition |+| (-1, 0)
      case Direction(unknown) => throw new IllegalArgumentException(s"Unknown direction $unknown")
    }

    val valueAtPosition = pointSurroundings.foldLeft(0)((total,offset) => {
      total + matrix.getOrElse(position |+| offset, 0)
    })

    if (valueAtPosition > threshold) {
      valueAtPosition
    } else {
      matrix.put(position, valueAtPosition)

      val nextDirection = direction match {
        case Direction("up")    if !matrix.contains(position |+| (-1, 0)) => Direction("left")
        case Direction("right") if !matrix.contains(position |+| (0, 1))  => Direction("up")
        case Direction("down")  if !matrix.contains(position |+| (1, 0))  => Direction("right")
        case Direction("left")  if !matrix.contains(position |+| (0, -1)) => Direction("down")
        case lastDirection                                                => lastDirection
      }

      getLargerValue(threshold, position, nextDirection)
    }
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
