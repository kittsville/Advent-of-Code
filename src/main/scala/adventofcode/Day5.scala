package adventofcode

import scala.collection.mutable.ArrayBuffer

object Day5 {
  def stepsToEscape(maze: ArrayBuffer[Int]): Int = {
    var stepsTaken = 0
    var index      = 0
    var oldIndex   = 0

    while (index >= 0 && index < maze.length) {
      stepsTaken += 1
      oldIndex = index
      index = index + maze(index)
      maze(oldIndex) = maze(oldIndex) + 1
    }

    stepsTaken
  }
}
