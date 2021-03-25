fun main(args: Array<String>) {
    val a: Int = 1  // immediate assignment
    val b = 2   // `Int` type is inferred
    val c: Int  // Type required when no initializer is provided
    c = 3       // deferred assignment
    println(args.contentToString())
    println("Hello World!")
    println(describe("Hello"))
    println("sum of 19 and 23 is ${sum2(19, 23)}")
    print("sum of 3 and 5 is ")
    println(sum1(3, 5))
    printSum(5,6)
    val rectangle = Rectangle(5.0, 2.0)
    println("The perimeter is ${rectangle.perimeter}")
    stringTemplate()
    println("max of 0 and 42 is ${maxOf(0, 42)}")
    rangeTest()
    collectionTest()
    loopTest()
    printProduct("6", "7")
    printProduct("a", "7")
    printProduct("a", "b")
    printLength("Incomprehensibilities")
    printLength(1000)
    printLength(listOf(Any()))
}
fun printLength(obj: Any) {
    println("Getting the length of '$obj'. Result: ${getStringLength(obj) ?: "Error: The object is not a string"} ")
}
/*
A reference must be explicitly marked as nullable when null value is possible. Nullable type names have ? at the end.
Return null if str does not hold an integer:
*/
fun parseInt(str: String): Int? {
    return str.toIntOrNull()
}

fun printProduct(arg1: String, arg2: String) {
    val x = parseInt(arg1)
    val y = parseInt(arg2)

    // Using `x * y` yields error because they may hold nulls.
    if (x != null && y != null) {
        // x and y are automatically cast to non-nullable after null check
        println(x * y)
    }
    else {
        println("'$arg1' or '$arg2' is not a number")
    }
}

fun getStringLength(obj: Any): Int? {
    if (obj is String) {
        return obj.length
    }
    return null
}

fun loopTest(){
    val items = listOf("apple", "banana", "kiwifruit")
    for (item in items) {
        println(item)
    }
    for (index in items.indices) {
        println("item at $index is ${items[index]}")
    }
    var index = 0
    while (index < items.size) {
        println("item at $index is ${items[index]}")
        index++
    }
}

fun rangeTest(){
    val x = 10
    val y = 9
    if (x in 1..y+1) {
        println("fits in range")
    }
    val list = listOf("a", "b", "c")

    if (-1 !in 0..list.lastIndex) {
        println("-1 is out of range")
    }
    println("list.size = ${list.size} list.indices = ${list.indices}")
    if (list.size !in list.indices) {
        println("list size is out of valid list indices range, too")
    }
    for (x in 1..5) {
        print(x)
    }
    for (x in 1..10 step 2) {
        print(x)
    }
    println()
    for (x in 9 downTo 0 step 3) {
        print(x)
    }
}

fun collectionTest(){
    val items = listOf("apple", "banana", "kiwifruit")
    for (item in items) {
        println(item)
    }
    when {
        "orange" in items -> println("juicy")
        "apple" in items -> println("apple is fine too")
    }
    val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
    fruits
        .filter { it.startsWith("a") }
        .sortedBy { it }
        .map { it.toUpperCase() }
        .forEach { println(it) }
}

fun maxOf(a: Int, b: Int): Int {
    if (a > b) {
        return a
    } else {
        return b
    }
}

fun stringTemplate(){
    var a = 1
    // simple name in template:
    val s1 = "a is $a"
    a = 2
    // arbitrary expression in template:
    val s2 = "${s1.replace("is", "was")}, but now is $a"
    println(s2)
}

fun describe(obj: Any): String =
    when (obj) {
        1          -> "One"
        "Hello"    -> "Greeting"
        is Long    -> "Long"
        !is String -> "Not a string"
        else       -> "Unknown"
}

fun sum1(a: Int, b: Int): Int {
    return a + b
}

fun sum2(a: Int, b: Int) = a + b

fun printSum(a: Int, b: Int) {
    println("sum of $a and $b is ${a + b}")
}