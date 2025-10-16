package com.levtttech.quoteapp.main.presentation

import com.levtttech.quoteapp.quotes.presentation.Communication

interface NavigationCommunication {
    class Base : Communication.Ui<Screen>(),
            Communication.Mutable<Screen>
}