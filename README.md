# Sudoku

Sudoku game implementation using Flow Signals. 

### Feedbacks about signals:
 - There is an increment API for NumberSignals but no built-in decrement API
 - You might run into ConcurrentModificationException quite often when using MapSignal
 - Signal.runEffect() runs everytime when any state value changes. It would be nice feature to let users pick the value that runEffect runs, same as reaction in Mobx
 - There is no way to reset all signals at once, you need to do it manually. Would be nice to have a clear method.
 - https://github.com/vaadin/flow/issues/21582 - Signals do not work after Hotswap if an effect is introduced
 - BooleanSignal is missing 
 - There should be a way to trigger Signal run Effect without changing the value, similar to requestUpdate() in Lit

An idea from redux or React dev tools is that there can be a state visualizer in Copilot where users can see the Signal value real time, without debugging.
