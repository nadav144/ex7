package oop.ex7.common;


public class TermType {
	
	private enum VarType {
		INT, DOUBLE, STRING, CHAR, BOOLEAN,VOID;
		
		public static VarType parse( String source ) {
			for ( VarType type : VarType.values() ) {
				if ( type.toString().equalsIgnoreCase( source ) ) {
					return type;
				}
				
			}
			
			return null;
		}
		
		public static boolean canAssignTo( VarType lhs, VarType rhs ) {
			switch ( lhs ) {
				case STRING:
				case CHAR:
				case BOOLEAN:
					return lhs.equals( rhs );
				case DOUBLE:
					if ( lhs.equals( rhs ) ) {
						return true;
					}
				case INT:
					return INT.equals( rhs );
				default:
					return false;
			}
		}
	}
	
	private VarType type;
	private boolean isArray;
	public TermType(VarType type,boolean isArray) {
		this.type=type;
		this.isArray=isArray;
		
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
	
}
