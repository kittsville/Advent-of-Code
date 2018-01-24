package adventofcode

object Day6 {
  def redistribute(memory: List[Int]): List[Int] = {
    val max = memory.max
    val indexOfMax = memory.indexOf(max)
    val remainder  = max % memory.length
    var redistributed = List.fill(memory.length)(max / memory.length)

    if (remainder > 0) {
      val startOfRemainder = (indexOfMax + 1) % memory.length
      val endOfRemainder = (indexOfMax + remainder) % memory.length

      redistributed = redistributed.zipWithIndex.map {
        case (value, index) if (index >= startOfRemainder & index <= endOfRemainder) & startOfRemainder <= endOfRemainder  => value + 1
        case (value, index) if (index >= startOfRemainder || index <= endOfRemainder) & startOfRemainder > endOfRemainder  => value + 1
        case (value, _) => value
      }
    }

    (memory.updated(indexOfMax,0), redistributed).zipped.map(_ + _)
  }
}
