package adventofcode

import adventofcode.Day8.largestRegister
import org.specs2._

class Day8Spec extends mutable.Specification {
  "Register specification where" >> {
    "No instructions are executed" >> {
      largestRegister(List()) must_== 0
    }

    "A single instructions is executed" >> {
      largestRegister(List("a inc 5 if a == 0")) must_== 5
    }

    "Multiple instructions are executed" >> {
      val instructions = List(
        "a inc 2 if a == 0",
        "b inc 4 if a == 2"
      )

      largestRegister(instructions) must_== 4
    }
  }
}
