package adventofcode

import scala.collection.mutable

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

  def loopsUntilRepeat(inputMemory: List[Int]): Int = {
    val configurations = mutable.Set[List[Int]]()
    var memory = inputMemory
    while(configurations.add(memory)) {
      memory = redistribute(memory)
    }

    configurations.size
  }

  def loopsBetweenRepeat(inputMemory: List[Int]): Int = {
    val configurations = mutable.Set[List[Int]]()
    val configOrder    = mutable.HashMap[List[Int], Int]()
    var memory = inputMemory
    while(configurations.add(memory)) {
      configOrder.put(memory, configurations.size)
      memory = redistribute(memory)
    }

    1 + configurations.size - configOrder(memory)
  }
}
