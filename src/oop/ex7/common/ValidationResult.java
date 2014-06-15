
package oop.ex7.common;

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
	public Boolean getSuccessful() {
		return successful;
	}
	
	public String getMessages() {
		return builder.toString();
	}
	
	/**
	 * @param successfull
	 *            the successful to set
	 */
	public void setSuccessful( Boolean successfull ) {
		this.successful = successfull;
	}
	
	public void append( ValidationResult result ) {
		setSuccessful( getSuccessful() && result.getSuccessful() );
		builder.append( result.getBuilder().toString() );
	}
	
	public void fail( String message ) {
		setSuccessful( false );
		append( message );
	}
	
	public void fail( Exception e ) {
		setSuccessful( false );
		append( e.getMessage() );
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
