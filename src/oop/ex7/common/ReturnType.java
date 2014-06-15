
package oop.ex7.common;

public enum ReturnType {
	INT, DOUBLE, STRING, CHAR, BOOLEAN, VOID;
	
	public static ReturnType parse( String source ) throws Exception{

        boolean isArray = false;
        if ( source != null && source.endsWith( "[]" ) ) {
            source = source.substring( 0, source.length() - 2 );
            isArray = true;

        }

		for ( ReturnType type : ReturnType.values() ) {
			if ( type.toString().equalsIgnoreCase( source ) ) {
                ReturnType typeToReturn = type;
                typeToReturn.setArray(isArray);
				return typeToReturn;
			}
			
		}
		
		return null;
	}

    private boolean isArray;

    public void setArray(boolean arr) throws Exception{
        if (this == ReturnType.VOID){
            throw new Exception("VOID[] is not a valid syntax");
        }
        isArray = arr;
    }

    public boolean isArray(){
        return isArray;
    }

    public boolean canReturn(VarType type) throws Exception{
        return (parse(type.toString()) == this && type.isArray() == this.isArray());
    }
}
