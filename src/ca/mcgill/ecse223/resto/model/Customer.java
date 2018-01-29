package ca.mcgill.ecse223.resto.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/


import java.util.*;

// line 56 "model.ump"
// line 119 "model.ump"
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

  public Customer(String aName, int aAge, Seat aSeat)
  {
    name = aName;
    age = aAge;
    individualBills = new ArrayList<IndividualBill>();
    boolean didAddSeat = setSeat(aSeat);
    if (!didAddSeat)
    {
      throw new RuntimeException("Unable to create customer due to seat");
    }
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

  public Seat getSeat()
  {
    return seat;
  }

  public Reservation getReservation()
  {
    return reservation;
  }

  public boolean hasReservation()
  {
    boolean has = reservation != null;
    return has;
  }

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

  public boolean setSeat(Seat aNewSeat)
  {
    boolean wasSet = false;
    if (aNewSeat == null)
    {
      //Unable to setSeat to null, as customer must always be associated to a seat
      return wasSet;
    }
    
    Customer existingCustomer = aNewSeat.getCustomer();
    if (existingCustomer != null && !equals(existingCustomer))
    {
      //Unable to setSeat, the current seat already has a customer, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    Seat anOldSeat = seat;
    seat = aNewSeat;
    seat.setCustomer(this);

    if (anOldSeat != null)
    {
      anOldSeat.setCustomer(null);
    }
    wasSet = true;
    return wasSet;
  }

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
    Seat existingSeat = seat;
    seat = null;
    if (existingSeat != null)
    {
      existingSeat.setCustomer(null);
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