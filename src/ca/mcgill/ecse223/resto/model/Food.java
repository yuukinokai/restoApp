package ca.mcgill.ecse223.resto.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/


import java.util.*;

// line 22 "model.ump"
// line 92 "model.ump"
public class Food extends MenuItem
{

  //------------------------
  // ENUMERATIONS
  //------------------------

  public enum FoodCategory { Appetizer, MainCourse, Dessert }

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Food Attributes
  private FoodCategory foodCategory;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Food(String aName, double aPrice, FoodCategory aFoodCategory)
  {
    super(aName, aPrice);
    foodCategory = aFoodCategory;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setFoodCategory(FoodCategory aFoodCategory)
  {
    boolean wasSet = false;
    foodCategory = aFoodCategory;
    wasSet = true;
    return wasSet;
  }

  public FoodCategory getFoodCategory()
  {
    return foodCategory;
  }

  public void delete()
  {
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "foodCategory" + "=" + (getFoodCategory() != null ? !getFoodCategory().equals(this)  ? getFoodCategory().toString().replaceAll("  ","    ") : "this" : "null");
  }
}