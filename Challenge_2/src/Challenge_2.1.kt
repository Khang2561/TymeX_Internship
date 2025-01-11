// Class Product
class Product(
    val name: String,
    val price: Double,
    val quantity: Int,
)

// Class Inventory
class Inventory(private val products: List<Product>) {
    // Sum Inventory value
    fun sumInventoryValue(): Double {
        return products.sumOf { it.price * it.quantity }
    }

    // Find Most Expensive Product
    fun findMostExpensiveProduct(): Product? {
        return products.maxByOrNull { it.price }
    }

    // Check if a product exists
    fun existsProduct(productName: String): Boolean {
        return products.any { it.name == productName }
    }

    // General sort function
    fun sortProductsBy(selector: (Product) -> Comparable<Any>, ascending: Boolean = true): List<Product> {
        return if (ascending) {
            products.sortedBy(selector)
        } else {
            products.sortedByDescending(selector)
        }
    }
}

fun main() {
    val inventory = Inventory(
        listOf(
            Product("Laptop", 999.99, 5),
            Product("Smartphone", 499.99, 10),
            Product("Tablet", 299.99, 0),
            Product("Smartwatch", 199.99, 3)
        )
    )

    // Sum Inventory Value
    val totalInventoryValue = inventory.sumInventoryValue()
    println("Sum Inventory: %.2f".format(totalInventoryValue))

    println("---------------------------------------------------------------")

    // Find the most expensive product
    val mostExpensiveProduct = inventory.findMostExpensiveProduct()
    println("Most expensive product: ${mostExpensiveProduct?.name}")

    println("---------------------------------------------------------------")

    // Check if a product named "Headphones" is in stock
    println("Is Headphones in Stock: ${inventory.existsProduct("Headphones")}")

    println("---------------------------------------------------------------")

    // Sort products by price (descending)
    println("Products sorted by price (descending):")
    inventory.sortProductsBy({ it.price as Comparable<Any> }, ascending = false).forEach {
        println("${it.name}: ${it.price}, ${it.quantity}")
    }

    println("---------------------------------------------------------------")

    // Sort products by quantity (ascending)
    println("Products sorted by quantity (ascending):")
    inventory.sortProductsBy({ it.quantity as Comparable<Any> }, ascending = true).forEach {
        println("${it.name}: ${it.price}, ${it.quantity}")
    }

    println("---------------------------------------------------------------")
}
