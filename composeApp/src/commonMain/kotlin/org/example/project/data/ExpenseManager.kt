package org.example.project.data

import org.example.project.model.Expense
import org.example.project.model.ExpenseCategory

object ExpenseManager {
    private var currentId = 1L
    val fakeExpenseList = mutableListOf(
        Expense(id = currentId++, amount = 123.0, category = ExpenseCategory.CAR, description = "Insurance"),
        Expense(id = currentId++, amount = 666.0, category = ExpenseCategory.COFFEE, description = "I like coffee"),
        Expense(id = currentId++, amount = 777.0, category = ExpenseCategory.PARTY, description = "New year's eve")
    )

    fun addNewExpense(expense: Expense) {
        fakeExpenseList.add(expense.copy(id = currentId++))
    }

    fun editExpense(expense: Expense) {
        val expenseIndex = fakeExpenseList.indexOfFirst{ item -> item.id == expense.id }
        if(expenseIndex != -1) {
            fakeExpenseList.set(expenseIndex, expense)
        }
    }

    fun deleteExpense(expense: Expense): Boolean {
        val expenseIndex = fakeExpenseList.indexOfFirst{ item -> item.id == expense.id }
        if(expenseIndex != -1) {
            fakeExpenseList.removeAt(expenseIndex)
            return true
        }
        return false
    }

    fun getCategories(): List<ExpenseCategory> {
        return listOf(
            ExpenseCategory.GROCERIES,
            ExpenseCategory.PARTY,
            ExpenseCategory.COFFEE,
            ExpenseCategory.HOUSE,
            ExpenseCategory.OTHER,
            ExpenseCategory.CAR,
            ExpenseCategory.SNACKS
        )
    }
}