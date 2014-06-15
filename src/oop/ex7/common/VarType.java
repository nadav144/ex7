
package oop.ex7.common;

public enum VarType {
	INT, DOUBLE, STRING, CHAR, BOOLEAN;

    private VarType(){
        isArray = false;
    }

    private boolean isArray;

    public void setArray(boolean arr){
        isArray = arr;
    }

    public boolean isArray(){
        return isArray;
    }
	
	public static VarType parse( String source ) {

        boolean isArray = false;
        if ( source != null && source.endsWith( "[]" ) ) {
            source = source.substring( 0, source.length() - 2 );
            isArray = true;
        }

        for ( VarType type : VarType.values() ) {
			if ( type.toString().equalsIgnoreCase( source ) ) {
				VarType typeToReturn = type;
                typeToReturn.setArray(isArray);
                return typeToReturn;
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
