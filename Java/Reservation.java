/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3760.904a8c2 modeling language!*/


import java.util.*;

// line 38 "model.ump"
// line 103 "model.ump"
public class Reservation
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Integer, Reservation> reservationsByReservationNumber = new HashMap<Integer, Reservation>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Reservation Attributes
  private int seatsNeeded;
  private int reservationNumber;

  //Reservation Associations
  private Table table;
  private Customer customer;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Reservation(int aSeatsNeeded, int aReservationNumber, Table aTable, Customer aCustomer)
  {
    seatsNeeded = aSeatsNeeded;
    if (!setReservationNumber(aReservationNumber))
    {
      throw new RuntimeException("Cannot create due to duplicate reservationNumber");
    }
    boolean didAddTable = setTable(aTable);
    if (!didAddTable)
    {
      throw new RuntimeException("Unable to create reservation due to table");
    }
    boolean didAddCustomer = setCustomer(aCustomer);
    if (!didAddCustomer)
    {
      throw new RuntimeException("Unable to create reservation due to customer");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setSeatsNeeded(int aSeatsNeeded)
  {
    boolean wasSet = false;
    seatsNeeded = aSeatsNeeded;
    wasSet = true;
    return wasSet;
  }

  public int getSeatsNeeded()
  {
    return seatsNeeded;
  }

  public int getReservationNumber()
  {
    return reservationNumber;
  }
  /* Code from template attribute_GetUnique */
  public static Reservation getWithReservationNumber(int aReservationNumber)
  {
    return reservationsByReservationNumber.get(aReservationNumber);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithReservationNumber(int aReservationNumber)
  {
    return getWithReservationNumber(aReservationNumber) != null;
  }
  /* Code from template association_GetOne */
  public Table getTable()
  {
    return table;
  }
  /* Code from template association_GetOne */
  public Customer getCustomer()
  {
    return customer;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setTable(Table aNewTable)
  {
    boolean wasSet = false;
    if (aNewTable == null)
    {
      //Unable to setTable to null, as reservation must always be associated to a table
      return wasSet;
    }
    
    Reservation existingReservation = aNewTable.getReservation();
    if (existingReservation != null && !equals(existingReservation))
    {
      //Unable to setTable, the current table already has a reservation, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Table anOldTable = table;
    table = aNewTable;
    table.setReservation(this);

    if (anOldTable != null)
    {
      anOldTable.setReservation(null);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setCustomer(Customer aNewCustomer)
  {
    boolean wasSet = false;
    if (aNewCustomer == null)
    {
      //Unable to setCustomer to null, as reservation must always be associated to a customer
      return wasSet;
    }
    
    Reservation existingReservation = aNewCustomer.getReservation();
    if (existingReservation != null && !equals(existingReservation))
    {
      //Unable to setCustomer, the current customer already has a reservation, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Customer anOldCustomer = customer;
    customer = aNewCustomer;
    customer.setReservation(this);

    if (anOldCustomer != null)
    {
      anOldCustomer.setReservation(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    reservationsByReservationNumber.remove(getReservationNumber());
    Table existingTable = table;
    table = null;
    if (existingTable != null)
    {
      existingTable.setReservation(null);
    }
    Customer existingCustomer = customer;
    customer = null;
    if (existingCustomer != null)
    {
      existingCustomer.setReservation(null);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "seatsNeeded" + ":" + getSeatsNeeded()+ "," +
            "reservationNumber" + ":" + getReservationNumber()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "table = "+(getTable()!=null?Integer.toHexString(System.identityHashCode(getTable())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "customer = "+(getCustomer()!=null?Integer.toHexString(System.identityHashCode(getCustomer())):"null");
  }
}