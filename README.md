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

### Coroutine based API

You can call CDP methods like a normal function!

```kotlin
// Calling "Target.createPage"
// https://chromedevtools.github.io/devtools-protocol/tot/Target/#method-createTarget
cdp.target.createTarget("http://example.com/")

// Calling "Page.captureScreenshot"
// https://chromedevtools.github.io/devtools-protocol/tot/Page/#method-captureScreenshot
val (data) = cdp.page.captureScreenshot(format = "jpeg", quality = 50)

// Calling "Runtime.evaluate"
// https://chromedevtools.github.io/devtools-protocol/tot/Runtime/#method-evaluate
val (result, exceptionDetails) = cdp.runtime.evaluate("document.querySelector('#main')")
```

Also, you can subscribe CDP events you want to watch as `Flow` streams!

```kotlin
// Listening "Target.targetCreated"
// https://chromedevtools.github.io/devtools-protocol/tot/Target/#event-targetCreated
cdp.target.targetCreated
    .filter { (targetInfo) -> targetInfo.attached}
    .collect { (targetInfo) ->
        print(targetInfo.url)
    }
```

### Multi-platform support (JVM/JS/Native)

This is Kotlin Multi-platform Project, so you can use this wrapper from JVM project (including Android project), JavaScript project, native application project (including iOS project)!


### Auto generated codes for Domain methods/events/types

Codes for CDP Domains are auto generated.
We can easily update it from the protocol information of Google Chrome / Chromium.

## Install

TBD

## Roadmap

* [x] Generator from protocol JSON file
* [x] Coroutine support
* [ ] Testing with headless Chrome
* [ ] Separate artifact IDs by versions of protocol documents (1.2, 1.3RC, node, tot)
* [ ] Publish to MavenCentral
* [ ] Making "PuppeteerKT" using this 
