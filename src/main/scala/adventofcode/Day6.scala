package adventofcode

object Day6 {
  def redistribute(memory: List[Int]): List[Int] = {
    val max = memory.max
    val indexOfMax = memory.indexOf(max)
    var redistributed = List.fill(memory.length)(max / memory.length)

    if (max % memory.length != 0) {
      val indexAfterMax = (indexOfMax + 1) % memory.length
      redistributed = redistributed.updated(indexAfterMax, max - max/2)
    }

    (memory.updated(indexOfMax,0), redistributed).zipped.map(_ + _)
  }
}
