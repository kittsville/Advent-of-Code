package adventofcode

import adventofcode.Day6.redistribute
import org.specs2._

class Day6Spec extends mutable.Specification {
  "Reallocation specification where" >> {
    "Memory is evenly distributed if banks and imbalance are equal" >> {
      redistribute(List(0,0,3)) must_== List(1,1,1)
    }

    "Memory is oddly distributed if banks and imbalance aren't equal" >> {
      redistribute(List(0,0,0,1)) must_== List(1,0,0,0)
    }

    "Memory requires multiple passes to redistribute if banks and imbalance are equal" >> {
      redistribute(List(0,0,0,8)) must_== List(2,2,2,2)
    }

    "Memory requires multiple passes to redistribute if banks and imbalance aren't equal" >> {
      redistribute(List(0,0,4)) must_== List(2,1,1)
    }

    "Memory is redistributed across banks that already contain blocks" >> {
      redistribute(List(1,2,3)) must_== List(2,3,1)
    }

    "Memory is redistributed from the first bank" >> {
      redistribute(List(4,0,0)) must_== List(1,2,1)
    }
  }
}
