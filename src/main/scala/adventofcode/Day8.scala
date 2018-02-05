package adventofcode

import scala.collection.mutable

object Day8 {
  private val parseInstruction = """^([a-z]+) (inc|dec) (-?\d+) if ([a-z]+) (==|!=|>=|<=|>|<) (-?\d+)$""".r

  def largestRegister(instructions: List[String]): Int = {
    if (instructions.isEmpty) {
      return 0
    }

    implicit val registry: mutable.Map[String, Int] = mutable.HashMap[String, Int]().withDefaultValue(0)


    instructions.foreach {
      {
        case parseInstruction(register, "inc", offset, cmpRegister, comparison, compareTo) if conditionHolds(cmpRegister, comparison, compareTo.asInt) => registry.put(register, registry(register) + offset.asInt)
        case parseInstruction(register, "dec", offset, cmpRegister, comparison, compareTo) if conditionHolds(cmpRegister, comparison, compareTo.asInt) => registry.put(register, registry(register) - offset.asInt)
        case parseInstruction(_, "inc", _, _, _, _) =>
        case parseInstruction(_, "dec", _, _, _, _) =>
        case invalid => throw new MatchError(s"Invalid instruction: '$invalid'")
      }
    }

    registry.size match {
      case 0 => 0
      case _ => registry.values.max
    }
  }

  private def conditionHolds(register: String, comparison: String, compareTo: Int)(implicit registry: mutable.Map[String, Int]): Boolean = {
    comparison match {
      case "=="  => registry(register) == compareTo
      case ">"   => registry(register) > compareTo
      case "<"   => registry(register) < compareTo
      case ">="  => registry(register) >= compareTo
      case "<="  => registry(register) <= compareTo
      case "!="  => registry(register) != compareTo
      case invalid => throw new MatchError(s"Invalid comparator $invalid")
    }
  }

  private implicit class StringToInt(x: String) {
    def asInt: Int = {
      Integer.parseInt(x, 10)
    }
  }
}
