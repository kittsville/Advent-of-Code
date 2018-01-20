package adventofcode

import scala.collection.mutable.ArrayBuffer

object Day5 {
  def stepsToEscape(maze: ArrayBuffer[Int]): Int = {
    countSteps(0, maze)
  }

  private def countSteps(index: Int, maze: ArrayBuffer[Int]): Int = {
    if (index < 0 || index >= maze.length) {
      return 0
    }

    val nextIndex = index + maze(index)

    maze(index) = maze(index) + 1

    countSteps(nextIndex, maze) + 1
  }
}
