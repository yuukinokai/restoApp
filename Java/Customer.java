/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3760.904a8c2 modeling language!*/


import java.util.*;

// line 57 "model.ump"
// line 120 "model.ump"
public class Customer
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Customer Attributes
  private String name;
  private int age;

  //Customer Associations
  private List<IndividualBill> individualBills;
  private Seat seat;
  private Reservation reservation;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Customer(String aName, int aAge)
  {
    name = aName;
    age = aAge;
    individualBills = new ArrayList<IndividualBill>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setAge(int aAge)
  {
    boolean wasSet = false;
    age = aAge;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getAge()
  {
    return age;
  }
  /* Code from template association_GetMany */
  public IndividualBill getIndividualBill(int index)
  {
    IndividualBill aIndividualBill = individualBills.get(index);
    return aIndividualBill;
  }

  public List<IndividualBill> getIndividualBills()
  {
    List<IndividualBill> newIndividualBills = Collections.unmodifiableList(individualBills);
    return newIndividualBills;
  }

  public int numberOfIndividualBills()
  {
    int number = individualBills.size();
    return number;
  }

  public boolean hasIndividualBills()
  {
    boolean has = individualBills.size() > 0;
    return has;
  }

  public int indexOfIndividualBill(IndividualBill aIndividualBill)
  {
    int index = individualBills.indexOf(aIndividualBill);
    return index;
  }
  /* Code from template association_GetOne */
  public Seat getSeat()
  {
    return seat;
  }

  public boolean hasSeat()
  {
    boolean has = seat != null;
    return has;
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfIndividualBills()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public IndividualBill addIndividualBill(double aTotalPrice)
  {
    return new IndividualBill(aTotalPrice, this);
  }

  public boolean addIndividualBill(IndividualBill aIndividualBill)
  {
    boolean wasAdded = false;
    if (individualBills.contains(aIndividualBill)) { return false; }
    Customer existingCustomer = aIndividualBill.getCustomer();
    boolean isNewCustomer = existingCustomer != null && !this.equals(existingCustomer);
    if (isNewCustomer)
    {
      aIndividualBill.setCustomer(this);
    }
    else
    {
      individualBills.add(aIndividualBill);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeIndividualBill(IndividualBill aIndividualBill)
  {
    boolean wasRemoved = false;
    //Unable to remove aIndividualBill, as it must always have a customer
    if (!this.equals(aIndividualBill.getCustomer()))
    {
      individualBills.remove(aIndividualBill);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addIndividualBillAt(IndividualBill aIndividualBill, int index)
  {  
    boolean wasAdded = false;
    if(addIndividualBill(aIndividualBill))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfIndividualBills()) { index = numberOfIndividualBills() - 1; }
      individualBills.remove(aIndividualBill);
      individualBills.add(index, aIndividualBill);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveIndividualBillAt(IndividualBill aIndividualBill, int index)
  {
    boolean wasAdded = false;
    if(individualBills.contains(aIndividualBill))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfIndividualBills()) { index = numberOfIndividualBills() - 1; }
      individualBills.remove(aIndividualBill);
      individualBills.add(index, aIndividualBill);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addIndividualBillAt(aIndividualBill, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOptionalOneToOptionalOne */
  public boolean setSeat(Seat aNewSeat)
  {
    boolean wasSet = false;
    if (aNewSeat == null)
    {
      Seat existingSeat = seat;
      seat = null;
      
      if (existingSeat != null && existingSeat.getCustomer() != null)
      {
        existingSeat.setCustomer(null);
      }
      wasSet = true;
      return wasSet;
    }

    Seat currentSeat = getSeat();
    if (currentSeat != null && !currentSeat.equals(aNewSeat))
    {
      currentSeat.setCustomer(null);
    }

    seat = aNewSeat;
    Customer existingCustomer = aNewSeat.getCustomer();

    if (!equals(existingCustomer))
    {
      aNewSeat.setCustomer(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setReservation(Reservation aNewReservation)
  {
    boolean wasSet = false;
    if (reservation != null && !reservation.equals(aNewReservation) && equals(reservation.getCustomer()))
    {
      //Unable to setReservation, as existing reservation would become an orphan
      return wasSet;
    }

    reservation = aNewReservation;
    Customer anOldCustomer = aNewReservation != null ? aNewReservation.getCustomer() : null;

    if (!this.equals(anOldCustomer))
    {
      if (anOldCustomer != null)
      {
        anOldCustomer.reservation = null;
      }
      if (reservation != null)
      {
        reservation.setCustomer(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=individualBills.size(); i > 0; i--)
    {
      IndividualBill aIndividualBill = individualBills.get(i - 1);
      aIndividualBill.delete();
    }
    if (seat != null)
    {
      seat.setCustomer(null);
    }
    Reservation existingReservation = reservation;
    reservation = null;
    if (existingReservation != null)
    {
      existingReservation.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "age" + ":" + getAge()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "seat = "+(getSeat()!=null?Integer.toHexString(System.identityHashCode(getSeat())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "reservation = "+(getReservation()!=null?Integer.toHexString(System.identityHashCode(getReservation())):"null");
  }
}