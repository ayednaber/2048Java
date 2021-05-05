/**
 * Author: Ayed Naber
 * Revised: April 12th, 2021
 * 
 * Description: This file will be responsible for running all of the unit tests
 * for my modules.
 */

package src;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
   TestTileT.class,	
   TestBoardT.class,
   TestBoardOps.class
})

public class AllTests
{
}
