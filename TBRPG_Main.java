package com.ashen.ide.TextBasedRPG;

import java.util.*;
//import java.io.*;
//import static java.lang.System.*;
import java.util.Scanner;
//import java.lang.Math;
import java.lang.Integer;

public class TBRPG_Main {
	
	  public static boolean Debug = false;
	  public static Scanner scan = new Scanner(System.in);
	  
	  public static boolean Repeat = true;
	  public static String Input = new String();
	  public static int Output = -1;
	  public static String Prompt = new String();
	  public static boolean PromptUser = true;
	  public static boolean InBattle = false;
	  public static int PromptNumber = 0;
	  public static String befPause = "";
	  
	  public static boolean doTutorial = false;
	  // 0 = empty, 1 = Stick, 2 = Rusty Dagger, 3 = Steel Shortsword, 4 = Steel Longsword, 5 = Steel Battleaxe, 6 = Shortbow, 7 = Longbow, 8 = Boomstick, 9 = AK47
	  // 0 = Neutral type, 1 = Piercing type, 2 = Slashing type, 3 = Blunt type, 4 = Chopping type, 5 = Tearing type, 6 = Poison/chemical type, 7 = Burning type
	  // Add weaponname*type# (if applicable add) .type#-type#.type#
	  public static String weaponName[] = new String[]
	  {"empty","stick","rusty dagger","steel shortsword", "steel longsword","steel battleaxe","shortbow","longbow","boomstick","ak47"};
	  public static int weaponType[] = new int[]
	  {3, 13, 125, 12, 21, 24, 1, 1, 135, 15};
	  public static double weaponDamage[] = new double[]
	  {0.1, 0.25, 0.75, 1.5, 2, 2.5, 1, 2, 3, 10};
	  
	  // 0 = No resist, 1 = Piercing resist, 2 = Slashing resist, 3 = Blunt resist, 4 = Chopping resist, 5 = Tearing resist, 6 = Poison/chemical resist, 7 = Burning resist
	  public static String armorName[] = new String[]
	  {"empty", "Regular Clothing", "Leather Protectors", "Reinforced Leather Armor", "Chainmail", "Full Plate Armor", "Barel", "God Armor"};
	  public static int armorType[] = new int[]
	  {0, 2, 1267, 124567, 1245, 1234567, 0, 1234567};
	  public static double armorTuffness[] = new double[]
	  {0.1, 0.2, 0.5, 0.9, 2, 2, 10};
	  
	  public static String locations[] = new String[]
	  {"Traveling", "City", "Grasslands", "Forest", "Badlands", "Dungeon & Catacombs", "Mines", "Volcano", "Custom Dimention", "Secret... Shhh!"};
	  
	  public static String GameState = "a0-b0-c0c*0-d0-e0-f0-g0h0i0-j0j*0j**0-k0k*5k**0k***0-aa0";
	  
	  public static TBRPG_Character[] Characters = new TBRPG_Character[]{new TBRPG_Character(0)};
	  public static ArrayList<Boolean> charAtk = new ArrayList<>();
	  public static TBRPG_Enemy[] Enemies = new TBRPG_Enemy[0];
	  public static ArrayList<Boolean> enemyAtk = new ArrayList<>();
	  
	  /*
	    a - Menu location - 0 = main menu, 1 = Pause, 2 = Equipment menu, 3 = Shop, 9 = No Menu/Zone/Battle
	    b - Zone - 0 = Traveling, 1 = City, 2 = Grasslands, 3 = Forest, 4 = Badlands, 5 = Dungeon & Catacombs, 6 = Mines, 7 = Volcano, 8 = secret 1, 9 = secret 2
	    c - Main hand - 0 = empty, 1 = Stick, 2 = Rusty Dagger, 3 = Steel Shortsword, 4 = Steel Longsword, 5 = Steel Battleaxe, 6 = Shortbow, 7 = Longbow, 8 = Boomstick,
	                    9 = AK47
	    d - Off hand - 0 = empty, 1 = Duel Wield (add space->#4a), 2 = Wooden Buckeler, 3 = Reinforced Buckeler, 4 = Steel Shield, 5 = Torch, 6 = Other Inventory Item
	    e - Armor - 0 = empty, 1 = Regular Clothing, 2 = Leather Protectors, 3 = Reinforced Leather Armor, 4 = Chainmail, 5 = Full Plate Armor, 6 = Barel,
	                7 = God Armor(hotdog suit)
	    f - Accessories - 0 = none, 1 = Farmer, 2 = Worker, 3 = Aristocrat, 4 = Monster, 5 = Angel, 6 = Gender Swap, 7 = The King
	    g,h,i - Consumables - 0 = none, 1 = Bandages, 2 = Food, 3 = Good Food, 4 = Ale, 5 = Arrows, 6 = Boom Powder, 7 = Sword Sharpener, 8 = Rock of Distraction,
	                          9 - Win Game Button
	    j-j** - Gold - A number from 0 to 999
	    k-k*** - Health - A number from 0.0 to 999.9
	    aa - Tutorial stage - 0 = New Game, 1 = Entered Tutorial, 2 = Traveling, 9 = Stop Tutorial Checks
	    
	  */
	  
