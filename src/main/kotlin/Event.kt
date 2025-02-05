import java.math.BigDecimal
import java.time.LocalDateTime

sealed interface Event {
    val accountId: String
    val timestamp: LocalDateTime
}

data class AccountCreated(override val accountId: String, override val timestamp: LocalDateTime = LocalDateTime.now()) : Event

data class Deposit(override val accountId: String, override val timestamp: LocalDateTime = LocalDateTime.now(), val amount: BigDecimal) : Event

data class Withdrawal(override val accountId: String, override val timestamp: LocalDateTime = LocalDateTime.now(), val amount: BigDecimal) : Event