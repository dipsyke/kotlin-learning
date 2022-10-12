package practice.practice18

fun main() {
    var givenAge = 0
    val persons: ArrayList<Person> = ArrayList()
    persons.add(person1)
    persons.add(person2)
    persons.add(person3)
    persons.add(person4)
    persons.add(person5)
    persons.add(person6)
    persons.add(person7)
    persons.add(person8)
    persons.add(person9)
    persons.add(person10)
    persons.add(person11)
    persons.add(person12)
    persons.add(person13)
    persons.add(person14)
    persons.add(person15)
    persons.add(person16)
    persons.add(person17)
    persons.add(person18)
    persons.add(person19)
    persons.add(person20)

    givenAge = askForAge()

    for (person in persons) {
        if (person.age <= givenAge + 5 && person.age >= givenAge - 5) {
            println(person)
        }
    }
}


fun askForName(): String {
    println("Add meg a nevedet!")
    return readLine()!!
}

fun askForAge(): Int {
    println("Add meg a korodat!")
    return readLine()!!.toInt()
}


val person1 = Person("Róbert", 21)
val person2 = Person("Károly", 30)
val person3 = Person("Gyula", 28)
val person4 = Person("László", 24)
val person5 = Person("Béla", 42)
val person6 = Person("Ernő", 35)
val person7 = Person("Ferenc", 17)
val person8 = Person("Ödön", 36)
val person9 = Person("Szaniszló", 69)
val person10 = Person("János", 50)
val person11 = Person("Tilda", 15)
val person12 = Person("Teréz", 26)
val person13 = Person("Magda", 44)
val person14 = Person("Mária", 57)
val person15 = Person("Piroska", 31)
val person16 = Person("Róza", 27)
val person17 = Person("Cecília", 60)
val person18 = Person("Alma", 23)
val person19 = Person("Elizabet", 36)
val person20 = Person("Erna", 48)