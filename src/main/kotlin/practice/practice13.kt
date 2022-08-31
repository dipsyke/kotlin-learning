package practice.p13

private class Mail(val content: String, val to: String)

private class Person(val name: String, val address: String) {
    fun receive(mailToReceive: Mail) {
        println("Yay, I'm $name and I've just received a ${mailToReceive.content}")
    }
}

private class Post(val country: String, val people: List<Person>) {
    fun sendMail(mailToSend: Mail) {
        for (person in people) {
            if (person.name == mailToSend.to) {
                person.receive(mailToSend)
            }
        }
    }
}

fun main() {
    val mail1 = Mail("Birthday wish", "Granny Clara")
    val mail2 = Mail("Condolence letter", "David")
    val mail3 = Mail("Congratulations", "János")
    val mail4 = Mail("Letter of resignation", "Adele")
    val mail5 = Mail("acceptance letter", "Rudolf")
    val mail6 = Mail("invitation", "Granny Clara")
    val mail7 = Mail("Christmas Card", "Rudolf")

    val person1 = Person("Granny Clara", "Long Street")
    val person2 = Person("David", "Short Street")
    val person3 = Person("János", "Wesselényi Street")
    val person4 = Person("Adele", "Fake Street")
    val person5 = Person("Rudolf", "Bad Street")

    person1.receive(mail1)


    val postHu = Post("Hungary", listOf(person1, person2, person3, person4, person5))
    postHu.sendMail(mail1)
    postHu.sendMail(mail2)
    postHu.sendMail(mail3)
    postHu.sendMail(mail4)
    postHu.sendMail(mail5)
    postHu.sendMail(mail6)
    postHu.sendMail(mail7)

}