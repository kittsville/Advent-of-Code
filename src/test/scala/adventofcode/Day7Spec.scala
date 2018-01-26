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
        "ajbf (86) -> fwft"
      )

      getBottom(input) must_== "ajbf"
    }

    "Robots support multiple other robots" >> {
      val input = List(
        "pbga (66)",
        "xhth (57)",
        "ebii (61)",
        "havc (66)",
        "ktlj (57)",
        "fwft (72) -> ktlj, cntj, xhth",
        "qoyq (66)",
        "padx (45) -> pbga, havc, qoyq",
        "tknk (41) -> ugml, padx, fwft",
        "jptl (61)",
        "ugml (68) -> gyxo, ebii, jptl",
        "gyxo (61)",
        "cntj (57)"
      )

      getBottom(input) must_== "tknk"
    }
  }
}
