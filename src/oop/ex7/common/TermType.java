
package oop.ex7.common;

import java.util.HashSet;
import java.util.Set;

/**
 * Represent a type of terms in the program. the type can be used for return value, or for paramerter and variable
 * declarations.
 *
 */
public class TermType {

    /**
     * Basic var types available in the compiler
     */
	public enum VarType {
		INT, DOUBLE, STRING, CHAR, BOOLEAN, VOID, ANY;

        /**
         * Parse var type
         * @param source string source value
         * @return var type
         * @throws IllegalArgumentException on parsing error
         */
		public static VarType parse( String source ) throws IllegalArgumentException {
			for ( VarType type : VarType.values() ) {
				if ( type.toString().equalsIgnoreCase( source ) ) {
					return type;
				}
				
			}
			
			throw new IllegalArgumentException( "Type is not valid:" + source );
		}

        /**
         * Check if the param can be assigned by a diffrent type
         * @param lhs left hand side
         * @param rhs right hand side
         * @return True if left hand side can be assigned by right hand side type. False otherwise.
         */
		public static boolean canAssignTo( VarType lhs, VarType rhs ) {
			if ( ANY.equals( rhs ) ) {
				return true;
			}
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

    /**
     * Initialize a new instance of non array TermType
     * @param type type strint rep.
     */
	public TermType( VarType type ) {
		this( type, false );
	}

    /**
     * Initialize a new instance of TermType
     * @param type type strint represenation
     * @param isArray True if the termType is array.
     */
	public TermType( VarType type, boolean isArray ) {
		this.type = type;
		this.isArray = isArray;
		
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
	
	public static boolean isArithmetic( TermType type ) {
		if ( type != null && type.getType() != null ) {
			if ( type.getType().equals( VarType.INT )
					|| type.getType().equals( VarType.DOUBLE ) ) {
				return true;
			}
		}
		return false;
	}

    /**
     * Parase the term Type from string content
     * @param source string source value
     * @return TermType matches the content
     * @throws IllegalArgumentException if parsing fails
     */
	public static TermType parse( String source ) throws IllegalArgumentException {
		TermType result = null;
		if ( source != null ) {
			source = source.trim();
			
			boolean isArray = false;
			source = source.replaceAll( "\\[\\s*\\]", "[]" );
			if ( source.endsWith( "[]" ) ) {
				source = source.substring( 0, source.length() - 2 );
				isArray = true;
			}
			
			result = new TermType( VarType.parse( source ), isArray );
		}
		
		return result;
	}

    /**
     * Get the common term type for a list of term.
     * this will return the main term type, that can cast all.
     * used mainly for arrays assignment
     * @param terms array of term types
     * @return the commomn term type
     */
	public static TermType getCommon( TermType[] terms ) {
		Set< TermType > distinctTerms = new HashSet< TermType >();
		for ( TermType term : terms ) {
			distinctTerms.add( term );
		}
		
		int count = 0;
		TermType result = null;
		for ( TermType i : distinctTerms ) {
			boolean canAssign = true;
			
			if ( i.isArray ) {
				count++;
				for ( TermType j : distinctTerms ) {
					canAssign = i.equals( j );
				}
			}
			else {
				for ( TermType j : distinctTerms ) {
					canAssign &= VarType.canAssignTo( i.getType(), j.getType() );
				}
			}
			
			if ( canAssign ) {
				result = i;
				break;
			}
		}
		
		if ( count != terms.length && count != 0 ) {
			// mixed array and non-array
			return null;
		}
		else {
			return result;
		}
	}

    /**
     * Check if type can be assigned to another type
     * @param lhs left hand side type
     * @param rhs right hand side type
     * @return True if left hand side cab be assigned by the right hand side type
     */
	public static boolean canAssignTo( TermType lhs, TermType rhs ) {
		if ( lhs.isArray() != rhs.isArray() ) {
			return false;
		}
		
		return VarType.canAssignTo( lhs.getType(), rhs.getType() );
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals( Object obj ) {
		if ( obj == null || !( obj instanceof TermType ) ) {
			return false;
		}
		TermType other = (TermType) obj;
		return getType().equals( other.getType() )
				&& isArray() == other.isArray();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return 7 * getType().hashCode() + 13 * ( isArray() ? 1 : 0 );
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getType().toString().toLowerCase() + ( isArray() ? "[]" : "" );
	}
	
}
