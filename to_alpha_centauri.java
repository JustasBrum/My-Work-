package alphacentauri;
import java.util.*;



//Student Number: 19340073

//Name: Justas Grimaila

//This code is all done by me.


public class to_alpha_centauri 
{
	static Scanner sc = new Scanner(System.in);
	static Random rn = new Random();
	//MAIN MENU
	public static void main(String args[])
	{
		/* Introduction to the game. The game is a simple survival game where there will 20 turns to try and survive a randomly
		 * generated journey to alpha Centauri. Keep Hull Integrity above 10% and the count of Humans on Board above 100,000. 
		 * Anything above that will doom your journey, and the future of humanity.
		 */
		
		System.out.println("------------To Alpha Centauri------------");
		System.out.println("-----------------------------------------");
		System.out.println("-----------------------------------------");
		System.out.println("-----------------------------------------");
		System.out.println("     You have been chosen to be the     \n"
						+  "   on-ship engineer AI on the ship the    \n"
						+  "              U.S.S Hope.               \n"
						+  "------------------------------------------ \n"
						+  " Earth has been made nearly uninhabitable \n"
						+  " Humanity may continue on Alpha Centauri \n"
						+  " An on-ship engineer needs to ensure the one \n"
						+ " million people on the ship in cryosleep survive \n"
						+ " the short but dangerous journey. People will die,\n"
						+ " But you will need to minimise the damage.  \n"
						+ "===============RULES===================== \n"
						+ "Do not let the human count get below 100,000 \n "
						+"and do not let Hull Integrity get below 10%. \n"
						+"   Survive to week 25, and you will win.      \n"
						+"Remember, Humans are non-renewable. Keep them. \n"
						+"============================================= \n"
						+"        Good luck. You will need it              \n "
						+"=============================================");
		System.out.println("----------Ver. 0.8 Pre-Release------------");
		//starting values
		int week = 1;
		int hullHP = 100;
		int humans = 1000000;
		
		int input = 0;
		
		while(input != 1)
		{
			System.out.println("Press 1 to continue");
			input = 0;
			try
			{
				input = sc.nextInt();
			}
			catch(java.util.InputMismatchException mistmatchException)
			{
				System.out.println("Do NOT use as any characters. Try to only use Integers and digits. Please restart the game.");
				return;
			}
			if(input == 1)
			{
				gameStart(week,hullHP,humans,0);
			}
			else
			{
				System.out.println("Invalid Input, try again");
				continue;
			}
		}
	}
	
	
	//GAME LOGIC
	/*This is a recursive method that deals with the over all game logic. The HULL HP,
	 * /Human Count is altered over the course of the method, and then these values are returned
	 * onto the method again, but with the week value increased.
	 * 
	 */
	public static void gameStart(int a, int b, int c, int d)
	{
		
		/*
		 * This is the main method. Everything returns to this method in someway. 
		 * It will follow the game logic and prints to the screen.
		 */
		if(b < 11)
		{
			gameEnd(1);
			return;
		}
		else if(c < 100000)
		{
			gameEnd(2);
			return;
		}
		//failure checks
		if(b < 30)
		{
					
			System.out.println("Warning: Comprimised Hull has led to on ship deaths!");
			int x = (rn.nextInt(10)+1)*10000;
			c = c-x;
			System.out.println(x + " lives lost!");
									
		}
		
		System.out.println("==============================");
		System.out.println("Week: " + a);
		System.out.println("Hull HP: " + b);
		System.out.println("Humans Left:" + c);
		System.out.println("==============================");
		
		
		
		if(a == 1)
		{
			System.out.println("The Ship has lifted out of earth. After a long few days, it leaves \n"
							+ " the solar system and you are booted up for the first time.");
			a++;
			int firstinput = 0;
			while(firstinput !=1)
			{
				System.out.println("Press 1 to continue");
				firstinput = 0;
				firstinput = sc.nextInt();
				if(firstinput == 1)
				{
					gameStart(a,b,c,0);
				}
				else
				{
					System.out.println("Invalid Input, try again");
					continue;
				}
			}
			
		}
		

		if(a == 25)
		{
			gameVictory(b,c);
			return;
		}
		
		
		else if(a < 25 && a > 1)
		{
			/* This is to pick out a random event from a list of 20
			 * events.
			 * Im thinking of maybe doing an array of 20?
			 * or maybe a list? but how do you do that when you will require inputs, sometimes
			 * multiple for each event?
			 * I will begin with a way which, although arcaic, will definitely work,
			 * using switch statements
			 */
			
			//Event Picker + Unique Event-Garanteed!
			int eventNum = rn.nextInt(17) + 1;
			if(eventNum == d && d == 7 )
			{
				eventNum--;
			}
			else if(eventNum == d)
			{
				eventNum++;
			}
			
			
			//int eventNum =  13; //EVENT TESTER
			//call upon event method here
			int returnedValue = gameEvent(eventNum);
			
			/* Let's check if value is < 100,
			 * if so, it is a human count effect,
			 * if not, hull effect
			 */
			if(returnedValue > 100 || returnedValue == -100000)
			{
				//this effects human lives on the ship
				c = c - returnedValue;
			}
			else if(returnedValue < 101)
			{
				//this effects Hull HP
				b = b - returnedValue;
			}
			
			
			int pinput = 0;
			
			while(pinput != 1)
			{
				System.out.println("Press 1 to continue");
				pinput = 0;
				pinput = sc.nextInt();
				
				
				if(pinput == 1)
				{
					a++;
					gameStart(a,b,c, eventNum);
				}
				else
				{
					System.out.println("Invalid Input, try again");
					continue;
				}
			}

		}
		
	}
	
	
	//EVENTS ~~~~~~~~~~Spagetti Code Time~~~~~~~~~~~~~~~~~~~~~~~~~~ 

	
	public static int gameEvent(int num)
	{
		int returnValue = 0;
		int playerInput1 = 0;
		int playerInput2 = 0;
		switch(num)
		{
			//Event 1 Asteroid Field #1
			case 1: System.out.println("You pass through a moving asteroid field!  \n"
									 + "           What do you do?              \n"
									 +"========================================== \n"
									+ "1. Try to swerve through the Asteroids. \n"
									+ "2. Turn on the shield and wait it out. ");
									
									//1
									while(playerInput1 != 1 || playerInput1 != 2)
									{
										playerInput1 = 0;
										playerInput1 = sc.nextInt();
										
										if(playerInput1 == 1)
										{
											boolean lol = rn.nextBoolean();
											if(lol == false)
											{
												System.out.println("You have crashed into a few asteroids.\n"
																+  "   You have taken 15 Hull Damage.");
												return 15;
											}
											else
											{
												System.out.println("You have safely navigated across the asteroid field.");
												return 0;
											}
										}
										else if(playerInput1 == 2)
										{	
											boolean lol = rn.nextBoolean();
											if(lol == false)
											{
												System.out.println("The shield has drained too much battery \n"
																+  "   You have lost 100,000 lives ");
												return 100000;
											}
											else
											{
												System.out.println("The asteroid passes by, leaving you unscaved.");
												return 0;
											}
										}
										else
										{
											System.out.println("Invalid Input, try again");
											continue;
										}
									}
									break;
									
				//Event 2 Mineral Planet		
				case 2: System.out.println("A rogue planet floats by with large mineral deposits \n"
						 				+ "                  What do you do?              \n"
										+"======================================================== \n"
										+ "1. Ignore it and fly past it. \n"
										+ "2. Scan the planet surface for further things. \n"
										+ "3. Attempt to mine some minerals.");
						//1
						while(playerInput1 != 1 || playerInput1 != 2 || playerInput1 != 3)
						{
							playerInput1 = 0;
							playerInput1 = sc.nextInt();
							
							if(playerInput1 == 1)
							{
								System.out.println("You fly past the planet and continue on the journey.");
								return 0;
							}
							else if(playerInput1 == 2)
							{	
								boolean lol = rn.nextBoolean();
								if(lol == false)
								{
									System.out.println("There was a power surge with the battery while scanning\n"
													+  " The power surge lost you 100,000 lives ");
									return 100000;
								}
								else
								{
									System.out.println("You find even more minerals than you originally thought \n"
												+     " You heal your ship up with extra materials. + 10 Hull HP.");
									return -10;
								}
							}
							else if(playerInput1 == 3)
							{
								int thirtychance = rn.nextInt(100);
								if(thirtychance < 30)
								{
									System.out.println("Your ship crashes and clips into the minerals while you mine. \n"
													+ "             You take 10 Hull Damage");
									return 10;
								}
								else
								{
									System.out.println("You succeded! +10 Hull Hp!");
									return -10;
								}
							}
							else
							{
								System.out.println("Invalid Input, try again");
								continue;
							}
						}
						break;
						
				//Event 3 Nothing with nothing.
				case 3: System.out.println("You fly peacefully throughout the whole week."); break;
				
				
				//Event 4 A distant supernova.
				case 4: System.out.println("A distant supernova glimmers in the distance. You are reminded \n"
										+  "why you are taking this journey. You work even harder now with the \n"
										+  "glimmering supernova in the distant view of space, inspiring you."); break;
										
				//Event 5 Space Pirate ship
				case 5: System.out.println("You encounter a single pirate ship!They want to take over the ship  \n"
											+"                    What do you do?  \n"
											+"======================================================== \n"
											+ "1. Fight them! \n"
											+ "2. Flee! \n"
											+ "3. Surrender!");
				
					while(playerInput1 != 1 || playerInput1 != 2 || playerInput1 != 3)
					{
						playerInput1 = 0;
						playerInput1 = sc.nextInt();
						
						if(playerInput1 == 1)
						{
							System.out.println("You fly head first into the ship! You release your mechanist drones \n"
											+  "and they fly towards the pirate ship. It manages to land a few hits \n"
											+  "on you, but your drones managed to deconstruct the pirate ship in its");
							int x = rn.nextInt(16) + 1;
							System.out.println("             tracks! You lose " + x +  " Hull HP.");
							
							return x;
						}
						else if(playerInput1 == 2)
						{	

							System.out.println("There was a power surge with the battery while fleeing!\n"
											+  " The power surge lost you 100,000 lives ");
							return 100000;

						}
						else if(playerInput1 == 3)
						{
							System.out.println("You surrender the ship, and are immediately striped of your position. \n"
											 + "            The pirates Destroy the ship");
							return 100;
						}
						else
						{
							System.out.println("Invalid Input, try again");
							continue;
						}
					}
				break;
				
				//Event 6 Ship Graveyard
				case 6: System.out.println("You fly past a massive spaceship graveyard, from a long \n"
										+  "forgotten space battle that waged here many years ago. \n"
										+  "          What do you do with the ships?\n"
										+  "========================================================== \n"
										+  "1. Attempt to salvage some of the ship parts for Hull repairs. \n"
										+  "2. Leave it. Not worth the time. Could be dangerous anyways.");

						while(playerInput1 != 1 || playerInput1 != 2)
						{
							playerInput1 = 0;
							playerInput1 = sc.nextInt();
							
							if(playerInput1 == 1)
							{
								boolean lol = rn.nextBoolean();
								if(lol == false)
								{
									System.out.println("There was an active mine lodged in one of the ships!\n"
											+  "It blows up as you come near it, shredding apart some of your \n"
											+"          ships hull. You lose 15 Hull HP");
									return 15;
								}
								else
								{
									int regen = rn.nextInt(14) + 1;
									System.out.println("You manage to salvage some of the hulls safely.\n"
											+  "You use the parts and patch it onto your own hull,"
											+"earning you around " + regen + "Hull HP.");
									return -regen;
								}
								
								
								
								
							}
							else if(playerInput1 == 2)
							{	
		
								System.out.println("You fly away, paying no mind to the graveyard.");
								return 0;
		
							}
							else
							{
								System.out.println("Invalid Input, try again");
								continue;
							}
						}
					break;
					
					//event 7 Distant Neutron Star
					case 7: System.out.println("A distant Neutron Star is spotted on your on ship telescopic perception systems.\n"
												+  "while being incredibly violent, and scary up close, from this far away\n"
												+  "It looks peaceful. You are reminded of your mission."); break;
												
				   //event 8: Circuit Blowout
					case 8: System.out.println("You wake up to an alarm. A freak incident has happened with the circuitry \n"
											+"inside of the ships power supply systems. The circuits have blown out and there \n"
											+"is a chance that there might be a complete power outage if not dealt with. \n"
											+"With that power outage, comes with a chance of life support systems being downed. \n"
											+"================================================================================ \n"
											+"1. Redirect remaining Shield Generator power to the Life Support systems \n"
											+"2. Overcharge power generators and circuits to try and save Life Support systems. \n"
											+"3. Try and directly fix the circuits.");
						while(playerInput1 != 1 || playerInput1 != 2 || playerInput1 != 3)
						{
							playerInput1 = 0;
							playerInput1 = sc.nextInt();
							
							if(playerInput1 == 1)
							{
								boolean lol = rn.nextBoolean();
								if(lol == false)
								{
									System.out.println("You redirect the Shield Generator energy towards the Life Support System. \n"
											+  "It leaves your hull open to some circuitry burnout damage however! \n"
											+"                  You lose 10 Hull HP \n");
									return 10;
								}
								else
								{
									
									System.out.println("You redirect the Shield Generator energy towards the Life Support System.\n"
											+  "The Life Support System lights up, giving you free time to slowly repair the circuits \n"
											+"      preventing any damage from being done to the ship or its systems.");
									return 0;
									
								}
								
								
							}
							else if(playerInput1 == 2)
							{	
								boolean lol = rn.nextBoolean();
								if(lol == false)
								{
									System.out.println("You overcharge the the power generators! They explode fiercly, and \n"
											+  "            heavily damage your hull.");
									
											int x = (rn.nextInt(3) + 1)*10;
									System.out.println("You lose " + x + " Hull HP to the explosion");	
									return x;
								}
								else
								{
									
									System.out.println("You overcharge the power generators and the power immediately cuts out! \n"
											+  "The Life Support System are instantly turned off, but you manage to recover the power \n"
											+"      preventing any major to the hull. Still, the lives lost are tragic. \n"
											+"                        You lose 150,000 humans                   ");
									return 150000;
								}
		
							}
							else if(playerInput1 == 3)
							{
								int times = rn.nextInt(3) + 1;
								System.out.println("You dive into the maintanence vents in your ship and manually repair the circuits. \n"
													+"The ship loses power multiple times while you repair. After you come up, \n"
													+"the ship had lost power " + times + " causing you some lost lives.");
								int lives = 50000;
								for(int x = 0; x <= times;x++)
								{
									lives = lives + 50000; //double the lives lost per times power went out.
								}
								System.out.println("You have lost " + lives + " lives!");
								return lives;
							}
							else
							{
								System.out.println("Invalid Input, try again");
								continue;
							}
						}
					break;
					
					//Event 9 Fleet of Pirates
					case 9: System.out.println("You encounter a fleet of pirate ships!They want to take over the ship  \n"
							+"                    What do you do?  \n"
							+"======================================================== \n"
							+ "1. Fight them! \n"
							+ "2. Flee! \n"
							+ "3. Surrender!");

							while(playerInput1 != 1 || playerInput1 != 2 || playerInput1 != 3)
							{
								playerInput1 = 0;
								playerInput1 = sc.nextInt();
								
								if(playerInput1 == 1)
								{
									System.out.println("You fly head first into the ship! You release your mechanist drones \n"
													+  "and they fly towards the pirate ship. It manages to land a few hits \n"
													+  "on you, but your drones managed to deconstruct the pirate ships in their");
									int x = rn.nextInt(25) * 2;
									System.out.println("             tracks! You lose " + x +  " Hull HP.");
									
									return x;
								}
								else if(playerInput1 == 2)
								{	
						
									System.out.println("There was a power surge with the battery while fleeing! \n"
													+  " The power surge lost you 100,000 lives ");
									return 100000;
						
								}
								else if(playerInput1 == 3)
								{
									System.out.println("You surrender the ship, and are immediately striped of your position. \n"
													 + "            The pirates Destroy the ship");
									return 100;
								}
								else
								{
									System.out.println("Invalid Input, try again");
									continue;
								}
							}
						break;
						
						//Event 10 Space Cannibals
					case 10: System.out.println("You encounter a ship filled with space cannibals. They  \n"
							+"want the people on your ship! What do you do?  \n"
							+"======================================================== \n"
							+ "1. Fight them! \n"
							+ "2. Exchange some lives in exchange for freedom(150,000 lives) \n"
							+ "3. Flee!");

							while(playerInput1 != 1 || playerInput1 != 2 || playerInput1 != 3)
							{
								playerInput1 = 0;
								playerInput1 = sc.nextInt();
								
								if(playerInput1 == 1)
								{
									System.out.println("You wont give up without a fight! You release your mechanist drones \n"
													+  "and they fly towards the cannibal ship. It manages to land a few hits \n"
													+  "on you, but your drones manage to deconstruct the ship out of existance ");
									int x = rn.nextInt(16);
									System.out.println("             tracks! You lose " + x +  " Hull HP.");
									
									return 10;
								}
								else if(playerInput1 == 2)
								{	
						
									System.out.println("You strike a deal with the cannibals. They agree to leave you be, \n"
													+  " for now, losing you 150,000 lives");
									return 150000;
						
								}
								else if(playerInput1 == 3)
								{
									System.out.println("You blast on the hyperdrive and skedaddle out of there! You manage to sustain some hull damage  \n"
													 + "while escaping, losing you 15 hull HP");
									return 15;
								}
								else
								{
									System.out.println("Invalid Input, try again");
									continue;
								}
							}
						break;
						
						//Ice Metoroite Collision
					case 11: System.out.println("Your ship detects some irregularities ahead.\n"
										+"Seems like some space dust?\n"
										+"======================================================== \n"
										+ "1. Inspect it using your External Scanner. \n"
										+ "2. Just push through the dust. Its just dust, after all.");
									while(playerInput1 != 1 || playerInput1 != 2)
									{
										playerInput1 = 0;
										playerInput1 = sc.nextInt();
										
										if(playerInput1 == 1)
										{
											System.out.println("The scanners scan outside the ship. Seems like the dust is fragments from \n"
															+  "a long gone collision of two Oxygen-Ice Metorites. Nothing Harmful. \n"
															+  "You move on with the journey, wondering about what are the chances \n"
															+  "                  of this random collision??");
											return 0;

										}
										else if(playerInput1 == 2)
										{	
								
											System.out.println("You dive forward into space, paying no mind to the dust.");
											return 0;
										}
										else
										{
											System.out.println("Invalid Input, try again");
											continue;
										}
									}
								break;
								
								
					//Abandoned Repair Station
					case 12: System.out.println("You come across a large structure in the middle of space. \n"
												+ "       Do you wish to inspect it? \n"
												+ "======================================================== \n"
												+ "1. Inspect it closer.\n"
												+ "2. Fly past it.");
									while(playerInput1 != 1 || playerInput1 != 2)
									{
										playerInput1 = 0;
										playerInput1 = sc.nextInt();
										if(playerInput1 == 1)
										{
											System.out.println("Upon inspection, it appears to be a abandonded repair station! \n "
														     +"================================================================ \n"
														     +"1. Use it to repair your hull! \n"
														     +"2. Leave it.");
															while(playerInput2 != 1 || playerInput2 != 2)
															{
																playerInput2 = 0;
																playerInput2 = sc.nextInt();
																if(playerInput2 == 1)
																{
																	int x = rn.nextInt(12) + 3;
																	System.out.println("You have managed to use some of the repair stations tool \n"
																			+"to repair " + x + " of your Hull HP");
																	return -x;
																}
																else if(playerInput2 == 2)
																{
																	System.out.println("You fly past it, thinking that its not worth repairing.");
																	return 0;
																}
															}
															

										}
										else if(playerInput1 == 2)
										{	
								
											System.out.println("You dive forward into space, paying no mind to the structure.");
											return 0;
										}
										else
										{
											System.out.println("Invalid Input, try again");
											continue;
										}
									}
								break;
								
					//Rescue Mission from pirates	
					case 13: System.out.println("You recieve a transmission. A bunch of humans from a previous colonial \n"
												+"mission are in trouble and inside of a pirate ship that took the ship \n"
												+"captive. The survivors are borded up on their ship, and they need the   \n"
												+"help. It will require either some stealth on your behalf or they will kill \n"
												+"                the crew on board. What do you do? \n");	
							 System.out.println("========================================================================== \n"
									 			+"1. Help the crew out! \n"
									 			+"2. The risk is too much. You decide to leave them");
					
					while(playerInput1 != 1 || playerInput1 != 2)
					{
						playerInput1 = 0;
						playerInput1 = sc.nextInt();
						
						if(playerInput1 == 1)
						{
							System.out.println("You sneak near the pirate ship, with everything turned off. You don't \n"
												+"find any way in except an escape corridor in the back. You connect it while \n"
												+"the pirate snooze on their ship. Around 100,000 people make it onto your ship \n"
												+"        joining the crew in cyrosleep once they are safe." );
							return -100000;

						}
						else if(playerInput1 == 2)
						{	
				
							System.out.println("You dive forward into space, hoping those people find another way out.");
							return 0;
						}
						else
						{
							System.out.println("Invalid Input, try again");
							continue;
						}
					}
				break;
				
				//A close call
					case 14: System.out.println("You are awoken by a suprinsing alarm. Appearantly, an extremely high speed object \n"
												+"which flew past your ship. Upon further diggin into the logs, it was apperantly \n"
												+"   a high-speed asteroid, hurled from a distant black hole at light-speed. \n"
												+"                   Lucky it didnt hit, huh?");
								break;
								
								
					//Solar Flare
					case 15: System.out.println("A distant solar flare is about to knock up your ship \n"
												+"                    What do you do?!  \n"
												+"============================================================= \n"
												+"1. Turn on your shields to absorb the damage \n"
												+"2. Let the hull absorb the damage");
					
											while(playerInput1 != 1 || playerInput1 != 2)
											{
												playerInput1 = 0;
												playerInput1 = sc.nextInt();
												
												if(playerInput1 == 1)
												{
													boolean lol = rn.nextBoolean();
													if(lol == false)
													{
														System.out.println("Your shield are turned on, and absorb so much energy \n"
																		+  "    that they overload the system power supply. \n"
																		+  "You lost 100,000 humans on the ship to the power loss.");
														return 100000;
													}
													else
													{
														System.out.println("Your shields successfuly absorb the Solar Flare.");
														return 0;
													}
												}
												else if(playerInput1 == 2)
												{	
													boolean lol = rn.nextBoolean();
													if(lol == false)
													{
														System.out.println("Your hull braces the solar flare, lighting parts of it\n"
																		+  " on fire. You lose 10 Hull HP.");
														return 10;
													}
													else
													{
														System.out.println("The Solar Flare isn't strong enough to pierce your hull. \n"
																		+ "  No significant damage is done to the hull.");
														return 0;
													}
												}
												else
												{
													System.out.println("Invalid Input, try again");
													continue;
												}
											}
											break;
											
											
											
					//Comsic Horror			
					case 16: System.out.println("The radiation detection systems go haywire. Motion detection detects something big, before it too, \n"
												+"goes haywire. Re runs of the motion detection find nothing, but occasionally detects something \n "
												+"before it dissapears again. Something strange is happening. Audio Detection systems show that \n"
												+"there is a low hum just outside the ship, but its location seems to be from all directions of the ship. \n"
												+"                                  What do you do?? \n"
												+"===================================================================================================== \n"
												+"1. Investigate whatever the hell is happening. \n"
												+"2. Engage hyperdrive, and get the hell out of here.");
					
					
								while(playerInput1 != 1 || playerInput1 != 2)
								{
									playerInput1 = 0;
									playerInput1 = sc.nextInt();
									if(playerInput1 == 1)
									{
										System.out.println("========================================================================== \n"
														 +"Upon inspection, there seems to be some live creature outside of the ship. \n "
														 +"It begins to move the ship. The systems are going haywire."
													     +"================================================================ \n"
													     +"1. Send the mechanist drones to attack it! \n"
													     +"2. Engage hyperdrive, and get the hell out of there!");
														while(playerInput2 != 1 || playerInput2 != 2)
														{
															playerInput2 = 0;
															playerInput2 = sc.nextInt();
															if(playerInput2 == 1)
															{
																
																System.out.println("The mechanist drones begin to fly out, but are submurged in some kind \n"
																		+"of celestial darkness. This darkness begins to overlap the whole ship. The trusters \n"
																		+"dont work anymore. All systems are down. You feel your own artificial awareness begin \n"
																		+"to fade away. The last thing your digital sensors feel are the last glimpses of space \n"
																		+"                                itself. \n"
																		+"You boot up again, systems are nominal. You are intact, and the ship is fine. One thing \n"
																		+"     is missing however. The entire human population of the ship, is gone.");
																return 1000000;
																
															}
															else if(playerInput2 == 2)
															{
																System.out.println("You get out there while you still can. You engage hyperdrive and into safety.");
																return 0;
															}
														}
														
			
									}
									else if(playerInput1 == 2)
									{	
										System.out.println("You dive forward into space, leaving behind whatever that anomaly was.");
										return 0;
									}
									else
									{
										System.out.println("Invalid Input, try again");
										continue;
									}
								}
							break;
							
							
					//Raider Ship
					case 17: System.out.println("You encounter a single bandit ship!They want to destroy the ship  \n"
												+"                    What do you do?  \n"
												+"======================================================== \n"
												+ "1. Fight them! \n"
												+ "2. Flee! \n"
												+ "3. Surrender!");

								while(playerInput1 != 1 || playerInput1 != 2 || playerInput1 != 3)
								{
									playerInput1 = 0;
									playerInput1 = sc.nextInt();
									
									if(playerInput1 == 1)
									{
										System.out.println("You fly head first into the ship! You release your mechanist drones \n"
														+  "and they fly towards the bandit ship. It manages to land a few hits \n"
														+  "on you, but your drones managed to deconstruct the pirate ship in its");
										int x = rn.nextInt(16) + 1;
										System.out.println("             tracks! You lose " + x +  " Hull HP.");
										
										return x;
									}
									else if(playerInput1 == 2)
									{	
							
										System.out.println("There was a power surge with the battery while fleeing!\n"
														+  " The power surge lost you 100,000 lives ");
										return 100000;
							
									}
									else if(playerInput1 == 3)
									{
										System.out.println("You surrender the ship, and are immediately striped of your position. \n"
														 + "            The bandits destroy the ship");
										return 100;
									}
									else
									{
										System.out.println("Invalid Input, try again");
										continue;
									}
								}
							break;
				
				
				
					
					
					
								
					
												
												
					
					
					
					
												
												
									
											
												
					
				
						
		
					
							
									
									default: break;

			
					
		}
		
		return returnValue;
		
	}
	
	
	
	
	//END STATES
	/*These are the fail game states. If you lose, or reach a fail state, you are brought to 
	 * this method. There are 2 different endings, depending on what value you lost out
	 * on.
	 */
	
