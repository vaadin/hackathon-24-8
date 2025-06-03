# Sudoku

Sudoku game implementation using Flow Signals. 

### Feedbacks about signals:
 - https://github.com/vaadin/flow/issues/21596 There is an increment API for NumberSignals but no built-in decrement API
 - You might run into ConcurrentModificationException quite often when using MapSignal
 - https://github.com/vaadin/flow/issues/21600 Signal.runEffect() runs everytime when any state value changes. It would be nice feature to let users pick the value that runEffect runs, same as reaction in Mobx
 - https://github.com/vaadin/flow/issues/21598 There is no way to reset all signals at once, you need to do it manually. Would be nice to have a clear method.
 - https://github.com/vaadin/flow/issues/21582 - Signals do not work after Hotswap if an effect is introduced
 - https://github.com/vaadin/flow/issues/21597 BooleanSignal is missing 
 - https://github.com/vaadin/flow/issues/21599 There should be a way to trigger Signal run Effect without changing the value, similar to requestUpdate() in Lit


An idea from redux or React dev tools is that there can be a state visualizer in Copilot where users can see the Signal value real time, without debugging.


### Other findings during Hackathon
 - Flow -  https://github.com/vaadin/flow/issues/21592 Application does not startup because of route path collision after file is removed
 - Flow - https://github.com/vaadin/flow/issues/21595 Flow view is not render when there is no React view in Full Stack Application
 - Copilot - https://github.com/vaadin/copilot-internal/issues/6091 It is not possible to see text of a Access Level link
 - Copilot - https://github.com/vaadin/copilot-internal/issues/6089 Server restart info should be gone after restart
 - Copilot - https://github.com/vaadin/copilot-internal/issues/6090 Adding spring security to project adds a deprecated Matcher
 - Copilot - https://github.com/vaadin/copilot-internal/issues/6088  Unexpected outcome when using i18n with Hilla