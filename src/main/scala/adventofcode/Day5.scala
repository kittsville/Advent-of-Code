package adventofcode

import scala.collection.mutable.ArrayBuffer

object Day5 {
  def stepsToEscape(maze: ArrayBuffer[Int]): Int = {
    val incrementer = (offset: Int) => {
      offset + 1
    }

    calculateSteps(maze, incrementer)
  }


  def fancyStepsToEscape(maze: ArrayBuffer[Int]): Int = {
    val incrementer = (offset: Int) => {
      if (offset >= 3) {
        offset - 1
      } else {
        offset + 1
      }
    }

    calculateSteps(maze, incrementer)
  }

  private def calculateSteps(maze: ArrayBuffer[Int], incrementer: (Int) => Int): Int = {
    var stepsTaken = 0
    var index      = 0
    var oldIndex   = 0

    while (index >= 0 && index < maze.length) {
      stepsTaken += 1
      oldIndex = index
      index = index + maze(index)

      maze(oldIndex) = incrementer(maze(oldIndex))
    }

    stepsTaken
  }
}
