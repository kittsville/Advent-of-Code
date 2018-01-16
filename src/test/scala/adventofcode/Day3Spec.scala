package adventofcode

import adventofcode.Day3.stepsRequired
import org.specs2._

class Day3Spec extends mutable.Specification {
  "Spiral Memory specification where" >> {
    "Square 1 is carried 0 steps" >> {
      stepsRequired(1) must_== 0
    }

    "Square 3 is carried 2 steps" >> {
      stepsRequired(3) must_== 2
    }

    "Square 12 is carried 3 steps" >> {
      stepsRequired(12) must_== 3

    }

    "Square 23 is carried 2 steps" >> {
      println(stepsRequired(265149))
      stepsRequired(23) must_== 2

    }

    "Square 1024 is carried 31 steps" >> {
      stepsRequired(1024) must_== 31

    }
  }
}
