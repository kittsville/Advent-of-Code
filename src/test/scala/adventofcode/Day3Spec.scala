package adventofcode

import adventofcode.Day3.{stepsRequired,largerThan}
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

  "Initilised spiral memory specification where" >> {
    "First value larger than 1 is 2" >> {
      largerThan(1) must_== 2
    }

    "First value larger than 2 is 4" >> {
      largerThan(2) must_== 4
    }

    "First value larger than 26 is 54" >> {
      largerThan(26) must_== 54
    }

    "First value larger than 747 is 806" >> {
      largerThan(747) must_== 806
    }
  }
}
