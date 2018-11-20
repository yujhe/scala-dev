package basic

// reference: https://underscore.io/blog/posts/2014/09/03/enumerations.html
object ScalaEnum {
  def main(args: Array[String]): Unit = {

    object Colours extends Enumeration {
      val Red, Yellow, Green = Value
    }

    object WeekDays extends Enumeration {
      val Mon: WeekDays.Value = Value("Monday")
      val Tue: WeekDays.Value = Value("Tuesday")
    }

    println("print Colours.Red: " + Colours.Red) // print Colours.Red: Red
    println("print WeekDays.Mon: " + WeekDays.Mon) // print WeekDays.Mon: Monday

    // Enumerations have the same type after erasure,
    // you can not overload method with different Enumerations
    object Functions {
      def f(x: Colours.Value) = "That's a colour"

      // def f(x: WeekDays.Value) = "That's a weekday"
    }

    // No exhaustive matching check during compile
    def matchRGB(colour: Colours.Value): (Int, Int, Int) = colour match {
      case Colours.Green => (0, 128, 0)
      case Colours.Red => (255, 0, 0)
    }

    // fail at runtime with a `scala.MatchError`
    val redRGBCode = matchRGB(Colours.Red)
    println("print matchRGB(Colours.Red): " + redRGBCode) // print matchRGB(Colours.Red): (255,0,0)
    // val yellowRGBCode = matchRGB(Colours.Yellow)
  }
}
