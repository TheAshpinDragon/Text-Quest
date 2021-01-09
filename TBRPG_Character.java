package com.ashen.ide.TextBasedRPG;

public class TBRPG_Character {
	
	  private int CNumber = 0;
	  private String Type = new String();
	  private String Name = new String();
	  private int Level = 1;
	  private double Health = 0;
	    private double MaxHealth = 0;
	  private int Speed = 0;
	    private int LastAttacked = 0;
	    private boolean hasAttacked = false;
	  private int Strength = 0;
	    private int Weapon = 0;
	      private double Damage = 0;
	    private int Armor = 0;
	      private double Defense = 0;
	  
	  public TBRPG_Character(int cNumber)
	  {
	    this.CNumber = cNumber;
	    this.Type = new String();
	    this.Name = new String();
	    this.Level = 1;
	    this.Health = 10;
	      this.MaxHealth = 10;
	    this.Speed = 1;
	      this.LastAttacked = 0;
	      hasAttacked = false;
	    this.Strength = 1;
	      this.Weapon = 0;
	        this.Damage = 0;
	      this.Armor = 0;
	        this.Defense = 0;
	    this.LastAttacked = 0;
	    updateStats();
	  }
	  
	  public TBRPG_Character(int cNumber, String type, String name, int level, int health, int speed, int strength, int weapon, int armor)
	  {
	    this.CNumber = cNumber;
	    this.Type = type;
	    this.Name = name;
	    this.Level = level;
	    this.Health = health;
	      this.MaxHealth = health;
	    this.Speed = speed;
	      this.LastAttacked = 0;
	      hasAttacked = false;
	    this.Strength = strength;
	      this.Weapon = weapon;
	        this.Damage = 0;
	      this.Armor = armor;
	        this.Defense = 0;
	    updateStats();
	  }
	  
	  private void updateStats()
	  {
	    Damage = ((TBRPG_Main.weaponDamage[Weapon] * Level) + (Strength * (Health / MaxHealth)) + (Speed * (Health / MaxHealth)));
	    Defense = ((TBRPG_Main.armorTuffness[Armor] * Level) - ((MaxHealth / Health) - Strength));
	    if(Defense < 0){ Defense = 0; }
	    if(Damage < 0){ Damage = 0; }
	    if(Health > MaxHealth){ Health = MaxHealth; }
	  }

	  public void charAttack(TBRPG_Enemy target)
	  {
	    updateStats(); target.updateHealth(-Damage); hasAttacked = true;
	    System.out.println("\nCHARACTER #" + CNumber + ": " + Name.toUpperCase() + " did " + (((int)(Damage * 100 + 0.5)) / 100.0) + " damage to CHARACTER #" +
	    					target.getNumber() + ": " + target.getName().toUpperCase() + "\n");
	  }
	  
	  public void getStatus()
	  {
	    System.out.println("CHARACTER #" + CNumber + ": " + Name.toUpperCase() + " STATUS: ");
	    System.out.println("  HP: " + (((int)(Health * 100 + 0.5)) / 100.0) + "/" + MaxHealth + "\tLevel: " + Level);
	    System.out.println("  Armor: " + TBRPG_Main.armorName[Armor] + "\t\tDefense: " + TBRPG_Main.armorTuffness[Armor]);
	    System.out.println("  Weapon: " + TBRPG_Main.weaponDamage[Weapon] + "\t\tDamage: " +  (((int)(Damage * 100 + 0.5)) / 100.0));
	    System.out.println("  Speed: " + Speed + "\t\tLast attacked: " + LastAttacked);
	    System.out.println("\n");
	  }
	  
	  public static void playerBlock(double blkType, TBRPG_Enemy target)
	  {
	    //double protection = 
	  }
	  
	  public int getNumber()
	  {
	    return CNumber;
	  }
	  
	  public String getType()
	  {
	    return Type;
	  }
	  
	  public String getName()
	  {
	    return Name;
	  }
	  
	  public int getLastAttacked()
	  {
	    return LastAttacked;
	  }
	  
	  public double getHealth()
	  {
	    return Health;
	  }
	  
	  public void updateHealth(double x)
	  {
	    Health = Health + x;
	  }
	  
	  public double getSpeed()
	  {
	    return Speed;
	  }

	  public boolean getAttacked() 
	  {
		  return hasAttacked;
	  }
	  
	  public void setAttaked(boolean atked)
	  {
		  hasAttacked = atked;
	  }
	  
}
