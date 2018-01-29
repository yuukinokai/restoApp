package ca.mcgill.ecse223.resto.model;
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.27.0.3728.d139ed893 modeling language!*/


import java.util.*;

// line 44 "model.ump"
// line 108 "model.ump"
public class Order
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Order Associations
  private MenuItem menuItem;
  private List<IndividualBill> individualBills;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Order(MenuItem aMenuItem)
  {
    boolean didAddMenuItem = setMenuItem(aMenuItem);
    if (!didAddMenuItem)
    {
      throw new RuntimeException("Unable to create order due to menuItem");
    }
    individualBills = new ArrayList<IndividualBill>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public MenuItem getMenuItem()
  {
    return menuItem;
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

  public boolean setMenuItem(MenuItem aMenuItem)
  {
    boolean wasSet = false;
    if (aMenuItem == null)
    {
      return wasSet;
    }

    MenuItem existingMenuItem = menuItem;
    menuItem = aMenuItem;
    if (existingMenuItem != null && !existingMenuItem.equals(aMenuItem))
    {
      existingMenuItem.removeOrder(this);
    }
    menuItem.addOrder(this);
    wasSet = true;
    return wasSet;
  }

  public boolean isNumberOfIndividualBillsValid()
  {
    boolean isValid = numberOfIndividualBills() >= minimumNumberOfIndividualBills();
    return isValid;
  }

  public static int minimumNumberOfIndividualBills()
  {
    return 1;
  }

  public boolean addIndividualBill(IndividualBill aIndividualBill)
  {
    boolean wasAdded = false;
    if (individualBills.contains(aIndividualBill)) { return false; }
    individualBills.add(aIndividualBill);
    if (aIndividualBill.indexOfOrder(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aIndividualBill.addOrder(this);
      if (!wasAdded)
      {
        individualBills.remove(aIndividualBill);
      }
    }
    return wasAdded;
  }

  public boolean removeIndividualBill(IndividualBill aIndividualBill)
  {
    boolean wasRemoved = false;
    if (!individualBills.contains(aIndividualBill))
    {
      return wasRemoved;
    }

    if (numberOfIndividualBills() <= minimumNumberOfIndividualBills())
    {
      return wasRemoved;
    }

    int oldIndex = individualBills.indexOf(aIndividualBill);
    individualBills.remove(oldIndex);
    if (aIndividualBill.indexOfOrder(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aIndividualBill.removeOrder(this);
      if (!wasRemoved)
      {
        individualBills.add(oldIndex,aIndividualBill);
      }
    }
    return wasRemoved;
  }

  public boolean setIndividualBills(IndividualBill... newIndividualBills)
  {
    boolean wasSet = false;
    ArrayList<IndividualBill> verifiedIndividualBills = new ArrayList<IndividualBill>();
    for (IndividualBill aIndividualBill : newIndividualBills)
    {
      if (verifiedIndividualBills.contains(aIndividualBill))
      {
        continue;
      }
      verifiedIndividualBills.add(aIndividualBill);
    }

    if (verifiedIndividualBills.size() != newIndividualBills.length || verifiedIndividualBills.size() < minimumNumberOfIndividualBills())
    {
      return wasSet;
    }

    ArrayList<IndividualBill> oldIndividualBills = new ArrayList<IndividualBill>(individualBills);
    individualBills.clear();
    for (IndividualBill aNewIndividualBill : verifiedIndividualBills)
    {
      individualBills.add(aNewIndividualBill);
      if (oldIndividualBills.contains(aNewIndividualBill))
      {
        oldIndividualBills.remove(aNewIndividualBill);
      }
      else
      {
        aNewIndividualBill.addOrder(this);
      }
    }

    for (IndividualBill anOldIndividualBill : oldIndividualBills)
    {
      anOldIndividualBill.removeOrder(this);
    }
    wasSet = true;
    return wasSet;
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

  public void delete()
  {
    MenuItem placeholderMenuItem = menuItem;
    this.menuItem = null;
    if(placeholderMenuItem != null)
    {
      placeholderMenuItem.removeOrder(this);
    }
    ArrayList<IndividualBill> copyOfIndividualBills = new ArrayList<IndividualBill>(individualBills);
    individualBills.clear();
    for(IndividualBill aIndividualBill : copyOfIndividualBills)
    {
      if (aIndividualBill.numberOfOrders() <= IndividualBill.minimumNumberOfOrders())
      {
        aIndividualBill.delete();
      }
      else
      {
        aIndividualBill.removeOrder(this);
      }
    }
  }

}