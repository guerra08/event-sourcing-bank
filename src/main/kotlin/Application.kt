import java.math.BigDecimal

val eventStore = appendOnlyList<Event>()

fun appendEvent(event: Event) {
    eventStore.append(event)
}

fun main() {

    appendEvent(
        AccountCreated(
            accountId = "1",
        )
    )

    appendEvent(
        Deposit(
            accountId = "1",
            amount = BigDecimal("100.00")
        )
    )

    appendEvent(
        Withdrawal(
            accountId = "1",
            amount = BigDecimal("50.00")
        )
    )

    val events = eventStore.records().filter { it.accountId == "1" }

    val account = rehydrate("1", events)

    println(account)
}