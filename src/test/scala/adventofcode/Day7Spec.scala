package adventofcode

import adventofcode.Day7.getBottom
import org.specs2._

class Day7Spec extends mutable.Specification {
  "Bottom finder specification where" >> {
    "The bottom is one of two" >> {
      val input = List(
        "ktlj (57)",
        "fwft (72) -> ktlj"
      )

      getBottom(input) must_== "fwft"
    }

    "The bottom is one of two robot supporting robots" >> {
      val input = List(
        "ktlj (57)",
        "fwft (72) -> ktlj",
        "ajbf (72) -> fwft"
      )

      getBottom(input) must_== "ajbf"
    }
  }
}
