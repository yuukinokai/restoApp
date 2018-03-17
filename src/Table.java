/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/



// line 2 "TableState.ump"
public class Table
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Table State Machines
  public enum Status { Available, InUse }
  public enum StatusInUse { Null, Ordering, AllSeatsBilled }
  private Status status;
  private StatusInUse statusInUse;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Table()
  {
    setStatusInUse(StatusInUse.Null);
    setStatus(Status.Available);
  }

  //------------------------
  // INTERFACE
  //------------------------

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

  public boolean toggleAvailableStatus()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case Available:
        // line 6 "TableState.ump"
        do: addOrder(Order aOrder)
        setStatus(Status.InUse);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean addOrder(Order aOrder)
  {
    boolean wasEventProcessed = false;
    
    StatusInUse aStatusInUse = statusInUse;
    switch (aStatusInUse)
    {
      case Ordering:
        exitStatusInUse();
        // line 11 "TableState.ump"
        order = getRestoApp.addOrder(aOrder); getRestoApp.addCurrentOrder(order);
        setStatusInUse(StatusInUse.Ordering);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean addBill(List<Seat> seats)
  {
    boolean wasEventProcessed = false;
    
    StatusInUse aStatusInUse = statusInUse;
    switch (aStatusInUse)
    {
      case Ordering:
        if (Table.numberOfCurrentSeats<seats.size())
        {
          exitStatusInUse();
        // line 12 "TableState.ump"
          Bill = aBill do: seats.addBill(aBill)
          setStatusInUse(StatusInUse.Ordering);
          wasEventProcessed = true;
          break;
        }
        exitStatusInUse();
        // line 13 "TableState.ump"
        Bill = aBill do: seats.addBill(aBill)
        setStatusInUse(StatusInUse.AllSeatsBilled);
        wasEventProcessed = true;
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

}