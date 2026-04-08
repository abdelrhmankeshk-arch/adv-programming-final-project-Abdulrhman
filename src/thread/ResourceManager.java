package thread;

/**
 * ResourceManager - Demonstrates deadlock prevention (Section V)
 */
public class ResourceManager {
    private final Object gradeLock = new Object();
    private final Object reportLock = new Object();

    // Correct way - always acquire locks in the SAME order to prevent deadlock
    public void safeMethod() {
        synchronized (gradeLock) {
            synchronized (reportLock) {
                // safe processing - no deadlock
            }
        }
    }
}