package org.example.project.data

import com.expenseApp.db.AppDatabase
import com.expenseApp.db.ExpenseEntity
import org.example.project.domain.ExpenseRepository
import org.example.project.model.Expense
import org.example.project.model.ExpenseCategory

class ExpenseRepoImp(
    private val expenseManager: ExpenseManager,
    private val appDatabase: AppDatabase
) : ExpenseRepository {
    private val queries = appDatabase.expensesDbQueries
    override fun getAllExpenses(): List<Expense> {
        return queries.selectAll().executeAsList().map {
            Expense(
                id = it.id,
                amount = it.amount,
                category = ExpenseCategory.valueOf(it.categoryName),
                description = it.description
            )
        }
    }

    override fun addExpense(expense: Expense) {
        queries.transaction {
            queries.insert(amount = expense.amount, categoryName = expense.category.name, description = expense.description)
        }
    }

    override fun editExpense(expense: Expense) {
        queries.transaction {
            queries.update(id = expense.id, amount = expense.amount, categoryName = expense.category.name, description = expense.description)
        }
    }

    override fun deleteExpense(expense: Expense): Boolean {
        return try {
            queries.transaction {
                queries.delete(id = expense.id)
            }
            true
        } catch (error: Error) {
            false
        }

    }

    override fun getCategories(): List<ExpenseCategory> {
        return queries.categories().executeAsList().map { ExpenseCategory.valueOf(it) }
    }
}