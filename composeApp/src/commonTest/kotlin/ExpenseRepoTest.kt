import org.example.project.data.ExpenseManager
import org.example.project.data.ExpenseRepoImp
import org.example.project.model.Expense
import org.example.project.model.ExpenseCategory
import kotlin.test.Test
import kotlin.test.assertContains
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class ExpenseRepoTest {
    private val expenseManager = ExpenseManager
    private val repo = ExpenseRepoImp(expenseManager)

    @Test
    fun expense_list_is_not_empty(){
        // Given
        val expenseList = mutableListOf<Expense>()

        // When
        expenseList.addAll(repo.getAllExpenses())

        // Then
        assertTrue(expenseList.isNotEmpty())
    }

    @Test
    fun add_new_expense(){
        // Given
        val expenseList = repo.getAllExpenses()
        val expenseToAdd = Expense(amount = 123.0, category = ExpenseCategory.SNACKS, description = "test")

        // When
        repo.addExpense(expenseToAdd)

        // Then
        assertContains(expenseList, expenseList.find { it.id == 4L })
    }

    @Test
    fun edit_expense(){
        // Given
        val expenseListBefore = repo.getAllExpenses()
        val expenseId = 3L

        assertNotNull(expenseListBefore.find { it.id == expenseId })
        val updatedExpense = Expense(id = expenseId, amount = 123.0, category = ExpenseCategory.SNACKS, description = "test")
        repo.editExpense(updatedExpense)

        // Then
        val listAfter = repo.getAllExpenses()
        val item = listAfter.find { it.id == expenseId }
        assertContains(listAfter, item)
        assertTrue(item?.description.equals("test"))
    }
}