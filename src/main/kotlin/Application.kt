import BankAccount.Companion.rehydrate
import java.math.BigDecimal

val eventStore = appendOnlyList<Event>()

fun appendEvent(event: Event) {
    eventStore.append(event)
}

fun main() {

    // We only append to the eventStore
    // Other than that, we can only read

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

    // Filtering for my account id
    val events = eventStore.records().filter { it.accountId == "1" }

    // In order to have an account instance, we need to rehydrate from the series of events
    val account = rehydrate("1", events)

    println(account)
}