package Task1;
/**
 * Class BlockStack
 * Implements character block stack and operations upon it.
 *
 * $Revision: 1.4 $
 * $Last Revision Date: 2019/02/02 $
 *
 * @author Serguei A. Mokhov, mokhov@cs.concordia.ca;
 * Inspired by an earlier code by Prof. D. Probst

 */

class BlockStack1
{
	/**
	 * # of letters in the English alphabet + 2
	 * Modified to restrict the accesssibility. A.J.U.U
	 */
	private static final int MAX_SIZE = 28; //was public

	/**
	 * Default stack size
	 * Modified to restrict the accesssibility. A.J.U.U
	 */
	private static final int DEFAULT_SIZE = 6; //was public

	/**
	 * Current size of the stack
	 * Modified to restrict the accesssibility. A.J.U.U
	 */
	private int iSize = DEFAULT_SIZE;

	/**
	 * Current top of the stack
	 * Modified to restrict the accesssibility. A.J.U.U
	 */
	private int iTop  = 3;

	/**
	 * stack[0:5] with four defined values
	 * Modified to restrict the accesssibility. A.J.U.U
	 */
	private char acStack[] = new char[] {'a', 'b', 'c', 'd', '*', '*'};

	/**
	 * Access number representing how many times the stack was accessed.
	 * Added and restricted the accesssibility. A.J.U.U
	 */
	private int stackAccessCounter=0;

	/**
	 * Default constructor
	 */
	public BlockStack1()
	{
	}

	/**
	 * Supplied size
	 */
	public BlockStack1(final int piSize)
	{


                if(piSize != DEFAULT_SIZE)
		{
			this.acStack = new char[piSize];

			// Fill in with letters of the alphabet and keep
			// 2 free blocks
			for(int i = 0; i < piSize - 2; i++)
				this.acStack[i] = (char)('a' + i);

			this.acStack[piSize - 2] = this.acStack[piSize - 1] = '*';

			this.iTop = piSize - 3;
                        this.iSize = piSize;
		}
	}

	/**
	 * Picks a value from the top without modifying the stack
	 *  Modified function to keep track of the stack access number A.J.U.U
	 * @return top element of the stack, char
	 */
	public char pick()
	{
		incrementAccessCounter(); //Here
		return this.acStack[this.iTop];
	}

	/**
	 * Returns arbitrary value from the stack array
	 *  Modified function to keep track of the stack access number A.J.U.U
	 * @return the element, char
	 */
	public char getAt(final int piPosition)
	{
		incrementAccessCounter(); //Here
		return this.acStack[piPosition];
	}

	/**
	 * Standard push operation
	 * Modified function to keep track of the stack access number A.J.U.U
	 * Modified to show operation completion message A.J.U.U
	 */
	public void push(final char pcBlock)
	{
		incrementAccessCounter(); //Here
		this.acStack[++this.iTop] = pcBlock;
		System.out.println("The stack was successfuly pushed!"); //Here
	}

	/**
	 * Standard pop operation
	 * Modified function to keep track of the stack access number A.J.U.U
	 * Modified to show operation completion message A.J.U.U
	 * @return ex-top element of the stack, char
	 */
	public char pop()
	{
		char cBlock = this.acStack[this.iTop];
		this.acStack[this.iTop--] = '*'; // Leave prev. value undefined
		incrementAccessCounter(); //Here
		System.out.println("The stack was successfuly popped!"); //Here
		return cBlock;
	}

	public int getTop(){
		return iTop;

	}
	public int getSize(){
		return iSize;

	}
	public int getAccessCounter(){ //weird function
		return stackAccessCounter;

	}

	public void incrementAccessCounter(){
		stackAccessCounter++;
	}

	public boolean isEmpty()
{
return (this.iTop == -1);
}
}

// EOF
