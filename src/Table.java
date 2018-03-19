/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/



// line 1 "TableState.ump"
public class Table
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Table Attributes
  private boolean almostAllPaid;

  //Table State Machines
  public enum Status { Available, InUse, Billing }
  public enum StatusInUse { Null, Ordering, AllSeatsBilled }
  private Status status;
  private StatusInUse statusInUse;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Table()
  {
    almostAllPaid = false;
    setStatusInUse(StatusInUse.Null);
    setStatus(Status.Available);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setAlmostAllPaid(boolean aAlmostAllPaid)
  {
    boolean wasSet = false;
    almostAllPaid = aAlmostAllPaid;
    wasSet = true;
    return wasSet;
  }

  public boolean getAlmostAllPaid()
  {
    return almostAllPaid;
  }

  public boolean isAlmostAllPaid()
  {
    return almostAllPaid;
  }

  public String getStatusFullName()
  {
    String answer = status.toString();
    if (statusInUse != StatusInUse.Null) { answer += "." + statusInUse.toString(); }
    return answer;
  }

  public Status getStatus()
  {
    return status;
  }

  public StatusInUse getStatusInUse()
  {
    return statusInUse;
  }

  public boolean addToOrder(Order aOrder)
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    StatusInUse aStatusInUse = statusInUse;
    switch (aStatus)
    {
      case Available:
        // line 5 "TableState.ump"
        addOrder(aOrder);
				getRestoApp().addOrder(aOrder); 
				getRestoApp().addCurrentOrder(aOrder);
        setStatusInUse(StatusInUse.Ordering);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    switch (aStatusInUse)
    {
      case Ordering:
        exitStatusInUse();
        // line 14 "TableState.ump"
        addOrder(aOrder);
					getRestoApp().addOrder(aOrder); 
					getRestoApp().addCurrentOrder(aOrder);
        setStatusInUse(StatusInUse.Ordering);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean payBill(Bill aBill)
  {
    boolean wasEventProcessed = false;
    
    StatusInUse aStatusInUse = statusInUse;
    switch (aStatusInUse)
    {
      case Ordering:
        if (!(almostAllPaid()))
        {
          exitStatus();
        // line 20 "TableState.ump"
          seats.addBill(aBill);
          setStatus(Status.Billing);
          wasEventProcessed = true;
          break;
        }
        if (almostAllPaid())
        {
          exitStatusInUse();
        // line 25 "TableState.ump"
          seats.addBill(aBill);
          setStatusInUse(StatusInUse.AllSeatsBilled);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void exitStatus()
  {
    switch(status)
    {
      case InUse:
        exitStatusInUse();
        break;
    }
  }

  private void setStatus(Status aStatus)
  {
    status = aStatus;

    // entry actions and do activities
    switch(status)
    {
      case InUse:
        if (statusInUse == StatusInUse.Null) { setStatusInUse(StatusInUse.Ordering); }
        break;
    }
  }

  private void exitStatusInUse()
  {
    switch(statusInUse)
    {
      case Ordering:
        setStatusInUse(StatusInUse.Null);
        break;
      case AllSeatsBilled:
        setStatusInUse(StatusInUse.Null);
        break;
    }
  }

  private void setStatusInUse(StatusInUse aStatusInUse)
  {
    statusInUse = aStatusInUse;
    if (status != Status.InUse && aStatusInUse != StatusInUse.Null) { setStatus(Status.InUse); }
  }

  public void delete()
  {}


  public String toString()
  {
    return super.toString() + "["+
            "almostAllPaid" + ":" + getAlmostAllPaid()+ "]";
  }
}