package oop.ex7.common;


/**
 * A general interface for SJava command line in a code file.
 * each SJava command (line of code) should implement this interface.
 */
public interface Command{

    /**
     * Validate the command in the given scope
     * @param scope scope to validate the command against
     * @return A ValidationResult object contains status and detailed message is the validation failed.
     */
    public ValidationResult isValid(Scope scope);

    /**
     * @return True if the command should create a new scope for her self. False otherwise.
     */
    public boolean isScope();

    /**
     * Perform any update needed by this command to the hosting scope.
     * any update to variables initialization or methods decleration should occur here.
     * @param scope scope to update with the command data
     */
    public void updateScope(Scope scope);
}
