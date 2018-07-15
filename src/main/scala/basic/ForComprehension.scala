package basic

import scala.util.Try

object ForComprehension {

  def main(args: Array[String]) = {
    /**
      * Scala for-comprehension tricks
      *   1. backwards arrow calls flatMap
      *   2. equal sign is just basic assignment
      *   3. if at the end of a line is a filter
      *   4. the yield at the end calls map
      */

    // every <- must done on the same monad
    // the following cause compile error
    /*
        val ex1 = for {
          number <- Option(123)
          string <- Try(number.toString)
        } yield string
        println(ex1)
    */

    // for-comprehension example for Map
    val m = Map("name" -> "Jane", "country" -> "Taiwan")
    // using map/flatMap
    val mInfo = m.get("name").flatMap { n =>
      m.get("country").map { c =>
        s"$n is from $c"
      }
    }
    println(s"Map example using map/flatMap: $mInfo")
    // using for-comprehension
    val fInfo = for {
      n <- m.get("name")
      c <- m.get("country")
    } yield s"$n is from $c"
    println(s"Map example using for-comprehension: $fInfo")


    // for-comprehension example for Try
    val num1T = Try(randomNumberIfLargerThan30)
    val num2T = Try(randomNumberIfLargerThan30)
    // using map/flatMap
    val mSum = num1T.flatMap { n1 =>
      num2T.map { n2 =>
        n1 + n2
      }
    }
    println(s"Try example using map/flatMap: $mSum")
    // using for-comprehension
    val fSum = for {
      n1 <- num1T
      n2 <- num2T
    } yield n1 + n2
    println(s"Try example using for-comprehension: $fSum")
  }

  def randomNumberIfLargerThan30 = {
    val num = Math.round(Math.random * 100)
    if (num > 30) num else throw new Exception("number is too small")
  }
}
