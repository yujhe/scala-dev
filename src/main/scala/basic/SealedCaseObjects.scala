package basic

/**
  * reference:
  *   1. https://underscore.io/blog/posts/2014/09/03/enumerations.html
  *   2. https://stackoverflow.com/questions/1898932/case-objects-vs-enumerations-in-scala
  *   3. https://stackoverflow.com/questions/7530039/built-in-parsing-of-a-string-to-a-scala-case-object
  */
object SealedCaseObjects {
  def main(args: Array[String]): Unit = {

    object Colours {
      sealed trait EnumVal { def name: String }
      case object Red extends EnumVal { val name = "red" }
      case object Yellow extends EnumVal { val name = "yellow" }
      case object Green extends EnumVal { val name = "green" }

      val colours = Seq(Red, Yellow, Green)

      def fromString(name: String): Option[Colours.EnumVal] = colours.find(_.name.toLowerCase == name.toLowerCase)
    }

    object WeekDays {
      sealed trait EnumVal
      case object Mon extends EnumVal
      case object Tue extends EnumVal
      val daysOfWeek = Seq(Mon, Tue)
    }

    // you can overload method with different sealed object
    object Functions {
      def f(x: Colours.EnumVal) = "That's a colour"
      def f(x: WeekDays.EnumVal) = "That's a weekday"
    }
    println("print Functions.f(Colours.Red): " + Functions.f(Colours.Red)) // print Functions.f(Colours.Red): That's a colour
    println("print Functions.f(WeekDays.Mon): " + Functions.f(WeekDays.Mon)) // print Functions.f(WeekDays.Mon): That's a weekday
    println("print WeekDays.Mon: " + WeekDays.Mon) // print WeekDays.Mon: Mon

    // having exhaustive matching check during compile
    def matchRGB(colour: Colours.EnumVal): (Int, Int, Int) = colour match {
      case Colours.Green => (0, 128, 0)
      case Colours.Red => (255, 0, 0)
      // case Colours.Yellow => (255, 255, 0)
    }
    val redRGBCode = matchRGB(Colours.Red)
    println("print matchRGB(Colours.Red): " + redRGBCode) // print matchRGB(Colours.Red): (255,0,0)

    val green = Colours.fromString("green")
    println("print Colours.fromString(\"green\"): " + green.get.name) // print Colours.fromString("green"): green

    val orange = Colours.fromString("orange")
    println("print Colours.fromString(\"orange\"): " + orange) // print Colours.fromString("orange"): None
  }
}
