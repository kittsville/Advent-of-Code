package adventofcode

import adventofcode.Day8.largestRegister
import org.specs2._

class Day8Spec extends mutable.Specification {
  "Register specification where" >> {
    "There are no instructions" >> {
      largestRegister(List()) must_== 0
    }

    "A single increment is executed" >> {
      largestRegister(List("a inc 5 if a == 0")) must_== 5
    }

    "A single decrement is executed" >> {
      largestRegister(List("a dec 5 if a == 0")) must_== -5
    }

    "Multiple increments are executed" >> {
      val instructions = List(
        "a inc 2 if a == 0",
        "b inc 4 if a == 2"
      )

      largestRegister(instructions) must_== 4
    }

    "Multiple decrements are executed" >> {
      val instructions = List(
        "a dec 4 if a == 0",
        "b dec 2 if a == -4"
      )

      largestRegister(instructions) must_== -2
    }

    "An increment is dependant on a condition" >> {
      largestRegister(List("a inc 5 if a == 5")) must_== 0
    }

    "A decrement is dependant on a condition" >> {
      largestRegister(List("a dec 5 if a == 5")) must_== 0
    }

    "Multiples instructions are dependant on conditions" >> {
      "Greater than" >> {
        val instructions = List(
          "a inc 2 if a > 0",
          "a inc 3 if a == 0",
          "a inc 2 if a > 2"
        )

        largestRegister(instructions) must_== 5
      }

      "Greater than or equals" >> {
        val instructions = List(
          "a inc 2 if a >= 0",
          "a inc 2 if a >= 1",
          "a inc 3 if a >= 5"
        )

        largestRegister(instructions) must_== 4
      }

      "Less than" >> {
        val instructions = List(
          "a inc 2 if a < 0",
          "a inc 3 if a < 1"
        )

        largestRegister(instructions) must_== 3
      }

      "Less than or equals" >> {
        val instructions = List(
          "a inc 2 if a <= 0",
          "a inc 2 if a <= 3",
          "a inc 3 if a <= 3"
        )

        largestRegister(instructions) must_== 4
      }

      "Not equals" >> {
        val instructions = List(
          "a inc 2 if a != 2",
          "a inc 2 if a != 2"
        )

        largestRegister(instructions) must_== 2
      }
    }
  }
}
