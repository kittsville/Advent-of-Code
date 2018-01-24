package adventofcode

object Day6 {
  def redistribute(memory: List[Int]): List[Int] = {
    val max = memory.max
    val indexOfMax = memory.indexOf(max)
    val redistributed = max match {
      case even if even % memory.length == 0 => List.fill(memory.length)(even / memory.length)
      case odd => List(odd - odd/2) ++ List.fill(memory.length - 1)(odd/memory.length)
    }

    (memory.updated(indexOfMax,0), redistributed).zipped.map(_ + _)
  }
}
