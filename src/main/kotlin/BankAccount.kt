import java.math.BigDecimal

fun rehydrate(accountId: String, events: Collection<Event>): BankAccount {
    val account = BankAccount(accountId)

    events.forEach(account::apply)

    return account
}

class BankAccount(val accountId: String) {

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