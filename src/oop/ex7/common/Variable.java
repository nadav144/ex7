/**
 * 
 */

package oop.ex7.common;

/**
 * @author Jirsch
 * 
 */
public class Variable {
	
	private VarType type;
	private boolean isArray;
	private String name;
	private boolean inited;
	private int lineDeclared;
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object arg0 ) {
		if ( arg0 == null ) {
			return false;
		}
		if ( arg0 instanceof String ) {
			return getName().equals( arg0 );
		}
		if ( arg0 instanceof Variable ) {
			return getName().equals( ( (Variable) arg0 ).getName() );
		}
		return false;
	}
	
	/**
	 * @return the type
	 */
	public VarType getType() {
		return type;
	}
	
	/**
	 * @return the isArray
	 */
	public boolean isArray() {
		return isArray;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the inited
	 */
	public boolean isInited() {
		return inited;
	}
	
	/**
	 * @return the lineDeclared
	 */
	public int getLineDeclared() {
		return lineDeclared;
	}
	
}
