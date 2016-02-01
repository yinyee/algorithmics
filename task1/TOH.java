package task1;
/**
 * This implementation is derived from the Tower of Hanoi Algorithm
 * Source Code available at
 * http://www.softwareandfinance.com/Java/TowerOfHanoi_Algorithm.html
 * authored by Kathiresan.
 */

import java.util.*;

class TOH {
    static int movecount = 0;
    static public void Solve2DiscsTOH ( Stack<Integer> source, Stack<Integer> temp, Stack<Integer> dest ) {
        temp.push ( source.pop() );
        movecount++;
        PrintStacks();
        dest.push ( source.pop() );
        movecount++;
        PrintStacks();
        dest.push ( temp.pop() );
        movecount++;
        PrintStacks();
    }
    static public int SolveTOH ( int nDiscs, Stack<Integer> source, Stack<Integer> temp, Stack<Integer> dest ) {
        if ( nDiscs <= 4 ) {
            if ( ( nDiscs % 2 ) == 0 ) {
                Solve2DiscsTOH ( source, temp, dest );
                nDiscs = nDiscs - 1;
                if ( nDiscs == 1 ) {
                    return 1;
                }
                temp.push ( source.pop() );
                movecount++;
                PrintStacks();
                Solve2DiscsTOH ( dest, source, temp );
                dest.push ( source.pop() );
                movecount++;
                PrintStacks();
                SolveTOH ( nDiscs, temp, source, dest );
            } else {
                if ( nDiscs == 1 ) {
                    return -1;
                }
                Solve2DiscsTOH ( source, dest, temp );
                nDiscs = nDiscs - 1;
                dest.push ( source.pop() );
                movecount++;
                PrintStacks();
                Solve2DiscsTOH ( temp, source, dest );
            }
            return 1;
        } else if ( nDiscs >= 5 ) {
            SolveTOH ( nDiscs - 2, source, temp, dest );
            temp.push ( source.pop() );
            movecount++;
            PrintStacks();
            SolveTOH ( nDiscs - 2, dest, source, temp );
            dest.push ( source.pop() );
            movecount++;
            PrintStacks();
            SolveTOH ( nDiscs - 1, temp, source, dest );
        }
        return 1;
    }
    static public Stack<Integer> A = new Stack<Integer>();
    static public Stack<Integer> B = new Stack<Integer>();
    static public Stack<Integer> C = new Stack<Integer>();
    static public void PrintStacks() {
        if ( countA != A.size() ||
                countB != B.size() ||
                countC != C.size() ) {
            int diffA = A.size() - countA;
            int diffB = B.size() - countB;
            if ( diffA == 1 ) {
                if ( diffB == -1 ) {
                    System.out.print ( "Move Disc " + A.peek() + " From B To A" );
                } else {
                    System.out.print ( "Move Disc " + A.peek() + " From C To A" );
                }
            } else if ( diffB == 1 ) {
                if ( diffA == -1 ) {
                    System.out.print ( "Move Disc " + B.peek() + " From A To B" );
                } else {
                    System.out.print ( "Move Disc " + B.peek() + " From C To B" );
                }
            } else {
                if ( diffA == -1 ) {
                    System.out.print ( "Move Disc " + C.peek() + " From A To C" );
                } else {
                    System.out.print ( "Move Disc " + C.peek() + " From B To C" );
                }
            }
            countA = A.size();
            countB = B.size();
            countC = C.size();
            System.out.println();
        }
        PrintStack ( A );
        System.out.print ( " , " );
        PrintStack ( B );
        System.out.print ( " , " );
        PrintStack ( C );
        System.out.print ( " , " );
    }
    static int countA = 0;
    static int countB = 0;
    static int countC = 0;
    static public void PrintStack ( Stack<Integer> s ) {
        System.out.print ( s.toString() );
    }
    public static void main ( String[] args ) {
        try {
            int maxdisc = 0;
            String inpstring = args[0];
            movecount = 0;
            maxdisc = Integer.parseInt ( inpstring );
            if ( maxdisc <= 1 || maxdisc >= 10 ) {
                System.out.println ( "Enter between 2 - 9" );
                return;
            }
            for ( int i = maxdisc; i >= 1; i-- ) {
                A.push ( i );
            }
            countA = A.size();
            countB = B.size();
            countC = C.size();
            PrintStacks();
            SolveTOH ( maxdisc, A, B, C );
            System.out.println ( "Total Moves = " + movecount );
            while ( C.size() > 0 ) {
                C.pop();
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}