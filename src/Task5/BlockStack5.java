package Task5;

import Task5.common5.*;

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

class BlockStack5
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
     * Access number representing how many times the stack was accessed.
     * Added and restricted the accesssibility. A.J.U.U
     */
    private int stackAccessCounter=0;



        /**
         * stack[0:5] with four defined values
         */
        private char acStack[] = new char[] {'a', 'b', 'c', 'd', '$', '$'};







        //The access counter variable
        private int accessCounter = 0;
        //The semaphore that guards the critical section, set as signaled
        private Semaphore accessCounterMutex = new Semaphore(1);
        //Helper function to safely increment the access counter
        private void incrementAccessCouter()
        {
            accessCounterMutex.Wait();
            ++accessCounter;
            accessCounterMutex.Signal();
        }

        public int getAccessCounter()
        {
            return accessCounter;
        }

        //Basic implementation to get the code compiling for task 1
        public int getTop()
        {
            return iTop;
        }

        public int getSize()
        {
            return iSize;
        }

        public boolean isEmpty()
        {
            return iTop == -1;
        }



        private Semaphore stackOperationMutex = new Semaphore(1);


        /**
         * Default constructor
         */
        public BlockStack5()
        {
        }

        /**
         * Supplied size
         */
        public BlockStack5(final int piSize)
        {


            if(piSize != DEFAULT_SIZE)
            {
                this.acStack = new char[piSize];

                // Fill in with letters of the alphabet and keep
                // 2 free blocks
                for(int i = 0; i < piSize - 2; i++)
                    this.acStack[i] = (char)('a' + i);

                this.acStack[piSize - 2] = this.acStack[piSize - 1] = '$';

                this.iTop = piSize - 3;
                this.iSize = piSize;
            }
        }

        /**
         * Picks a value from the top without modifying the stack
         * @return top element of the stack, char
         */
        public char pick()
        {
            incrementAccessCouter();
            return this.acStack[this.iTop];
        }

        /**
         * Returns arbitrary value from the stack array
         * @return the element, char
         */
        public char getAt(final int piPosition) throws Exception
        {
            if (piPosition < 0)
            {
                throw new Exception("getAt was called with an invalid parameter: " + (piPosition));
            }
            incrementAccessCouter();
            return this.acStack[piPosition];
        }

        /**
         * Standard push operation
         */
        public void push(final char pcBlock) throws PushException
        {
            stackOperationMutex.Wait();
            //Handle case when stack is empty
            if (iTop == 0)
            {
                acStack[++iTop] = 'a';
                return;
            }

            //Throw of stack is full
            int newIndex = ++this.iTop;
            if (newIndex > iSize)
            {
                throw new PushException();
            }

            this.acStack[newIndex] = pcBlock;
            incrementAccessCouter();
            stackOperationMutex.Signal();
        }

        /**
         * Standard pop operation
         * @return ex-top element of the stack, char
         */
        public char pop() throws PopException
        {
            stackOperationMutex.Wait();
            //Throw if stack is empty
            if (iTop < 0)
            {
                throw new PopException();
            }

            char cBlock = this.acStack[this.iTop];
            this.acStack[this.iTop--] = '$'; // Leave prev. value undefined
            incrementAccessCouter();
            stackOperationMutex.Signal();
            return cBlock;
        }
    
}

// EOF