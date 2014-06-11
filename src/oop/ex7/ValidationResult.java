
package oop.ex7;

/**
 * Created by Nadav on 10/06/14.
 */
public class ValidationResult {
	
	private Boolean successful;
	private StringBuilder builder;
	
	public ValidationResult() {
		successful = true;
		builder = new StringBuilder();
	}
	
	/**
	 * @return the successful
	 */
	public Boolean getsuccessful() {
		return successful;
	}
	
	/**
	 * @param successfull
	 *            the successful to set
	 */
	public void setSuccessful( Boolean successfull ) {
		this.successful = successfull;
	}
	
	public void append( ValidationResult result ) {
		setSuccessful( getsuccessful() && result.getsuccessful() );
		builder.append( result.getBuilder().toString() );
	}
	
	/**
	 * @return the builder
	 */
	private StringBuilder getBuilder() {
		return builder;
	}
	
	public void append( String msg ) {
		builder.append( msg );
		builder.append( Character.LINE_SEPARATOR );
	}
}