	  // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ RUN LOOPS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	  
	  public static void main (String[] str)
	  {
	    while(Repeat == true)
	    {update();}
	  }
	  
	  public static void update ()
	  {
	    //*********GAME CHECKS*********
	    if(InBattle == true && !getGameState("a").equals("1")){checkInput(new String[0], false, false);}
	    else if(getGameState("a").equals("0")){mainMenu();}
	    else if(getGameState("a").equals("1")){pauseMenu();}
	    else if(!getGameState("aa").equals("9") && !getGameState("a").equals("1")){doTotorial();}
	    
	    else{Repeat = false; throw new RuntimeException("Unexpected update end.");}
	  }
	  
	  // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ FUNCTIONS ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	  
	  // ================================================== UTILITY ==================================================
	  
	  public static void getInput(String prompt)
	  {
	    PromptNumber++; Prompt = prompt;
	    if(prompt != "" && prompt != null){System.out.println(prompt);}
	    Input = scan.nextLine();
	  }
	  
	  
	  
	  public static int[] checkInput(String[] answers, boolean caseSens, boolean repeatIfError)
	  {
	    int[] matchedInputs = new int[]{-1};
	    int[] emptyArr = new int[]{-1};
	    int a = 1;
	    int cmd = 0;
	    
	    while(cmd != -1)
	    {
	      if(Input.equals("end"))
	      {
	        Repeat = false;
	        System.out.println("End of program, save code: " + GameState);
	        cmd = -1;
	      }
	      
	      if(Input.equals("savecode"))
	      {
	        System.out.println(GameState + "\n");
	        cmd = 1; if(InBattle == false && !answers[0].equals("end")){getInput(Prompt);}
	      }
	      
	      if(Input.indexOf("/debug") == 0)
	      {
	        if(Input.equals("/debug debugText = true") == true){Debug = true; System.out.println("DEBUG: debugText = true" + "\n");}
	        else if(Input.equals("/debug debugText = false") == true){Debug = false; System.out.println("DEBUG: debugText = true" + "\n");}
	        cmd = 1; if(InBattle == false && !answers[0].equals("end")){getInput(Prompt);}
	      }
	      
	      if(Input.indexOf("/?") == 0)
	      {
	        System.out.println("\nINVENTORY:\n\'inv\' - Enter inventory\n\'pickup\' - Pick up ground item.");
	        System.out.println("\'equip\' - If not a consumable, equips to its slot, drops old item on ground. If consumable," +
	                           "add a space and a 1, 2, or 3 for the consumable slot to equip to." + "\n");
	        
	        System.out.println("BATTLE:\n\'atk\'/\'!\' - Perform default attack to first enemy. Add a space and a <,|, or > for light, stab, and heavy respectively." +
	                           "Add a space and a \'#\' and a number to specify the enmy to attack.");
	        System.out.println("\'block\'/\'@\' - Performs a block. Add a space and a 1 or 2 for angle block (high arcing arows) and two" +
	                           "handed block (cannot attack) respectively");
	        System.out.println("\'consume\'/\'#\' - Uses the consumable in slot 1. Add a space and a 2 or 3 to use slot 2 or 3." + "\n");
	        
	        System.out.println("TRAVEL:\nZONES: 1 = City, 2 = Grasslands, 3 = Forest, 4 = Badlands, 5 = Dungeon & Catacombs, 6 = Mines, 7 = Volcano");
	        System.out.println("\'travel\'/\'*\' - Travels to the city by default, add a space and numbers 1-7 for each of the zones." + 
	                           "Traveling costs first food than health if you run out." + "\n");
	        
	        System.out.println("SHOP:\n\'buy #\' - Purchases the item #, requires a number, a seccond prompt will ask for you to conferm.");
	        System.out.println("\'sell\' - Will sell the first ground item by default, add a space and \'all\' to sell all ground items," +
	                           "a seccond prompt will ask for you to conferm." + "\n");
	        
	        System.out.println("OTHER:\n\'help\'/\'?\' - Will output this tutorial again.\n\'savecode\' - Outputs your current save code to input the next time you play." +
	                           "\n");
	        System.out.println("\'pause\' - Will pause game actions.\n\'resume\' - Will resume game actions after pausing." + "\n");
	        cmd = 1; if(InBattle == false && !answers[0].equals("end")){getInput(Prompt);}
	      }
	      
	      if(Input.equals("pause"))
	      {
	        befPause = getGameState("a");
	        GameState = updateGameState("a", "1");
	        System.out.println("Pauseing... GameState:" + GameState + " befPause: " + befPause);
	        cmd = -1; answers[0] = "end";
	      }
	      
	      if (matchedInputs[0] == emptyArr[0] && cmd == 0 && InBattle == false)
	      {
	        for(String ans : answers)
	        {
	          if(Input.equals(ans) && caseSens == true)
	          {
	            int[] temp = new int[matchedInputs.length];
	            temp = matchedInputs;
	            matchedInputs = new int[temp.length + 1];
	            for (int i = 0; i < temp.length; i++)
	            {matchedInputs[i] = temp[i];}
	            matchedInputs[temp.length] = a - 1;
	          }else if(Input.toLowerCase().equals(ans.toLowerCase()) && caseSens == false)
	          {
	            int[] temp = new int[matchedInputs.length];
	            temp = matchedInputs;
	            matchedInputs = new int[temp.length];
	            for (int i = 0; i < temp.length; i++)
	            {matchedInputs[i] = temp[i];}
	            matchedInputs[temp.length - 1] = a - 1;
	          }
	          a++;
	        }
	        if(repeatIfError == false && matchedInputs[0] == emptyArr[0]){emptyArr[0] = -2; cmd = -1;}
	        else if(repeatIfError == true && matchedInputs[0] == emptyArr[0]){System.out.println("\n" + "Invalid Input! Try again:" + "\n"); getInput(Prompt); a = 1;}
	      }else if(cmd != 0 && cmd != -1){cmd = 0;}
	      
	      if(matchedInputs[0] != emptyArr[0]){cmd = -1;}
	      
	      if(InBattle == true)
	      {
	        boolean inFight = false; int x = 0; for(TBRPG_Enemy enemy : Enemies)
	        {
	          if(enemy.getHealth() > 0){inFight = true;}
	          else
	          {
	            if(Enemies.length > 1 || (Enemies.length >= 2 && Enemies[x] == Enemies[x - 1]))
	            {Enemies[x] = Enemies[x + 1];}
	            else{Enemies = new TBRPG_Enemy[0];}
	            System.out.println("\n-=- Enemy: " + enemy.getName().toUpperCase() + " was slain -=-\n");
	          }
	          x++;
	        }
	        
	        if(InBattle == true && inFight == true)
	        {
		      if (!charAtk.contains(false) && !enemyAtk.contains(false)) 
		      {charAtk.clear(); for(TBRPG_Character ch : Characters) {charAtk.add(false); ch.charStatus();} for(TBRPG_Enemy ch : Enemies) {enemyAtk.add(false); ch.enemyStatus();}} // int i = 0; i <= Characters.length; i++
		      else
		      {
		    	if(charAtk.size() != Characters.length) 
		    	{
		       	  boolean temp[] = new boolean[Characters.length];
		       	  for(int i = 0; i < Characters.length; i++) {if(Characters.length != 0 && i < Characters.length - 1) {temp[i] = charAtk[i];}
		       	  	  else if(Characters.length != 0) {temp[i] = false;}} charAtk = temp; System.out.println("New Character list: " + charAtk);
		      	}
		    	if(enemyAtk.length != (Enemies.length)) 
		        {
		        	boolean temp[] = new boolean[Enemies.length];
		        	for(int i = 0; i < Enemies.length; i++) {if(Enemies.length != 0 && i < Enemies.length - 1) {temp[i] = enemyAtk[i];}
		        		else if(Enemies.length != 0) {temp[i] = false;}} enemyAtk = temp; System.out.println("New Enemy list: " + enemyAtk);
		        }
		      }
		      
	          getInput("Input command to attack enemy: (/? for help)");
	          boolean badIn = false;
	          if(Input.indexOf("atk") == 0 || Input.indexOf("!") == 0)
	          {
	            boolean atk = false;
	            if(Input.equals("atk") || Input.equals("!")){Characters[0].charAttack(Enemies[0]); atk = true;}
	            else if((Input.indexOf("atk ") != -1 && Input.length() >= 5) || (Input.indexOf("! ") != -1 && Input.length() >= 3))
	            {
	              Integer y = 1; for(TBRPG_Enemy enemy : Enemies)
	              {
	                if (Input.substring(Input.indexOf(" ") + 1, Input.indexOf(" ") + 2).equals(y.toString()) && enemy.getHealth() > 0)
	                {Characters[0].charAttack(enemy); atk = true;}
	                //if (Input.substring(Input.indexOf("  ") + 1, Input.indexOf(" ") + 2).equals(y.toString()) && enemy.getHealth() > 0) added attack type,requires 2 spaces
	                //{charAttack(weaponType[Integer.parseInt(getGameState("c"))], enemy); enemy.getStatus();} 
	                y++;
	              }
	            } else {System.out.println("\nInvalid attack! Try again:");}
	            if(atk == false) {badIn = true;}
	          }
	          else if(Input.indexOf("block") == 0 || Input.indexOf("@") == 0)
	          {
	            
	          }
	          else
	          {
	            System.out.println("\nInvalid attack! Try again:"); badIn = true;
	          }
	          
	          if(badIn == false) { for(TBRPG_Enemy enemy : Enemies)
	          {
	            if(enemy.getLastAttacked() == 0 || PromptNumber - enemy.getSpeed() == enemy.getLastAttacked())
	            {enemy.attack(Characters[0]);}
	          }}
	        }else{InBattle = false;}
	      }
	      if(answers.length > 0 && answers[0].equals("end")){cmd = -1;}
	    }
	    return matchedInputs;
	  }
	  
	  
	  
