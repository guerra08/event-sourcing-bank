import BankAccount.Companion.rehydrate
import java.math.BigDecimal

val eventStore = appendOnlyList<Event>()

fun <T> AppendOnlyList<T>.ofAccountId(accountId: String) where T : Event =
    this.records().filter { it.accountId == accountId }.sortedBy { it.timestamp }

private const val accountId = "1"

fun main() {

    // We only append to the eventStore
    // Other than that, we can only read

    eventStore.append(
        AccountCreated(
            accountId = accountId,
        )
    )

    eventStore.append(
        Deposit(
            accountId = accountId,
            amount = BigDecimal("100.00")
        )
    )

    eventStore.append(
        Withdrawal(
            accountId = accountId,
            amount = BigDecimal("50.00")
        )
    )

    // Filtering for my account id, sorting it by timestamp
    val events = eventStore.ofAccountId(accountId)

    // In order to have an account instance, we need to rehydrate from the series of events
    val account = rehydrate(accountId, events)

    println(account)
}