package adventofcode

import scala.collection.mutable

object Day8 {
  private val parseInstruction = """^([a-z]+) (inc|dec) (-?\d+).*""".r

  def largestRegister(instructions: List[String]): Int = {
    if (instructions.isEmpty) {
      return 0
    }

    var registry = mutable.HashMap[String, Int]().withDefaultValue(0)

    instructions.foreach {
      {
        case parseInstruction(register, "inc", offset) => registry(register) = registry(register) + offset.toInt
        case parseInstruction(register, "dec", offset) => registry(register) = registry(register) - offset.toInt
        case invalid => throw new MatchError(s"Invalid instruction: '$invalid'")
      }
    }

    registry.values.max
  }
}