	  public static String updateGameState(String stateToChange, String changeTo)
	  {
	    if(Debug == true){System.out.println("DEBUG: State before update: " + GameState + "\n");}
	    String updatedState = new String(GameState);
	    String[] changeToComp = new String[changeTo.length()];
	    boolean z = true; int x = 0;
	    for(int i = 0; i < changeTo.length(); i++)
	    {changeToComp[i] = changeTo.substring(i, i + 1); if(Debug == true){System.out.println("DEBUG: State Changing: " + changeToComp[i]);}}
	    while(z == true)
	    {
	      if(GameState.indexOf(stateToChange + "*") == -1 || x <= changeToComp.length){z = false;}
	      String a = GameState.substring(0,GameState.indexOf(stateToChange) + stateToChange.length());
	      String b = GameState.substring(GameState.indexOf(stateToChange) + 1 + stateToChange.length()); //Add offset system, changed how game state stores
	      updatedState = a + changeToComp[x] + b;
	      stateToChange = stateToChange + "*"; x++;
	    }
	    if(Debug == true){System.out.println("DEBUG: State updated to: " + updatedState + "\n");}
	    return updatedState;
	  }
	  
	  
	  
	  public static String getGameState(String stateToGet)
	  {
	    String State = GameState.substring(GameState.indexOf(stateToGet) + stateToGet.length(), GameState.indexOf(stateToGet) + 1 + stateToGet.length());
	    while(GameState.indexOf(stateToGet + "*") != -1)
	    {
	      stateToGet = stateToGet + "*";
	      State = State + GameState.substring(GameState.indexOf(stateToGet) + stateToGet.length(), GameState.indexOf(stateToGet) + 1 + stateToGet.length());
	    }
	    if(Debug == true){System.out.println("DEBUG: Returning state: " + stateToGet + "\nDEBUG: State: " + State + "\nDEBUG: Current game-state: " + GameState+ "\n");}
	    return State;
	  }
	  
	  
	  
