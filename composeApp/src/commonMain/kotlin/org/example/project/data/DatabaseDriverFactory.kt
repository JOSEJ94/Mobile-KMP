package org.example.project.data

import app.cash.sqldelight.db.SqlDriver

expect class DatabaseDriverFactory : SqlDriver {
   fun createDriver(): SqlDriver
}