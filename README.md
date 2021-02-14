# cdp-client

This is a thin wrapper [Chrome DevTools Protocol](https://chromedevtools.github.io/debugger-protocol-viewer/) client
written in [Kotlin](https://kotlinlang.org) with coroutine based API and supporting [Kotlin Multi Platform (KMP)](https://kotlinlang.org/docs/multiplatform.html).

```kotlin
// You need launching Chrome with "--remote-debugging-port=9222" option

// In a coroutine scope
val cdp = CDPClient.create("localhost", 9222, "devtools/browser/d0cd4d55-c55b-4c3a-973f-717079053d95")
cdp.target.createTarget("http://example.com")
// Now Chrome opens a new page!
```

## Features

* Coroutine based API
* Multi-platform support (JVM/JS/Native)
* Auto generated codes for Domain methods/events/types