	  // ================================================== INTERACTIONS AND SEQUENCES ==================================================
	  
	  
	  public static void doTotorial ()
	  {
	    if((getGameState("a").equals("9")) && (getGameState("aa").equals("0"))) // If Pause Menu and Tutorial = false
	    {
	      int[] tIn = new int[1];
	      getInput("Would you like to do the tutorial? - Enter \'yes\' or \'no\':");
	      tIn[0] = checkInput(new String[]{"yes", "no"}, false, true)[0];
	      
	      if(tIn[0] == 0)
	      {
	        GameState = updateGameState("aa", "1");
	        doTutorial = true;
	        PromptUser = false;
	      }
	      else if(tIn[0] == 1)
	      {
	        GameState = updateGameState("aa", "1");
	        GameState = updateGameState("a", "9");
	        PromptUser = false;
	      }
	    }
	    else if((GameState.substring(GameState.length() - 1).equals("1") == true) && doTutorial == true || Input.equals("help") || Input.equals("?") ) // Tutorial
	    {
	      System.out.println("\n" + "Welcome to Text Quest, the basic controls of the game primarily include your keybord. User commands go as follows:" + "\n");
	      
	      Input = "/?";
	      checkInput(new String[]{"end"}, false, false);
	      
	      int[] tIn = new int[1];
	      getInput("\nWould you like to practice combat? - Enter \'yes\' or \'no\'");
	      tIn[0] = checkInput(new String[]{"yes", "no"}, false, true)[0];
	      
	      if(tIn[0] == 0)
	      {
	        System.out.println("\nEnter action command from \'BATTLE\' section of tutorial:\n");
	        
	        InBattle = true;
	        Enemies = new TBRPG_Enemy[]{new TBRPG_Enemy(1)};
	        Enemies[0] = new TBRPG_Enemy(Enemies[0].getNumber(),"Dummy", null, "neutral", 1, 25, 1, 0, 0, 1);
	        Enemies[0].getStatus();
	      }
	      doTutorial = false;
	      
	      GameState = updateGameState("a", "9");
	      GameState = updateGameState("b", "0");
	      GameState = updateGameState("aa", "9"); //End of tutorial
	    }
	  }
	  
	  
	  public static void mainMenu()
	  {
	    int[] tIn = new int[1];
	    System.out.println("\n\t\tWelcome To Text Quest!\n>Please enter \'load\' to enter load screen, or \'new\' for new game.\n" + 
	                       "\tEnter \'exit\' at any time to end the program.\n");
	    getInput("");
	    tIn[0] = checkInput(new String[]{"new", "load"}, false, true)[0];
	    
	    if(tIn[0] == 0)
	    {
	      System.out.println("\n" + "Loading new game..." + "\n");
	      GameState = updateGameState("a", "9");
	      Characters[0] = new TBRPG_Character(0, "neutral", "player", 1, 100, 1, 1, 0, 0);
	    }
	    else if(tIn[0] == 1)
	    {
	      getInput("Enter save code: ");
	    }
	  }
	  
	  
	  public static void pauseMenu()
	  {
	    int[] tIn = new int[1];
	    getInput("Type:\'resume\' to resume. No game actions can be made.");
	    tIn[0] = checkInput(new String[]{"resume"}, false, true)[0];
	    if(tIn[0] == 0){GameState = updateGameState("a", befPause);}
	  }
	  
	  
	  public static void townMenus()
	  {
	    int[] tIn = new int[1];
	    getInput("Welcome to the city, what is your busines? Type \'buy\' or \'sell\'");
	    tIn[0] = checkInput(new String[]{"buy", "sell"}, false, true)[0];
	    if(tIn[0] == 0){GameState = updateGameState("a", "3");}
	  }
	  
	  //TRAVEL:\nZONES: 1 = City, 2 = Grasslands, 3 = Forest, 4 = Badlands, 5 = Dungeon & Catacombs, 6 = Mines, 7 = Volcano
	  //travel or * - Travels to the city by default, add a space and numbers 1-7 for each of the zones. Traveling costs first food than health if you run out
	  
	  
	  public static void travelInteractions()
	  {
	    int destination = Integer.parseInt(Input.substring(Input.indexOf(" ") + 1, Input.indexOf(" ") + 2));
	    int[] tIn = new int[1];
	    getInput("Are you sure you want to travel to " + locations[destination].toUpperCase() + "?\n");
	    tIn[0] = checkInput(new String[]{"yes", "no"}, false, true)[0];
	    if(tIn[0] == 0){GameState = updateGameState("a", "9");}
	    else if(tIn[0] == 0){}//GameState = updateGameState("", "");
	  }
	
}
