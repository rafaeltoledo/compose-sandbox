package net.rafaeltoledo.sandbox

import android.app.Application
import org.conscrypt.Conscrypt
import java.security.Security

class App : Application() {

  init {
      Security.insertProviderAt(Conscrypt.newProvider(), 1)
  }
}
