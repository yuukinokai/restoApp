package ca.mcgill.ecse223.resto.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/


import java.util.*;

// line 49 "model.ump"
// line 113 "model.ump"
public class IndividualBill
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //IndividualBill Attributes
  private double totalPrice;
  private boolean isPaid;

  //IndividualBill Associations
  private List<Order> orders;
  private Customer customer;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public IndividualBill(double aTotalPrice, Customer aCustomer)
  {
    totalPrice = aTotalPrice;
    isPaid = false;
    orders = new ArrayList<Order>();
    boolean didAddCustomer = setCustomer(aCustomer);
    if (!didAddCustomer)
    {
      throw new RuntimeException("Unable to create individualBill due to customer");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setTotalPrice(double aTotalPrice)
  {
    boolean wasSet = false;
    totalPrice = aTotalPrice;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsPaid(boolean aIsPaid)
  {
    boolean wasSet = false;
    isPaid = aIsPaid;
    wasSet = true;
    return wasSet;
  }

  public double getTotalPrice()
  {
    return totalPrice;
  }

  public boolean getIsPaid()
  {
    return isPaid;
  }

  public boolean isIsPaid()
  {
    return isPaid;
  }

  public Order getOrder(int index)
  {
    Order aOrder = orders.get(index);
    return aOrder;
  }

  public List<Order> getOrders()
  {
    List<Order> newOrders = Collections.unmodifiableList(orders);
    return newOrders;
  }

  public int numberOfOrders()
  {
    int number = orders.size();
    return number;
  }

  public boolean hasOrders()
  {
    boolean has = orders.size() > 0;
    return has;
  }

  public int indexOfOrder(Order aOrder)
  {
    int index = orders.indexOf(aOrder);
    return index;
  }

  public Customer getCustomer()
  {
    return customer;
  }

  public boolean isNumberOfOrdersValid()
  {
    boolean isValid = numberOfOrders() >= minimumNumberOfOrders();
    return isValid;
  }

  public static int minimumNumberOfOrders()
  {
    return 1;
  }

  public boolean addOrder(Order aOrder)
  {
    boolean wasAdded = false;
    if (orders.contains(aOrder)) { return false; }
    orders.add(aOrder);
    if (aOrder.indexOfIndividualBill(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aOrder.addIndividualBill(this);
      if (!wasAdded)
      {
        orders.remove(aOrder);
      }
    }
    return wasAdded;
  }

  public boolean removeOrder(Order aOrder)
  {
    boolean wasRemoved = false;
    if (!orders.contains(aOrder))
    {
      return wasRemoved;
    }

    if (numberOfOrders() <= minimumNumberOfOrders())
    {
      return wasRemoved;
    }

    int oldIndex = orders.indexOf(aOrder);
    orders.remove(oldIndex);
    if (aOrder.indexOfIndividualBill(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aOrder.removeIndividualBill(this);
      if (!wasRemoved)
      {
        orders.add(oldIndex,aOrder);
      }
    }
    return wasRemoved;
  }

  public boolean setOrders(Order... newOrders)
  {
    boolean wasSet = false;
    ArrayList<Order> verifiedOrders = new ArrayList<Order>();
    for (Order aOrder : newOrders)
    {
      if (verifiedOrders.contains(aOrder))
      {
        continue;
      }
      verifiedOrders.add(aOrder);
    }

    if (verifiedOrders.size() != newOrders.length || verifiedOrders.size() < minimumNumberOfOrders())
    {
      return wasSet;
    }

    ArrayList<Order> oldOrders = new ArrayList<Order>(orders);
    orders.clear();
    for (Order aNewOrder : verifiedOrders)
    {
      orders.add(aNewOrder);
      if (oldOrders.contains(aNewOrder))
      {
        oldOrders.remove(aNewOrder);
      }
      else
      {
        aNewOrder.addIndividualBill(this);
      }
    }

    for (Order anOldOrder : oldOrders)
    {
      anOldOrder.removeIndividualBill(this);
    }
    wasSet = true;
    return wasSet;
  }

  public boolean addOrderAt(Order aOrder, int index)
  {  
    boolean wasAdded = false;
    if(addOrder(aOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrders()) { index = numberOfOrders() - 1; }
      orders.remove(aOrder);
      orders.add(index, aOrder);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOrderAt(Order aOrder, int index)
  {
    boolean wasAdded = false;
    if(orders.contains(aOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOrders()) { index = numberOfOrders() - 1; }
      orders.remove(aOrder);
      orders.add(index, aOrder);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOrderAt(aOrder, index);
    }
    return wasAdded;
  }

  public boolean setCustomer(Customer aCustomer)
  {
    boolean wasSet = false;
    if (aCustomer == null)
    {
      return wasSet;
    }

    Customer existingCustomer = customer;
    customer = aCustomer;
    if (existingCustomer != null && !existingCustomer.equals(aCustomer))
    {
      existingCustomer.removeIndividualBill(this);
    }
    customer.addIndividualBill(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (orders.size() > 0)
    {
      Order aOrder = orders.get(orders.size() - 1);
      aOrder.delete();
      orders.remove(aOrder);
    }
    
    Customer placeholderCustomer = customer;
    this.customer = null;
    if(placeholderCustomer != null)
    {
      placeholderCustomer.removeIndividualBill(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "totalPrice" + ":" + getTotalPrice()+ "," +
            "isPaid" + ":" + getIsPaid()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "customer = "+(getCustomer()!=null?Integer.toHexString(System.identityHashCode(getCustomer())):"null");
  }
}