	public static void gameEnd(int a)
	{
		
		if(a == 1)//Hull Destroyed
		{
			System.out.println("You have failed your mission!");
			System.out.println("Reason: Hull Integrity has failed, and the ship has been destroyed or rendered unusable.");
		}
		else if(a == 2)//Not enough lives left to colonise
		{
			System.out.println("You have failed your mission!");
			System.out.println("Reason: The Human Count on your ship is too low to create a sustainable colony. \n "
							+ "   You have traded too many lives.");
		}
		System.out.println("Do you wish to continue?");
		int input = 0;
		while(input != 1)
		{
			System.out.println("Press 1 to continue");
			input = 0;
			input = sc.nextInt();
			if(input == 1)
			{
				main(null);
			}
			else
			{
				System.out.println("Invalid Input, try again");
				continue;
			}
		}
		
	}
	
	public static void gameVictory(int hull, int humans)
	{
		for(int x = 0; x <10;x++)
		{
			System.out.println("======================================================");
		}
		System.out.println("=================================================================");
		System.out.println("You have reached Alpha Centauri and have setup a permanent colony \n"
						 +"       on one of the habitable planets on the system.   \n"
						 +"             You have saved Humanity");
		
		System.out.println("Your stats: Hull: " + hull + "\n"
						+  "          : Humans: " + humans); 
		System.out.println("Playtest and other credits/people who helped me out \n"
						+"with finding bugs and problems with my code. \n"
						+"====================================================== \n"
						+"Bartosz, playtester and helped out with bugs. \n"
						+"Bog, playtester. \n"
						+"Ben, playtester. \n"
						+"Mario, playtester. \n"
						+"Bean, playtester. \n"
						+"Jack, playtester. \n"
						+"Ned, playtester.  \n"
						+"Coffee. It tasted really, really good. \n"
						+"And HOME - The egg sandwich.");
		System.out.println("");
		System.out.println("Thank you all for helping me with this CS210 project \n."
						  +"====================================================== \n");
		System.out.println("Do you wish to continue?");
		int input = 0;
		while(input != 1)
		{
			System.out.println("Press 1 to continue");
			input = 0;
			input = sc.nextInt();
			if(input == 1)
			{
				main(null);
			}
			else
			{
				System.out.println("Invalid Input, try again");
				continue;
			}
		}
	}
}
