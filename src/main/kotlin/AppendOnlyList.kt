fun <T> appendOnlyList() = AppendOnlyList<T>()

class AppendOnlyList<T> {
    private val list = mutableListOf<T>()

    fun append(item: T) {
        list.add(item)
    }

    fun records(): List<T> = list.toList()
}