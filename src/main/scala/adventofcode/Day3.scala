package adventofcode

object Day3 {
  def stepsRequired(dataSquare: Int): Int = {
    dataSquare match {
      case 1 => 0
      case _ => calculateInwardSteps(dataSquare, 2)
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
}
