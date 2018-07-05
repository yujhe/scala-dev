package basic

object CompanionObjectsAndImplicits {

  case class Person(name: String)

  object Person {
    implicit val p = new Person("Jane")
  }

  def main(args: Array[String]): Unit = {
    // say hello to Abbe
    hello(Person("Abbe"))

    // say hello without implicit
    // companion object is the 2nd place Scala will look for implicit
    hello

    // say hello with implicit
    implicit val p = Person("Ricky")
    hello
  }

  def hello(implicit p: Person) = println(s"Hello, ${p.name}")

}
