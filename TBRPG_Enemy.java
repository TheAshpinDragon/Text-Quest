package com.ashen.ide.TextBasedRPG;

public class TBRPG_Enemy {
	
	  private int eNumber = 0;
	  private String name = new String();
	  private String dropCode = new String();
	  private String type = new String();
	  private int level = 0;
	  private double health = 0;
	    private double maxHealth = 0;
	  private int strength = 0;
	    private int weapon = 0;
	      private double damage = 0;
	    private int armor = 0;
	      private double defense = 0;
	  private double speed = 0;
	    private int lastAttacked = 0;
	    private boolean hasAttacked = false;
	  
	  public TBRPG_Enemy(int eNumber)
	  {
	    this.eNumber = eNumber;
	    this.type = new String();
	    this.name = new String();
	    this.dropCode = new String();
	    this.level = 1;
	    this.health = 10;
	      this.maxHealth = 10;
	    this.speed = 1;
	      this.lastAttacked = 0;
	      hasAttacked = false;
	    this.strength = 1;
	      this.weapon = 0;
	        this.damage = 0;
	      this.armor = 0;
	        this.defense = 0;
	    updateStats();
	  }
	  
	  public TBRPG_Enemy(int eNumber, String name, String dropCode, String type, int level, int health, int strength, int weapon, int armor, double speed)
	  {
	    this.eNumber = eNumber;
	    this.type = type;
	    this.name = name;
	    this.dropCode = dropCode;
	    this.level = level;
	    this.health = health;
	      this.maxHealth = health;
	    this.speed = speed;
	      this.lastAttacked = 0;
	      hasAttacked = false;
	    this.strength = strength;
	      this.weapon = weapon;
	        this.damage = 0;
	      this.armor = armor;
	        this.defense = 0;
	    updateStats();
	  }
	  
	  private void updateStats()
	  {
	    damage = ((TBRPG_Main.weaponDamage[weapon] * level) + (strength * (health / maxHealth)) + (speed * (health / maxHealth)));
	    defense = ((TBRPG_Main.armorTuffness[armor] * level) - ((maxHealth / health) - strength));
	    if(defense < 0){ defense = 0; }
	    if(damage < 0){ damage = 0; }
	    if(health > maxHealth){ health = maxHealth; }
	  }
	  
	  public void attack(TBRPG_Character target)
	  {
		updateStats(); target.updateHealth(-damage); hasAttacked = true;
        System.out.println("Enemy #" + eNumber + ": " + name.toUpperCase() + " did " + (((int)(damage * 100 + 0.5)) / 100.0) + " damage to CHARACTER #" + 
        					target.getNumber() + ": " + target.getName().toUpperCase() + "\n");
	  }
	  
	  public void getStatus()
	  {
	    System.out.println("ENEMY #" + eNumber + ": " + name.toUpperCase() + " STATUS: ");
	    System.out.println("  HP: " + (((int)(health * 100 + 0.5)) / 100.0) + "/" + maxHealth + "    \tLevel: " + level);
	    System.out.println("  Armor: " + TBRPG_Main.armorName[armor] + "\t\tDefense: " + TBRPG_Main.armorTuffness[armor]);
	    System.out.println("  Weapon: " + TBRPG_Main.weaponDamage[weapon] + "\t\tDamage: " +  (((int)(damage * 100 + 0.5)) / 100.0));
	    System.out.println("  Speed: " + speed + "\t\tLast attacked: " + lastAttacked);
	    System.out.println("\n");
	  }
	  
	  public int getNumber()
	  {
	    return this.eNumber;
	  }
	  
	  public String getName()
	  {
	    return name;
	  }
	  
	  public int getLastAttacked()
	  {
	    return lastAttacked;
	  }
	  
	  public double getHealth()
	  {
	    return health;
	  }
	  
	  public void updateHealth(double x)
	  {
	    health = health + x;
	  }
	  
	  public double getDamage()
	  {
	    return damage;
	  }
	  
	  public double getSpeed()
	  {
	    return speed;
	  }
	  
	  public boolean getAttacked() 
	  {
		  return hasAttacked;
	  }
	  
	  public void setAttaked(boolean atked)
	  {
		  hasAttacked = atked;
	  }
	  
	  public String getDrops()
	  {
	    return dropCode;
	  }
}
