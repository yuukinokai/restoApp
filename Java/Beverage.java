/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3760.904a8c2 modeling language!*/


import java.util.*;

// line 32 "model.ump"
// line 98 "model.ump"
public class Beverage extends MenuItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Beverage Attributes
  private boolean isAlcohol;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Beverage(String aName, double aPrice, boolean aIsAlcohol)
  {
    super(aName, aPrice);
    isAlcohol = aIsAlcohol;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setIsAlcohol(boolean aIsAlcohol)
  {
    boolean wasSet = false;
    isAlcohol = aIsAlcohol;
    wasSet = true;
    return wasSet;
  }

  public boolean getIsAlcohol()
  {
    return isAlcohol;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isIsAlcohol()
  {
    return isAlcohol;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "isAlcohol" + ":" + getIsAlcohol()+ "]";
  }
}