package practice

fun main() {

    talkLikeCashier(sayNicely("How can I help you"))
    talkLikeCustomer("David", "${askAboutProduct(1, "kg of", "bread")}")
    talkLikeCashier(tellPrice(300))
    talkLikeCashier(sayNicely("Will that be all"))
    talkLikeCustomer("David", "Yes, thank you.")
    talkLikeCashier(sayNicely("How can I help you"))
    talkLikeNiceCashier("How can I help you")
    talkLikeCustomer("Bob", "${askAboutProduct(2, "cartons of", "milk")}")
    talkLikeCashier(tellPrice(200))
    talkLikeCustomer("Bob", "${sayNicely("Thank you very much")}")
    talkLikeCashier(sayNicely("Thank you and have a nice day"))


}

fun talkLikeNiceCashier(text: String){
    talkLikeCashier(sayNicely(text))
}

fun talkLikeCashier(text: String) {
    println("Cashier: $text")
}

fun talkLikeCustomer(name: String, text1: String) {
    println("Customer $name: $text1")
}

fun askAboutProduct(productQuantity: Int, productUnit: String, productName: String): String {
    return "I'd like to buy $productQuantity $productUnit $productName. How much is it?"
}

fun tellPrice(price: Int): String {
    return "It costs $price Ft."
}

fun sayNicely(text2: String): String {
    return "$text2, dear Sir."
}