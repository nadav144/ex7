
package oop.ex7.common;

public interface Expression extends Command {
	
	/**
	 * Returns the type the expression will evaluate to. Will only function
	 * properly if isValid is successful
	 * 
	 * @param scope
	 *            the scope of the expression
	 * @return the type the expression will evaluate to.
	 */
	TermType getType( Scope scope );
}
