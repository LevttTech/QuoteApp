package com.levtttech.quoteapp.quotes.presentation

import android.view.View
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.levtttech.quoteapp.quotes.domain.QuoteDomain
import com.levtttech.quoteapp.quotes.domain.QuoteInteractor
import com.levtttech.quoteapp.quotes.domain.QuoteResult
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoInteractions
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.kotlin.any
import org.mockito.kotlin.eq
import org.mockito.kotlin.isA
import org.mockito.kotlin.whenever

@HiltAndroidTest
@ExperimentalCoroutinesApi
class QuotesViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var interactor: QuoteInteractor

    @Mock
    private lateinit var communications: QuotesCommunications

    @Mock
    private lateinit var handle: QuoteHandleRequest

    @Mock
    private lateinit var lifecycleOwner: LifecycleOwner

    @Mock
    private lateinit var progressObserver: Observer<Int>

    @Mock
    private lateinit var stateObserver: Observer<UiState>

    @Mock
    private lateinit var quotesObserver: Observer<List<QuoteUi>>

    private lateinit var viewModel: QuotesViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = QuotesViewModel(interactor, communications, handle)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `handle with null onComplete should not crash`() = runTest(testDispatcher) {
        doAnswer { invocation ->
            null
        }.whenever(handle).handle(any(), any(), any())

        viewModel.init(true)

        verify(handle).handle(any(), any(), any())
        verify(communications, never()).showState(any())
    }
    @Test
    fun `init first run should call interactor init and clear text`() = runTest(testDispatcher) {
        doAnswer { invocation ->
            val onComplete = invocation.getArgument<(() -> Unit)?>(2)
            onComplete?.invoke()
            null
        }.whenever(handle).handle(any(), any(), any())

        viewModel.init(true)

        verify(handle).handle(
            eq(viewModel.viewModelScope), any(), any()
        )
        verify(communications).showState(isA<UiState.ClearText>())
        verifyNoInteractions(interactor)
    }
    @Test
    fun `clearText should show clear state`() {
        viewModel.clearText()

        verify(communications).showState(isA<UiState.ClearText>())
    }
    @Test
    fun `viewModel should implement all interfaces`() {
        assertTrue(viewModel is FetchQuote)
        assertTrue(viewModel is Init)
        assertTrue(viewModel is ClearText)
        assertTrue(viewModel is ObserveQuotes)
    }
    @Test
    fun `init not first run should do nothing`() = runTest(testDispatcher) {
        viewModel.init(false)

        verifyNoInteractions(interactor)
        verifyNoInteractions(communications)
        verifyNoInteractions(handle)
    }

    @Test
    fun `fetchQuote should call interactor quote`() = runTest(testDispatcher) {
        viewModel.fetchQuote()

        verify(handle).handle(
            eq(viewModel.viewModelScope),
            any(),
            eq(null)
        )
        verifyNoInteractions(communications)
    }

    @Test
    fun `observeProgress should delegate to communications`() {
        viewModel.observeProgress(lifecycleOwner, progressObserver)

        verify(communications).observeProgress(lifecycleOwner, progressObserver)
        verifyNoInteractions(interactor, handle)
    }

    @Test
    fun `observeState should delegate to communications`() {
        viewModel.observeState(lifecycleOwner, stateObserver)

        verify(communications).observeState(lifecycleOwner, stateObserver)
        verifyNoInteractions(interactor, handle)
    }

    @Test
    fun `observeQuotes should delegate to communications`() {
        viewModel.observeQuotes(lifecycleOwner, quotesObserver)

        verify(communications).observeQuotes(lifecycleOwner, quotesObserver)
        verifyNoInteractions(interactor, handle)
    }
}

class QuotesResultMapperTest {

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var communications: QuotesCommunications

    @Mock
    private lateinit var mapper: QuoteDomain.Mapper<QuoteUi>

    private lateinit var resultMapper: QuotesResultMapper

    @Before
    fun setup() {
        resultMapper = QuotesResultMapper(communications, mapper)
    }

    @Test
    fun `map with error should show error state`() {
        val errorMessage = "Network error"

        resultMapper.map(errorMessage)

        verify(communications).showState(UiState.Error(errorMessage))
        verifyNoInteractions(mapper)
    }

    @Test
    fun `map with empty list should show error state`() {
        val emptyList = emptyList<QuoteDomain>()

        resultMapper.map(emptyList)

        verify(communications).showState(UiState.Error("Quote is empty!"))
        verifyNoInteractions(mapper)
    }

    @Test
    fun `map with non-empty list should show success state and quotes`() {
        val quoteDomain = QuoteDomain(1, "Test quote")
        val quoteUi = QuoteUi(1, "Test quote")
        val list = listOf(quoteDomain)
        `when`(mapper.map(1, "Test quote")).thenReturn(quoteUi)

        resultMapper.map(list)

        verify(communications).showState(UiState.Success("Test quote"))
        verify(communications).showQuotes(listOf(quoteUi))
        verify(mapper).map(1, "Test quote")
    }
}

class QuoteHandleRequestTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @Mock
    private lateinit var mapper: QuoteResult.Mapper<Unit>

    @Mock
    private lateinit var communications: QuotesCommunications

    @Mock
    private lateinit var dispatchersList: DispatchersList

    private lateinit var handleRequest: QuoteHandleRequest.Base
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        whenever(dispatchersList.io()).thenReturn(testDispatcher)
        whenever(dispatchersList.ui()).thenReturn(testDispatcher)

        handleRequest = QuoteHandleRequest.Base(mapper, communications, dispatchersList)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `handle should show progress and process result`() = runTest(testDispatcher) {
        val quoteResult = mock<QuoteResult>()
        val block: suspend () -> QuoteResult = { quoteResult }
        val onComplete: () -> Unit = mock()

        handleRequest.handle(this, block, onComplete)
        advanceUntilIdle()

        verify(communications).showProgress(eq(View.VISIBLE))
        verify(communications).showProgress(eq(View.GONE))
        verify(quoteResult).map(mapper)
        verify(onComplete).invoke()
        verify(dispatchersList).io()
        verify(dispatchersList).ui()
    }
}