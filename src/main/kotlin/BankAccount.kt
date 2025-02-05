import java.math.BigDecimal

class BankAccount private constructor(val accountId: String) {

    companion object {
        /**
         * Rehydrate is the only way to obtain a BankAccount instance
         */
        fun rehydrate(accountId: String, events: Collection<Event>): BankAccount {
            if (events.isEmpty()) throw IllegalArgumentException("Events cannot be empty")
            if (events.first() !is AccountCreated) throw IllegalStateException("First event needs to be AccountCreated event")

            val account = BankAccount(accountId)

            events.forEach(account::apply)

            return account
        }
    }

    private var _balance: BigDecimal = BigDecimal.ZERO
    val balance: BigDecimal get() = _balance

    fun apply(event: Event) {
        when (event) {
            is AccountCreated -> println("Account $accountId created")
            is Deposit -> {
                println("Deposited ${event.amount} to account $accountId")
                _balance += event.amount
            }
            is Withdrawal -> {
                println("Withdrew ${event.amount} from account $accountId")
                _balance -= event.amount
            }
        }
    }

    override fun toString(): String {
        return "BankAccount(accountId='$accountId', balance=$_balance)"
    }


}