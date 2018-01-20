package adventofcode

import adventofcode.Day5.{fancyStepsToEscape, stepsToEscape}
import org.specs2._

import scala.collection.mutable.ArrayBuffer

class Day5Spec extends mutable.Specification {
  "Maze jumper specification where" >> {
    "Single positive instruction should take one step" >> {
      stepsToEscape(ArrayBuffer(1)) must_== 1
    }

    "Single zero instruction should take two steps" >> {
      stepsToEscape(ArrayBuffer(0)) must_== 2
    }

    "Two instructions take three steps" >> {
      stepsToEscape(ArrayBuffer(0, 1)) must_== 3
    }

    "Multiple instructions take multiple steps" >> {
      stepsToEscape(ArrayBuffer(0, 3, 0, 1, -3)) must_== 5
    }
  }

  "Fancy maze specification where" >> {
    "Offsets of three or higher get decremented" >> {
      fancyStepsToEscape(ArrayBuffer(3, 0, 2, -3)) must_== 4
    }

    "Multiple instructions take 10 fancy steps" >> {
      fancyStepsToEscape(ArrayBuffer(0, 3, 0, 1, -3)) must_== 10
    }
  }
}
