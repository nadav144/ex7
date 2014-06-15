
package oop.ex7.common;

public interface Expression {
	
	/**
	 * Returns the type the expression will evaluate to. Will only function
	 * properly if isValid is successful
	 * 
	 * @param scope
	 *            the scope of the expression
	 * @return the type the expression will evaluate to.
	 */
	TermType getType( Scope scope );

    /**
     * Validate the command in the given scope
     * @param scope scope to validate the command against
     * @return A ValidationResult object contains status and detailed message is the validation failed.
     */
    public ValidationResult isValid(Scope scope);
}
