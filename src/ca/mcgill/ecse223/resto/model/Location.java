package ca.mcgill.ecse223.resto.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/


import java.util.*;

// line 65 "model.ump"
// line 127 "model.ump"
public class Location
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Location Attributes
  private String x;
  private String y;

  //Location Associations
  private Table table;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Location(String aX, String aY)
  {
    x = aX;
    y = aY;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setX(String aX)
  {
    boolean wasSet = false;
    x = aX;
    wasSet = true;
    return wasSet;
  }

  public boolean setY(String aY)
  {
    boolean wasSet = false;
    y = aY;
    wasSet = true;
    return wasSet;
  }

  public String getX()
  {
    return x;
  }

  public String getY()
  {
    return y;
  }

  public Table getTable()
  {
    return table;
  }

  public boolean hasTable()
  {
    boolean has = table != null;
    return has;
  }

  public boolean setTable(Table aNewTable)
  {
    boolean wasSet = false;
    if (table != null && !table.equals(aNewTable) && equals(table.getLocation()))
    {
      //Unable to setTable, as existing table would become an orphan
      return wasSet;
    }

    table = aNewTable;
    Location anOldLocation = aNewTable != null ? aNewTable.getLocation() : null;

    if (!this.equals(anOldLocation))
    {
      if (anOldLocation != null)
      {
        anOldLocation.table = null;
      }
      if (table != null)
      {
        table.setLocation(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Table existingTable = table;
    table = null;
    if (existingTable != null)
    {
      existingTable.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "x" + ":" + getX()+ "," +
            "y" + ":" + getY()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "table = "+(getTable()!=null?Integer.toHexString(System.identityHashCode(getTable())):"null");
  }
}