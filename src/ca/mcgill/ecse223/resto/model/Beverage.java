package ca.mcgill.ecse223.resto.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/


import java.util.*;

// line 31 "model.ump"
// line 97 "model.ump"
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