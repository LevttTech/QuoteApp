package com.levtttech.quoteapp.main.presentation

import com.levtttech.quoteapp.quotes.presentation.Communication

interface NavigationCommunication {
    class Base : Communication.SingleUi<NavigationStrategy>(),
        Communication.Mutable<NavigationStrategy>
}