/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3760.904a8c2 modeling language!*/


import java.util.*;

// line 2 "model.ump"
// line 75 "model.ump"
public class Table
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<Integer, Table> tablesByTableNumber = new HashMap<Integer, Table>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Table Attributes
  private int tableNumber;
  private int numberOfSeats;
  private boolean isReserved;
  private boolean isUsed;

  //Table Associations
  private List<Seat> seats;
  private Reservation reservation;
  private Location location;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Table(int aTableNumber, int aNumberOfSeats, Location aLocation)
  {
    numberOfSeats = aNumberOfSeats;
    isReserved = false;
    isUsed = false;
    if (!setTableNumber(aTableNumber))
    {
      throw new RuntimeException("Cannot create due to duplicate tableNumber");
    }
    seats = new ArrayList<Seat>();
    boolean didAddLocation = setLocation(aLocation);
    if (!didAddLocation)
    {
      throw new RuntimeException("Unable to create table due to location");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumberOfSeats(int aNumberOfSeats)
  {
    boolean wasSet = false;
    numberOfSeats = aNumberOfSeats;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsReserved(boolean aIsReserved)
  {
    boolean wasSet = false;
    isReserved = aIsReserved;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsUsed(boolean aIsUsed)
  {
    boolean wasSet = false;
    isUsed = aIsUsed;
    wasSet = true;
    return wasSet;
  }

  public int getTableNumber()
  {
    return tableNumber;
  }
  /* Code from template attribute_GetUnique */
  public static Table getWithTableNumber(int aTableNumber)
  {
    return tablesByTableNumber.get(aTableNumber);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithTableNumber(int aTableNumber)
  {
    return getWithTableNumber(aTableNumber) != null;
  }

  public int getNumberOfSeats()
  {
    return numberOfSeats;
  }

  public boolean getIsReserved()
  {
    return isReserved;
  }

  public boolean getIsUsed()
  {
    return isUsed;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isIsReserved()
  {
    return isReserved;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isIsUsed()
  {
    return isUsed;
  }
  /* Code from template association_GetMany */
  public Seat getSeat(int index)
  {
    Seat aSeat = seats.get(index);
    return aSeat;
  }

  public List<Seat> getSeats()
  {
    List<Seat> newSeats = Collections.unmodifiableList(seats);
    return newSeats;
  }

  public int numberOfSeats()
  {
    int number = seats.size();
    return number;
  }

  public boolean hasSeats()
  {
    boolean has = seats.size() > 0;
    return has;
  }

  public int indexOfSeat(Seat aSeat)
  {
    int index = seats.indexOf(aSeat);
    return index;
  }
  /* Code from template association_GetOne */
  public Reservation getReservation()
  {
    return reservation;
  }

  public boolean hasReservation()
  {
    boolean has = reservation != null;
    return has;
  }
  /* Code from template association_GetOne */
  public Location getLocation()
  {
    return location;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSeats()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Seat addSeat()
  {
    return new Seat(this);
  }

  public boolean addSeat(Seat aSeat)
  {
    boolean wasAdded = false;
    if (seats.contains(aSeat)) { return false; }
    Table existingTable = aSeat.getTable();
    boolean isNewTable = existingTable != null && !this.equals(existingTable);
    if (isNewTable)
    {
      aSeat.setTable(this);
    }
    else
    {
      seats.add(aSeat);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeSeat(Seat aSeat)
  {
    boolean wasRemoved = false;
    //Unable to remove aSeat, as it must always have a table
    if (!this.equals(aSeat.getTable()))
    {
      seats.remove(aSeat);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSeatAt(Seat aSeat, int index)
  {  
    boolean wasAdded = false;
    if(addSeat(aSeat))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSeats()) { index = numberOfSeats() - 1; }
      seats.remove(aSeat);
      seats.add(index, aSeat);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSeatAt(Seat aSeat, int index)
  {
    boolean wasAdded = false;
    if(seats.contains(aSeat))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSeats()) { index = numberOfSeats() - 1; }
      seats.remove(aSeat);
      seats.add(index, aSeat);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSeatAt(aSeat, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setReservation(Reservation aNewReservation)
  {
    boolean wasSet = false;
    if (reservation != null && !reservation.equals(aNewReservation) && equals(reservation.getTable()))
    {
      //Unable to setReservation, as existing reservation would become an orphan
      return wasSet;
    }

    reservation = aNewReservation;
    Table anOldTable = aNewReservation != null ? aNewReservation.getTable() : null;

    if (!this.equals(anOldTable))
    {
      if (anOldTable != null)
      {
        anOldTable.reservation = null;
      }
      if (reservation != null)
      {
        reservation.setTable(this);
      }
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToOptionalOne */
  public boolean setLocation(Location aNewLocation)
  {
    boolean wasSet = false;
    if (aNewLocation == null)
    {
      //Unable to setLocation to null, as table must always be associated to a location
      return wasSet;
    }
    
    Table existingTable = aNewLocation.getTable();
    if (existingTable != null && !equals(existingTable))
    {
      //Unable to setLocation, the current location already has a table, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Location anOldLocation = location;
    location = aNewLocation;
    location.setTable(this);

    if (anOldLocation != null)
    {
      anOldLocation.setTable(null);
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    tablesByTableNumber.remove(getTableNumber());
    while (seats.size() > 0)
    {
      Seat aSeat = seats.get(seats.size() - 1);
      aSeat.delete();
      seats.remove(aSeat);
    }
    
    Reservation existingReservation = reservation;
    reservation = null;
    if (existingReservation != null)
    {
      existingReservation.delete();
    }
    Location existingLocation = location;
    location = null;
    if (existingLocation != null)
    {
      existingLocation.setTable(null);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "tableNumber" + ":" + getTableNumber()+ "," +
            "numberOfSeats" + ":" + getNumberOfSeats()+ "," +
            "isReserved" + ":" + getIsReserved()+ "," +
            "isUsed" + ":" + getIsUsed()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "reservation = "+(getReservation()!=null?Integer.toHexString(System.identityHashCode(getReservation())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "location = "+(getLocation()!=null?Integer.toHexString(System.identityHashCode(getLocation())):"null");
  }
}