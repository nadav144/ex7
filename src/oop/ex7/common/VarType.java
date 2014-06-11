
package oop.ex7.common;

public enum VarType {
	INT, DOUBLE, STRING, CHAR, BOOLEAN;
	
	public static VarType parse( String source ) {
		for ( VarType type : VarType.values() ) {
			if ( type.toString().equalsIgnoreCase( source ) ) {
				return type;
			}
			
		}
		
		return null;
	}
}
