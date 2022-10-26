package example

fun main() {
    val callStack = "main>"
    printWithCallStack(callStack, "Start of main")
    fx(callStack+"fx>")
    f1("${callStack}f1>")
    printWithCallStack(callStack, "End of main")
}

private fun f1(callStack: String) {
    printWithCallStack(callStack, "Start of f1")
    fx(callStack+"fx>")
    f2("${callStack}f2>")
    printWithCallStack(callStack, "End of f1")
}

private fun f2(callStack: String) {
    printWithCallStack(callStack, "Start of f2")
    fx(callStack+"fx>")
    return // We're using return, so instructions in f2 after the return keyword won't run
    printWithCallStack(callStack, "End of f2")
}

private fun fx(callStack: String) {
    printWithCallStack(callStack, "In fx")
}

private fun printWithCallStack(callStack: String, toPrint: String) {
    println("$callStack: $toPrint")
}