package banksystems.bank.bankapp.banking.room

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.SmallTest
import androidx.test.runner.AndroidJUnit4
import banksystems.bank.bankapp.banking.getOrAwaitValue
import banksystems.bank.bankapp.banking.models.Transaction
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@SmallTest
class TransactionDataDaoTest {
    @get:Rule
    var instantTaskExecutorRule= InstantTaskExecutorRule()
    private lateinit var usersTransactionsDatabase: UsersTransactionsDatabase
    private lateinit var transactionDataDao: TransactionDataDao

    @Before
    fun setup() {
        usersTransactionsDatabase = Room.inMemoryDatabaseBuilder(

            ApplicationProvider.getApplicationContext(), UsersTransactionsDatabase::class.java
        ).allowMainThreadQueries().build()
        transactionDataDao = usersTransactionsDatabase.transactionDataDao

    }

    @After
    fun teardown() {
        usersTransactionsDatabase.close()
    }

    @Test
    fun insertTransaction() = runBlockingTest {
        val transction = Transaction("firstuser", "seconduser", "45", "7:00AM")
        transactionDataDao.InsertTransction(transction)
        val alltransactionsData = transactionDataDao.GetAllTransctionsData().getOrAwaitValue()
        assertThat(alltransactionsData).contains(transction)
    }

}