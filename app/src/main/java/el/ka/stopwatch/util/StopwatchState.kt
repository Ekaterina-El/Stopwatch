package el.ka.stopwatch.util

enum class StopwatchState(val state: String) {
    WORKING("working"),
    PAUSE("pause"),
    STOPPED("stopped");

    companion object {
        fun invertState(currentState: StopwatchState): StopwatchState {
            return if (currentState == WORKING) PAUSE else WORKING;
        }
    }
}