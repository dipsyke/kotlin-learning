package practice.p11

private class Person (val name: String, val age: Int, val colour: String){
    fun introduce(){
        println("Hello, az én nevem $name")
    }

    fun introduceTo(toName: String){
        println("Hello, $toName, az én nevem $name")
    }

    fun introduceTo(to: Person){
        println("Hello, ${to.name}, az én nevem $name")
    }
}

private class TalkShow (val title: String, val guests: List<Person>){
    fun doIntro(){
        println("Üdvözöljük kedves nézőinket, ez itt a $title")
        for (guest in guests){
            printHeadline(guest)
        }
    }
}

private fun printHeadline(person: Person) {
    println("Teletubby Talkshow vendég név: ${person.name} kor: ${person.age}  szín: ${person.colour}")
}
fun main() {

    val person1 = Person ("Dipsy", 24, "green")
    val person2 = Person("Po", 4, "red")

    val talkShow1 = TalkShow("talkintubbies", listOf(person1, person2))
    talkShow1.doIntro()

    println(person1.name)
    println(person1.age)
    person1.introduce()
    person1.introduceTo("Tinky Winky")
    person1.introduceTo(person2)
    printHeadline(person1)

    println(person2.name)
    println(person2.age)
    person2.introduce()
    person2.introduceTo("Lala")
    person2.introduceTo(person1)
    printHeadline(person2)



}