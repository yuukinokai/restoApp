/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3760.904a8c2 modeling language!*/



// line 11 "model.ump"
// line 81 "model.ump"
public class Seat
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Seat Attributes
  private boolean isUsed;

  //Seat Associations
  private Table table;
  private Customer customer;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Seat(Table aTable)
  {
    isUsed = false;
    boolean didAddTable = setTable(aTable);
    if (!didAddTable)
    {
      throw new RuntimeException("Unable to create seat due to table");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setIsUsed(boolean aIsUsed)
  {
    boolean wasSet = false;
    isUsed = aIsUsed;
    wasSet = true;
    return wasSet;
  }

  public boolean getIsUsed()
  {
    return isUsed;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isIsUsed()
  {
    return isUsed;
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

  public boolean hasCustomer()
  {
    boolean has = customer != null;
    return has;
  }
  /* Code from template association_SetOneToMany */
  public boolean setTable(Table aTable)
  {
    boolean wasSet = false;
    if (aTable == null)
    {
      return wasSet;
    }

    Table existingTable = table;
    table = aTable;
    if (existingTable != null && !existingTable.equals(aTable))
    {
      existingTable.removeSeat(this);
    }
    table.addSeat(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setCustomer(Customer aNewCustomer)
  {
    boolean wasSet = false;
    if (customer != null && !customer.equals(aNewCustomer) && equals(customer.getSeat()))
    {
      //Unable to setCustomer, as existing customer would become an orphan
      return wasSet;
    }

    customer = aNewCustomer;
    Seat anOldSeat = aNewCustomer != null ? aNewCustomer.getSeat() : null;

    if (!this.equals(anOldSeat))
    {
      if (anOldSeat != null)
      {
        anOldSeat.customer = null;
      }
      if (customer != null)
      {
        customer.setSeat(this);
      }
    }
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Table placeholderTable = table;
    this.table = null;
    if(placeholderTable != null)
    {
      placeholderTable.removeSeat(this);
    }
    Customer existingCustomer = customer;
    customer = null;
    if (existingCustomer != null)
    {
      existingCustomer.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "isUsed" + ":" + getIsUsed()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "table = "+(getTable()!=null?Integer.toHexString(System.identityHashCode(getTable())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "customer = "+(getCustomer()!=null?Integer.toHexString(System.identityHashCode(getCustomer())):"null");
  }
}