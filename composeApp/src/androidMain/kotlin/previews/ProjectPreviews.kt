package previews

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import org.example.project.data.ExpenseManager
import org.example.project.model.Expense
import org.example.project.model.ExpenseCategory
import org.example.project.presenter.ExpensesUiState
import org.example.project.ui.AllExpensesHeader
import org.example.project.ui.ExpenseTotalHeader
import org.example.project.ui.ExpensesItem
import org.example.project.ui.ExpensesScreen

@Preview(showBackground = true)
@Composable
fun ExpenseTotalHeaderPreview() {
    ExpenseTotalHeader(123456.0)
}

@Preview(showBackground = true)
@Composable
fun AllExpensesHeaderPreview() {
    AllExpensesHeader()
}

@Preview
@Composable
fun ExpensesItemPreview() {
    ExpensesItem(
        expense = Expense(
            amount = 123.0,
            category = ExpenseCategory.GROCERIES,
            description = "test"
        ), onExpenseClick = {})
}

@Preview(showBackground = true)
@Composable
fun ExpenseScreenPreview() {
    ExpensesScreen(
        uiState = ExpensesUiState(
            expenses = ExpenseManager.fakeExpenseList,
            total = 123.0
        ), onExpenseClick = {})
}